// do functional test rather than unit test
package bigfight.combat;

import bigfight.combat.attack.SkillAttack;
import bigfight.combat.fighter.Fighter;
import bigfight.combat.fighter.FighterBuilderTestUtil;
import bigfight.combat.util.CombatAlgo;
import bigfight.combat.util.CombatRandom;
import bigfight.model.skill.skills.*;
import bigfight.model.skill.skills.special.*;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.skill.struct.SkillStruct;
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
import static org.mockito.ArgumentMatchers.doubleThat;
import static org.mockito.Mockito.*;

class CombatEachSkillTest {
    private Uiable mockUi = mock(EnUi.class);
    private CombatRandom mockRandom = mock(CombatRandom.class);
    private final int DAMAGE = 10;
    private final double EPSILON = 0.01;

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
        when(random.getSingleSpecialRandom()).thenReturn(INVOKE_HAKI);
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
        when(random.getSingleSpecialRandom()).thenReturn(INVOKE_HAKI);
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
        Empowerment empowerment = fighter.getCombatSelector().selectEmpowerment(random);;
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
        when(random.getSingleSpecialRandom()).thenReturn(INVOKE);

        // test
        final int EXPECTED_HEALTH = fighter1.getHealth() + (int) (DAMAGE * bloodThirsty.getLifeStealPercentage());
        new Round(fighter1, fighter2, empowerment, random, mockUi).fight();
        assertEquals(EXPECTED_HEALTH, fighter1.getHealth());
    }

    @Test
    void dash_ignore_one_round() {
        Fighter fighter1 = new FighterBuilderTestUtil().build();
        Fighter fighter2 = new FighterBuilderTestUtil().build();
        SkillModel skill = DEFAULT_SKILL_FACTORY.create(SkillIdentity.DASH);

        // test
        final int EXPECTED = 1;
        new SkillAttack(fighter1, fighter2, skill, mockRandom, mockUi).attack();
        int result = fighter2.getFighterFlag().ignored;
        assertEquals(EXPECTED, result);
    }

    @Test
    void dash_damage_with_speed_multiply() {
        Fighter fighter1 = new FighterBuilderTestUtil().build();
        Fighter fighter2 = new FighterBuilderTestUtil().build();
        SkillModel skill = DEFAULT_SKILL_FACTORY.create(SkillIdentity.DASH);
        Dash tickle = (Dash) skill;
        Empowerment empowerment = new Empowerment(skill);

        // test
        final int EXPECTED_HEALTH = fighter2.getHealth() - (int) (tickle.getDamage() + (fighter1.getSpeed() * tickle.getSpeedMultiply()));
        new Round(fighter1, fighter2, empowerment, mockRandom, mockUi).fight();
        assertEquals(EXPECTED_HEALTH, fighter2.getHealth());
    }

    @Test
    void shake_dispose_opponent_weapon() {
        Fighter fighter1 = new FighterBuilderTestUtil().build();
        Fighter fighter2 = new FighterBuilderTestUtil()
                .withWeapon(DEFAULT_WEAPON_FACTORY.create(WeaponIdentity.TRIDENT))
                .build();
        Shake shake = (Shake) DEFAULT_SKILL_FACTORY.create(SkillIdentity.SHAKE);

        // test
        int EXPECTED = fighter2.getWeaponSize() - shake.getDisposeNum();
        new SkillAttack(fighter1, fighter2, shake, mockRandom, mockUi).attack();
        assertEquals(EXPECTED, fighter2.getWeaponSize());
    }

    @Test
    void windy_kick_speed_addition_and_damage_multiply() {
        Fighter fighter1 = new FighterBuilderTestUtil().build();
        Fighter fighter2 = new FighterBuilderTestUtil().build();
        WindyKick windyKick = (WindyKick) DEFAULT_SKILL_FACTORY.create(SkillIdentity.WINDY_KICK);

        // test
        int EXPECTED_HEALTH = fighter2.getHealth() - (int) ((fighter1.getSpeed() + windyKick.getSpeedAddition()) * windyKick.getSpeedMultiply());
        int EXPECTED_SPEED = fighter1.getSpeed();
        new SkillAttack(fighter1, fighter2, windyKick, mockRandom, mockUi).attack();
        assertEquals(EXPECTED_SPEED, fighter1.getSpeed());
        assertEquals(EXPECTED_HEALTH, fighter2.getHealth());
    }

    @Test
    void blood_sacrifice_life_steal_example_roar() {
        final double INVOKE = -1.0;
        BloodSacrifice bloodSacrifice = (BloodSacrifice) DEFAULT_SKILL_FACTORY.create(SkillIdentity.BLOOD_SACRIFICE);
        Fighter fighter1 = new FighterBuilderTestUtil().withSkill(bloodSacrifice).build();
        fighter1.updateHealth(fighter1.getHealth() / 2);
        Fighter fighter2 = new FighterBuilderTestUtil().build();
        Roar skill = (Roar) DEFAULT_SKILL_FACTORY.create(SkillIdentity.ROAR);
        Empowerment empowerment = new Empowerment(skill);
        CombatRandom random = mock(CombatRandom.class);
        when(random.getSingleSpecialRandom()).thenReturn(INVOKE);

        // test
        final int EXPECTED_HEALTH = fighter1.getHealth() + (int) (skill.getDamage() * bloodSacrifice.getLifeStealPercentage());
        new Round(fighter1, fighter2, empowerment, random, mockUi).fight();
        assertEquals(EXPECTED_HEALTH, fighter1.getHealth());
    }

    @Test
    void lucky_or_not_level_multiply() {
        Fighter fighter1 = new FighterBuilderTestUtil().build();
        Fighter fighter2 = new FighterBuilderTestUtil().build();
        LuckyOrNot skill = (LuckyOrNot) DEFAULT_SKILL_FACTORY.create(SkillIdentity.LUCKY_OR_NOT);

        // test
        final int EXPECTED = fighter2.getHealth() - (int) (skill.getDamage() + (fighter1.getLevel() * skill.getLevelMultiply()));
        new SkillAttack(fighter1, fighter2, skill, mockRandom, mockUi).attack();
        assertEquals(EXPECTED, fighter2.getHealth());
    }

    @Test
    void lucky_or_not_heal_when_bad_luck() {
        Fighter fighter1 = new FighterBuilderTestUtil().build();
        Fighter fighter2 = new FighterBuilderTestUtil().build();
        fighter2.updateHealth(fighter2.getHealth() / 2);
        LuckyOrNot luckyOrNot = (LuckyOrNot) DEFAULT_SKILL_FACTORY.create(SkillIdentity.LUCKY_OR_NOT);
        double LUCK = luckyOrNot.getLuckyChance();
        CombatRandom random = mock(CombatRandom.class);
        when(random.getLuckyOrNotRandom()).thenReturn(LUCK + EPSILON);

        // test
        final int EXPECTED = fighter2.getHealth() + luckyOrNot.getHeal();
        new SkillAttack(fighter1, fighter2, luckyOrNot, random, mockUi).attack();
        assertEquals(EXPECTED, fighter2.getHealth());
    }

    @Test
    void lucky_or_not_hit_rate_increment() {
        Fighter fighter1 = new FighterBuilderTestUtil().build();
        Fighter fighter2 = new FighterBuilderTestUtil().build();
        CombatRandom random = mock(CombatRandom.class);
        LuckyOrNot luckyOrNot = (LuckyOrNot) DEFAULT_SKILL_FACTORY.create(SkillIdentity.LUCKY_OR_NOT);
        // test
        double EXPECTED_HIT_RATE = luckyOrNot.getExtraHitRate();
        when(random.getEscapeRandom()).thenReturn(1 + EXPECTED_HIT_RATE - EPSILON);
        int ORIGINAL_HEALTH = fighter2.getHealth();
        new SkillAttack(fighter1, fighter2, luckyOrNot, random, mockUi).attack();
        assertNotEquals(ORIGINAL_HEALTH, fighter2.getHealth());
    }

    @Test
    void shock_wave_round_increment() {
        final int roundNum = 20;
        Fighter fighter1 = new FighterBuilderTestUtil().build();
        Fighter fighter2 = new FighterBuilderTestUtil().build();
        CombatRandom random = mock(CombatRandom.class);
        when(random.getWeaponDamageRandom(anyInt(), anyInt())).thenReturn(0); // zero damage,
        for (int i = 0; i < roundNum; i++) {
            // increase number of rounds
            new Round(fighter1, fighter2, CombatTestUtil.createUnarmedEmpowerment(), random, mockUi);
        }
        ShockWave shockWave = (ShockWave) DEFAULT_SKILL_FACTORY.create(SkillIdentity.SHOCK_WAVE);
        // test
        double EXPECTED_HIT_RATE = roundNum * shockWave.getHitRateIncrement();
        int EXPECTED_HEALTH = fighter2.getHealth() - (shockWave.getDamage() + roundNum * shockWave.getDamageIncrement());
        when(random.getEscapeRandom()).thenReturn(1 + EXPECTED_HIT_RATE - EPSILON);
        new SkillAttack(fighter1, fighter2, shockWave, random, mockUi).attack();
        assertEquals(EXPECTED_HEALTH, fighter2.getHealth());
    }

    @Test
    void acupointer_level_multiply() {
        Fighter fighter1 = new FighterBuilderTestUtil().build();
        Fighter fighter2 = new FighterBuilderTestUtil().build();
        Acupointer skill = (Acupointer) DEFAULT_SKILL_FACTORY.create(SkillIdentity.ACUPOINTER);

        // test
        final int EXPECTED = fighter2.getHealth() - (int) (skill.getDamage() + (fighter1.getLevel() * skill.getLevelMultiply()));
        new SkillAttack(fighter1, fighter2, skill, mockRandom, mockUi).attack();
        assertEquals(EXPECTED, fighter2.getHealth());
    }

    @Test
    void acupointer_seal_off_skill() {
        Fighter fighter1 = new FighterBuilderTestUtil().build();
        Fighter fighter2 = new FighterBuilderTestUtil().build();
        Acupointer skill = (Acupointer) DEFAULT_SKILL_FACTORY.create(SkillIdentity.ACUPOINTER);

        // test
        new SkillAttack(fighter1, fighter2, skill, mockRandom, mockUi).attack();
        int EXPECTED = skill.getNoSKillRounds();
        assertEquals(EXPECTED, fighter2.getFighterFlag().noSelectSkill);
    }

    @Test
    void acupointer_hit_rate_increment() {
        Fighter fighter1 = new FighterBuilderTestUtil().build();
        Fighter fighter2 = new FighterBuilderTestUtil().build();
        CombatRandom random = mock(CombatRandom.class);
        Acupointer acupointer = (Acupointer) DEFAULT_SKILL_FACTORY.create(SkillIdentity.ACUPOINTER);
        // test
        double EXPECTED_HIT_RATE = acupointer.getExtraHitRate();
        when(random.getEscapeRandom()).thenReturn(1 + EXPECTED_HIT_RATE - EPSILON);
        int ORIGINAL_HEALTH = fighter2.getHealth();
        new SkillAttack(fighter1, fighter2, acupointer, random, mockUi).attack();
        assertNotEquals(ORIGINAL_HEALTH, fighter2.getHealth());
    }

    @Test
    void heal_level_multiply() {
        final double SELECT = 0;
        Heal heal = (Heal) DEFAULT_SKILL_FACTORY.create(SkillIdentity.HEAL);
        HealUsable healUsable = (HealUsable) heal.getUsableInstance();
        Fighter fighter = new FighterBuilderTestUtil().withSkill(healUsable).build();
        CombatRandom random = mock(CombatRandom.class);
        when(random.selectHealingSkillRandom()).thenReturn(SELECT);
        int numOfInvocation = healUsable.getRemainingUsage();
        // test
        fighter.updateHealth(1);
        int EXPECTED_HEALTH = fighter.getHealth() + healUsable.getBaseHeal() + (int) (fighter.getLevel() *healUsable.getLevelMultiply());
        fighter.getCombatSelector().selectHealingSkill(random, fighter.getHealthObj(), fighter.getLevel());
        assertEquals(EXPECTED_HEALTH, fighter.getHealth());
    }

    @Test
    void heal_invoke_limitation() {
        final double SELECT = 0;
        Heal heal = (Heal) DEFAULT_SKILL_FACTORY.create(SkillIdentity.HEAL);
        HealUsable healUsable = (HealUsable) heal.getUsableInstance();
        Fighter fighter = new FighterBuilderTestUtil().withSkill(healUsable).build();
        CombatRandom random = mock(CombatRandom.class);
        when(random.selectHealingSkillRandom()).thenReturn(SELECT);
        int numOfInvocation = healUsable.getRemainingUsage();
        for (int i = 0; i < numOfInvocation; i++) {
            fighter.getCombatSelector().selectHealingSkill(random, fighter.getHealthObj(), fighter.getLevel());
        }
        // test
        fighter.updateHealth(1);
        int EXPECTED_HEALTH = fighter.getHealth();
        fighter.getCombatSelector().selectHealingSkill(random, fighter.getHealthObj(), fighter.getLevel());
        assertEquals(EXPECTED_HEALTH, fighter.getHealth());
    }

    @Test
    void stinky_feet_strength_and_same_sex_multiply() {
        Fighter fighter1 = new FighterBuilderTestUtil().build();
        Fighter fighter2 = new FighterBuilderTestUtil().build();
        StinkyFeet skill = (StinkyFeet) DEFAULT_SKILL_FACTORY.create(SkillIdentity.STINKY_FEET);
        final int STRENGTH_DAMAGE = (int) (fighter1.getStrength() * skill.getStrengthMultiply());
        // test
        final int EXPECTED = fighter2.getHealth() - (int) ((skill.getDamage() + STRENGTH_DAMAGE) * (1 + skill.getSameSexMultiply()));
        new SkillAttack(fighter1, fighter2, skill, mockRandom, mockUi).attack();
        assertEquals(EXPECTED, fighter2.getHealth());
    }

    @Test
    void stinky_feet_opposite_sex_no_multiply() {
        boolean male = true;
        boolean female = false;
        Fighter fighter1 = new FighterBuilderTestUtil().withSex(male).build();
        Fighter fighter2 = new FighterBuilderTestUtil().withSex(female).build();
        StinkyFeet skill = (StinkyFeet) DEFAULT_SKILL_FACTORY.create(SkillIdentity.STINKY_FEET);
        final int STRENGTH_DAMAGE = (int) (fighter1.getStrength() * skill.getStrengthMultiply());
        // test
        final int EXPECTED = fighter2.getHealth() - (skill.getDamage() + STRENGTH_DAMAGE);
        new SkillAttack(fighter1, fighter2, skill, mockRandom, mockUi).attack();
        assertEquals(EXPECTED, fighter2.getHealth());
    }

    @Test
    void ghost_one_behaviour_correct() {
        Fighter fighter1 = new FighterBuilderTestUtil().build();
        Fighter fighter2 = new FighterBuilderTestUtil().build();
        GhostOn skill = (GhostOn) DEFAULT_SKILL_FACTORY.create(SkillIdentity.GHOST_ON);
        GhostOn spy = spy(skill);
        double EXTRA_HIT_RATE = skill.getExtraHitRate(); // test extra hit rate
        int LEVEL_DMAGE = (int) (fighter1.getLevel() * skill.getLevelMultiply()); // test level multiply
        CombatRandom random = mock(CombatRandom.class);
        when(random.getEscapeRandom()).thenReturn(1 + EXTRA_HIT_RATE - EPSILON);
        // test
        final int EXPECTED = fighter2.getHealth() - (skill.getDamage() + LEVEL_DMAGE);
        new SkillAttack(fighter1, fighter2, spy, random, mockUi).attack();
        assertEquals(EXPECTED, fighter2.getHealth());
        verify(spy).createDebuff();
    }
}
