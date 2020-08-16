// do functional test rather than unit test
package bigfight.combat;

import bigfight.combat.attack.SkillAttack;
import bigfight.combat.fighter.Fighter;
import bigfight.combat.fighter.FighterBuilderTestUtil;
import bigfight.combat.util.CombatRandom;
import bigfight.model.skill.skills.*;
import bigfight.model.skill.skills.special.BloodThirsty;
import bigfight.model.skill.skills.special.HakiProtect;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.warrior.component.Empowerment;
import bigfight.model.weapon.Weapon;
import bigfight.model.weapon.struct.WeaponIdentity;
import bigfight.ui.EnUi;
import bigfight.ui.Uiable;

import org.junit.jupiter.api.Test;
import static bigfight.model.skill.SkillFactoryTestUtil.DEFAULT_SKILL_FACTORY;
import static bigfight.model.weapon.WeaponFactoryTestUtil.DEFAULT_WEAPON_FACTORY;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CombatEachSkillTest {
    private Uiable mockUi = mock(EnUi.class);
    private CombatRandom mockRandom = mock(CombatRandom.class);
    private final int DAMAGE = 10;

    @Test
    void roar_ignore_one_round() {
        // fighter with equal attributes
        Fighter fighter1 = new FighterBuilderTestUtil().build();
        Fighter fighter2 = new FighterBuilderTestUtil().build();
        SkillModel skill = DEFAULT_SKILL_FACTORY.create(SkillIdentity.ROAR);

        // test
        final int EXPECTED = 1;
        new SkillAttack(fighter1, fighter2, skill, mockRandom, mockUi).attack();
        int result = fighter2.getFighterFlag().ignored;
        assertEquals(EXPECTED, result);
    }

    @Test
    void apparent_death_survive_deadly_attack() {
        Fighter fighter1 = new FighterBuilderTestUtil().build();
        Fighter test = CombatTestUtil.createDyingFighterWithApparentDeath();
        CombatRandom random = mock(CombatRandom.class);
        when(random.getWeaponDamageRandom(anyInt(), anyInt())).thenReturn(fighter1.getUnarmedDamage().lower());

        new Round(fighter1, test, CombatTestUtil.createUnarmedEmpowerment(), random, mockUi).fight();
        final int EXPECTED_HEALTH_LEFT = 1;
        assertEquals(EXPECTED_HEALTH_LEFT, test.getHealth());
    }

    @Test
    void bolt_from_the_blue_level_multiply() {
        // fighter with equal attributes
        Fighter fighter1 = new FighterBuilderTestUtil().build();
        Fighter fighter2 = new FighterBuilderTestUtil().build();
        SkillModel skill = DEFAULT_SKILL_FACTORY.create(SkillIdentity.BOLT_FROM_THE_BLUE);
        BoltFromTheBlue boltFromTheBlue = (BoltFromTheBlue) skill;
        Empowerment empowerment = new Empowerment(skill);

        // test
        final int EXPECTED = fighter2.getHealth() - (int) (boltFromTheBlue.getDamage() + (fighter1.getLevel() * boltFromTheBlue.getLevelMultiply()));
        new Round(fighter1, fighter2, empowerment, mockRandom, mockUi).fight();
        assertEquals(EXPECTED, fighter2.getHealth());
    }

    @Test
    // big, medium, small, throw, throwout are just copy paste. Not used in skill.
    void haki_protect_effective_example_unarmed() {
        final double INVOKE_HAKI = 0;
        Fighter fighter1 = new FighterBuilderTestUtil().build();
        Fighter fighter2 = CombatTestUtil.createLargeHealthFighterWithHakiProtect();
        SkillModel skill = DEFAULT_SKILL_FACTORY.create(SkillIdentity.HAKI_PROTECT); // same skill used in fighter 2
        HakiProtect hakiProtect = (HakiProtect) skill;
        CombatRandom random = mock(CombatRandom.class);
        when(random.getWeaponDamageRandom(anyInt(), anyInt())).thenReturn(DAMAGE);
        when(random.getHakiProtectRandom()).thenReturn(INVOKE_HAKI);
        // test
        final int EXPECTED_HEALTH = fighter2.getHealth() - (int) (DAMAGE * (1 - hakiProtect.getProtectionPercentage()));
        new Round(fighter1, fighter2, CombatTestUtil.createUnarmedEmpowerment(), random, mockUi).fight();
        assertEquals(EXPECTED_HEALTH, fighter2.getHealth());
    }

    @Test
    void haki_protect_invoke_limitation() {
        final double INVOKE_HAKI = 0;
        Fighter fighter1 = new FighterBuilderTestUtil().build();
        Fighter fighter2 = CombatTestUtil.createLargeHealthFighterWithHakiProtect();
        SkillModel skill = DEFAULT_SKILL_FACTORY.create(SkillIdentity.HAKI_PROTECT); // same skill used in fighter 2
        HakiProtect hakiProtect = (HakiProtect) skill;
        CombatRandom random = mock(CombatRandom.class);
        when(random.getWeaponDamageRandom(anyInt(), anyInt())).thenReturn(DAMAGE);
        when(random.getHakiProtectRandom()).thenReturn(INVOKE_HAKI);
        final int MAX_INVOCATION = hakiProtect.getRemainingUsage();
        // invoke n times
        for (int i = 0; i < MAX_INVOCATION; i += 1) {
            new Round(fighter1, fighter2, CombatTestUtil.createUnarmedEmpowerment(), random, mockUi).fight();
        }
        final int EXPECTED_HEALTH = fighter2.getHealth() - DAMAGE;
        new Round(fighter1, fighter2, CombatTestUtil.createUnarmedEmpowerment(), random, mockUi).fight();
        assertEquals(EXPECTED_HEALTH, fighter2.getHealth());
    }

    @Test
    void sea_reflect_successfully_reflect() {
        final double INVOKE_SKILL = 0;
        Fighter fighter1 = new FighterBuilderTestUtil().build();
        Fighter fighter2 = CombatTestUtil.createHealthyFighterWithSeaIsUnfathomable();
        CombatRandom random = mock(CombatRandom.class);
        when(random.getWeaponDamageRandom(anyInt(), anyInt())).thenReturn(DAMAGE);
        when(random.getSeaReflectRandom()).thenReturn(INVOKE_SKILL);

        final int EXPECTED_HEALTH = fighter1.getHealth() - DAMAGE;
        new Round(fighter1, fighter2, CombatTestUtil.createUnarmedEmpowerment(), random, mockUi).fight();
        assertEquals(EXPECTED_HEALTH, fighter1.getHealth());
    }

    @Test
    void sea_reflect_invoke_limitation() {
        final double INVOKE_SKILL = 0;
        Fighter fighter1 = new FighterBuilderTestUtil().build();
        Fighter fighter2 = CombatTestUtil.createHealthyFighterWithSeaIsUnfathomable();
        CombatRandom random = mock(CombatRandom.class);
        when(random.getWeaponDamageRandom(anyInt(), anyInt())).thenReturn(DAMAGE);
        when(random.getSeaReflectRandom()).thenReturn(INVOKE_SKILL);
        // use once to reduce remaining invocation to zero
        new Round(fighter1, fighter2, CombatTestUtil.createUnarmedEmpowerment(), random, mockUi).fight();
        // test
        final int EXPECTED_HEALTH = fighter1.getHealth();
        new Round(fighter1, fighter2, CombatTestUtil.createUnarmedEmpowerment(), random, mockUi).fight();
        assertEquals(EXPECTED_HEALTH, fighter1.getHealth());
    }

    @Test
    void tornado_strength_multiply() {
        Fighter fighter1 = new FighterBuilderTestUtil().build();
        Fighter fighter2 = new FighterBuilderTestUtil().build();
        SkillModel skill = DEFAULT_SKILL_FACTORY.create(SkillIdentity.TORNADO);
        Tornado tornado = (Tornado) skill;
        Empowerment empowerment = new Empowerment(skill);

        // test
        final int EXPECTED = fighter2.getHealth() - (int) (tornado.getDamage() + (fighter1.getStrength() * tornado.getStrengthMultiply()));
        new Round(fighter1, fighter2, empowerment, mockRandom, mockUi).fight();
        assertEquals(EXPECTED, fighter2.getHealth());
    }

    @Test
    void one_punch_seckill() {
        final double HIT = -1.0;
        Fighter fighter1 = new FighterBuilderTestUtil().build();
        Fighter fighter2 = new FighterBuilderTestUtil().build();
        SkillModel skill = DEFAULT_SKILL_FACTORY.create(SkillIdentity.ONE_PUNCH);
        Empowerment empowerment = new Empowerment(skill);
        CombatRandom random = mock(CombatRandom.class);
        when(random.getOnePunchRandom()).thenReturn(HIT);

        // test
        int EXPECTED = 1;
        new Round(fighter1, fighter2, empowerment, random, mockUi).fight();
        assertEquals(EXPECTED, fighter2.getHealth());
    }

    @Test
    void disarm_snatch_weapon_correct() {
        Fighter fighter1 = new FighterBuilderTestUtil().build();
        Fighter fighter2 = new FighterBuilderTestUtil().build();
        Weapon weapon = DEFAULT_WEAPON_FACTORY.create(WeaponIdentity.TRIDENT);
        final int DAMAGE = weapon.getDamage().lower();
        fighter2.changeWeapon(new Empowerment(weapon));
        SkillModel skill = DEFAULT_SKILL_FACTORY.create(SkillIdentity.DISARM);
        Empowerment empowerment = new Empowerment(skill);
        CombatRandom random = mock(CombatRandom.class);
        when(random.getWeaponDamageRandom(anyInt(), anyInt())).thenReturn(DAMAGE);

        // test
        final int EXPECTED = fighter2.getHealth() - DAMAGE;
        new Round(fighter1, fighter2, empowerment, random, mockUi).fight();
        assertEquals(EXPECTED, fighter2.getHealth());
    }

    @Test
    void glue_increases_ignore_when_select_non_throw() {
        final double NOT_SELECT_UNARMED = 1.0;
        final int SELECT_BIG_WEAPON = 0;
        Fighter fighter = new FighterBuilderTestUtil()
                .withWeapon(CombatTestUtil.createBigWeapon())
                .build();
        fighter.getFighterFlag().beingGlued = true;
        assertFalse(fighter.getFighterFlag().ignoredByUnselection);
        CombatRandom random = mock(CombatRandom.class);
        when(random.selectUnarmed()).thenReturn(NOT_SELECT_UNARMED);
        when(random.selectWeapon(anyInt())).thenReturn(SELECT_BIG_WEAPON);
        // test
        Empowerment empowerment = fighter.selectEmpowerment(random);
        assertNull(empowerment.getWeapon());
        assertTrue(fighter.getFighterFlag().ignoredByUnselection);
    }

    @Test
    void angels_wings_agility_multiply() {
        Fighter fighter1 = new FighterBuilderTestUtil().build();
        Fighter fighter2 = new FighterBuilderTestUtil().build();
        SkillModel skill = DEFAULT_SKILL_FACTORY.create(SkillIdentity.ANGELS_WINGS);
        AngelsWings angelsWings = (AngelsWings) skill;
        Empowerment empowerment = new Empowerment(skill);

        // test
        final int EXPECTED = fighter2.getHealth() - (int) (angelsWings.getDamage() + (fighter1.getAgility() * angelsWings.getAgilityMultiply()));
        new Round(fighter1, fighter2, empowerment, mockRandom, mockUi).fight();
        assertEquals(EXPECTED, fighter2.getHealth());
    }

    @Test
    void foshan_kick_strength_multiply() {
        Fighter fighter1 = new FighterBuilderTestUtil().build();
        Fighter fighter2 = new FighterBuilderTestUtil().build();
        SkillModel skill = DEFAULT_SKILL_FACTORY.create(SkillIdentity.FOSHAN_KICK);
        FoshanKick foshanKick = (FoshanKick) skill;
        Empowerment empowerment = new Empowerment(skill);

        // test
        final int EXPECTED = fighter2.getHealth() - (int) (foshanKick.getDamage() + (fighter1.getStrength() * foshanKick.getStrengthMultiply()));
        new Round(fighter1, fighter2, empowerment, mockRandom, mockUi).fight();
        assertEquals(EXPECTED, fighter2.getHealth());
    }

    @Test
    void tickle_damage_with_agility_multiply() {
        Fighter fighter1 = new FighterBuilderTestUtil().build();
        Fighter fighter2 = new FighterBuilderTestUtil().build();
        SkillModel skill = DEFAULT_SKILL_FACTORY.create(SkillIdentity.TICKLE);
        Tickle tickle = (Tickle) skill;
        Empowerment empowerment = new Empowerment(skill);

        // test
        final int EXPECTED_HEALTH = fighter2.getHealth() - (int) (tickle.getDamage() + (fighter1.getAgility() * tickle.getAgilityMultiply()));
        new Round(fighter1, fighter2, empowerment, mockRandom, mockUi).fight();
        assertEquals(EXPECTED_HEALTH, fighter2.getHealth());
    }

    @Test
    void weapon_rainstorm_effective() {
        Weapon weapon1 = CombatTestUtil.createBigWeapon();
        Weapon weapon2 = CombatTestUtil.createBigWeapon();
        Weapon weapon3 = CombatTestUtil.createBigWeapon();
        Fighter fighter1 = new FighterBuilderTestUtil()
                .withWeapon(weapon1)
                .withWeapon(weapon2)
                .withWeapon(weapon3)
                .build();
        Fighter fighter2 = new FighterBuilderTestUtil().build();
        WeaponRainstorm weaponRainstorm = (WeaponRainstorm) DEFAULT_SKILL_FACTORY.create(SkillIdentity.WEAPON_RAINSTORM);
        Empowerment empowerment = new Empowerment(weaponRainstorm);
        CombatRandom random = mock(CombatRandom.class);
        when(random.getWeaponDamageRandom(anyInt(), anyInt())).thenReturn(DAMAGE);
        // test
        final int EXPECTED_HEALTH = fighter2.getHealth() - DAMAGE * weaponRainstorm.getNumOfWeapons();
        new Round(fighter1, fighter2, empowerment, random, mockUi).fight();
        assertEquals(EXPECTED_HEALTH, fighter2.getHealth());
    }

    @Test
    void blood_thirsty_life_steal_example_case_of_big_weapon() {
        final double INVOKE = -1.0;
        BloodThirsty bloodThirsty = (BloodThirsty) DEFAULT_SKILL_FACTORY.create(SkillIdentity.BLOOD_THIRSTY);
        Fighter fighter1 = new FighterBuilderTestUtil().withSkill(bloodThirsty).build();
        fighter1.updateHealth(fighter1.getHealth() / 2);
        Fighter fighter2 = new FighterBuilderTestUtil().build();
        Empowerment empowerment = new Empowerment(CombatTestUtil.createBigWeapon());
        CombatRandom random = mock(CombatRandom.class);
        when(random.getWeaponDamageRandom(anyInt(), anyInt())).thenReturn(DAMAGE);
        when(random.getBloodThirstyRandom()).thenReturn(INVOKE);

        // test
        final int EXPECTED_HEALTH = fighter1.getHealth() + (int) (DAMAGE * bloodThirsty.getLifeStealPercentage());
        new Round(fighter1, fighter2, empowerment, random, mockUi).fight();
        assertEquals(EXPECTED_HEALTH, fighter1.getHealth());
    }
}
