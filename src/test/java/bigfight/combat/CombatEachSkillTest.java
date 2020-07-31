// do functional test rather than unit test
package bigfight.combat;

import bigfight.combat.fighter.FighterStatus;
import bigfight.combat.util.CombatRandom;
import bigfight.model.skill.skills.*;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.warrior.component.Empowerment;
import bigfight.model.weapon.Weapon;
import bigfight.model.weapon.struct.WeaponIdentity;
import bigfight.ui.EnUi;
import bigfight.ui.Uiable;

import org.junit.jupiter.api.Test;
import static bigfight.model.skill.SkillFactoryTestUtil.DEFAULT_SKILL_FACTORY;
import static bigfight.model.weapon.WeaponFactoryTestUtil.DEFAULT_WEAPON_FACTORY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CombatEachSkillTest {
    private static Uiable mockUi = mock(EnUi.class);

    private final double NO_ESCAPE = 1.0;
    private final int DAMAGE = 10;

    @Test
    void roar_ignore_one_round() {
        // fighter with equal attributes
        FighterStatus fighter1 = CombatTestUtil.createSimpleFixedFighter();
        FighterStatus fighter2 = CombatTestUtil.createSimpleFixedFighter();
        SkillModel skill = DEFAULT_SKILL_FACTORY.create(SkillIdentity.ROAR);
        Empowerment empowerment = new Empowerment(skill);
        CombatRandom random = mock(CombatRandom.class);
        when(random.getEscapeRandom()).thenReturn(NO_ESCAPE);

        // test
        final int EXPECTED = 1;
        int result = new Round(fighter1, fighter2, empowerment, random, mockUi).fight();
        assertEquals(EXPECTED, result);
    }

    @Test
    void apparent_death_survive_deadly_attack() {
        FighterStatus fighter1 = CombatTestUtil.createSimpleFixedFighter();
        FighterStatus test = CombatTestUtil.createDyingFighterWithApparentDeath();
        CombatRandom random = mock(CombatRandom.class);
        when(random.getEscapeRandom()).thenReturn(NO_ESCAPE);
        when(random.getWeaponDamageRandom(anyInt(), anyInt())).thenReturn(fighter1.getUnarmedDamage().lower());

        new Round(fighter1, test, CombatTestUtil.createUnarmedEmpowerment(), random, mockUi).fight();
        final int EXPECTED_HEALTH_LEFT = 1;
        assertEquals(EXPECTED_HEALTH_LEFT, test.getHealth());
    }

    @Test
    void bolt_from_the_blue_level_multiply() {
        // fighter with equal attributes
        FighterStatus fighter1 = CombatTestUtil.createSimpleFixedFighter();
        FighterStatus fighter2 = CombatTestUtil.createSimpleFixedFighter();
        SkillModel skill = DEFAULT_SKILL_FACTORY.create(SkillIdentity.BOLT_FROM_THE_BLUE);
        BoltFromTheBlue boltFromTheBlue = (BoltFromTheBlue) skill;
        Empowerment empowerment = new Empowerment(skill);
        CombatRandom random = mock(CombatRandom.class);
        when(random.getEscapeRandom()).thenReturn(NO_ESCAPE);

        // test
        final int EXPECTED = fighter2.getHealth() - (int) (boltFromTheBlue.getDamage() + (fighter1.getLevel() * boltFromTheBlue.getLevelMultiply()));
        new Round(fighter1, fighter2, empowerment, random, mockUi).fight();
        assertEquals(EXPECTED, fighter2.getHealth());
    }

    @Test
    void fast_hand_hit_twice() {
        FighterStatus fighter1 = CombatTestUtil.createSimpleFixedFighter();
        FighterStatus fighter2 = CombatTestUtil.createSimpleFixedFighter();
        fighter1.getFighterFlag().fastHandsFlag = true;
        Empowerment empowerment = CombatTestUtil.createUnarmedEmpowerment();
        CombatRandom random = mock(CombatRandom.class);
        when(random.getEscapeRandom()).thenReturn(NO_ESCAPE);
        when(random.getWeaponDamageRandom(anyInt(), anyInt())).thenReturn(DAMAGE);
        // test
        final int EXPECTED = fighter2.getHealth() - DAMAGE * 2;
        new Round(fighter1, fighter2, empowerment, random, mockUi).fight();
        assertEquals(EXPECTED, fighter2.getHealth());
    }

    @Test
    // big, medium, small, throw, throwout are just copy paste. Not used in skill.
    void haki_protect_effective_example_unarmed() {
        final double INVOKE_HAKI = 0;
        FighterStatus fighter1 = CombatTestUtil.createSimpleFixedFighter();
        FighterStatus fighter2 = CombatTestUtil.createLargeHealthFighterWithHakiProtect();
        SkillModel skill = DEFAULT_SKILL_FACTORY.create(SkillIdentity.HAKI_PROTECT); // same skill used in fighter 2
        HakiProtect hakiProtect = (HakiProtect) skill;
        CombatRandom random = mock(CombatRandom.class);
        when(random.getEscapeRandom()).thenReturn(NO_ESCAPE);
        when(random.getWeaponDamageRandom(anyInt(), anyInt())).thenReturn(DAMAGE);
        when(random.getHakiProtectRandom()).thenReturn(INVOKE_HAKI);
        final int EXPECTED_HEALTH = fighter2.getHealth() - (int) (DAMAGE * (1 - hakiProtect.getProtectionPercentage()));
        new Round(fighter1, fighter2, CombatTestUtil.createUnarmedEmpowerment(), random, mockUi).fight();
        assertEquals(EXPECTED_HEALTH, fighter2.getHealth());
    }

    @Test
    void haki_protect_invoke_limitation() {
        final double INVOKE_HAKI = 0;
        FighterStatus fighter1 = CombatTestUtil.createSimpleFixedFighter();
        FighterStatus fighter2 = CombatTestUtil.createLargeHealthFighterWithHakiProtect();
        SkillModel skill = DEFAULT_SKILL_FACTORY.create(SkillIdentity.HAKI_PROTECT); // same skill used in fighter 2
        HakiProtect hakiProtect = (HakiProtect) skill;
        CombatRandom random = mock(CombatRandom.class);
        when(random.getEscapeRandom()).thenReturn(NO_ESCAPE);
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
    void sea_is_unfathomable_reflect() {
        final double INVOKE_SKILL = 0;
        FighterStatus fighter1 = CombatTestUtil.createSimpleFixedFighter();
        FighterStatus fighter2 = CombatTestUtil.createHealthyFighterWithSeaIsUnfathomable();
        CombatRandom random = mock(CombatRandom.class);
        when(random.getEscapeRandom()).thenReturn(NO_ESCAPE);
        when(random.getWeaponDamageRandom(anyInt(), anyInt())).thenReturn(DAMAGE);
        when(random.getSeaReflectRandom()).thenReturn(INVOKE_SKILL);

        final int EXPECTED_HEALTH = fighter1.getHealth() - DAMAGE;
        new Round(fighter1, fighter2, CombatTestUtil.createUnarmedEmpowerment(), random, mockUi).fight();
        assertEquals(EXPECTED_HEALTH, fighter1.getHealth());
    }

    @Test
    void sea_is_unfathomable_invoke_limitation() {
        final double INVOKE_SKILL = 0;
        FighterStatus fighter1 = CombatTestUtil.createSimpleFixedFighter();
        FighterStatus fighter2 = CombatTestUtil.createHealthyFighterWithSeaIsUnfathomable();
        CombatRandom random = mock(CombatRandom.class);
        when(random.getEscapeRandom()).thenReturn(NO_ESCAPE);
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
        FighterStatus fighter1 = CombatTestUtil.createSimpleFixedFighter();
        FighterStatus fighter2 = CombatTestUtil.createSimpleFixedFighter();
        SkillModel skill = DEFAULT_SKILL_FACTORY.create(SkillIdentity.TORNADO);
        Tornado tornado = (Tornado) skill;
        Empowerment empowerment = new Empowerment(skill);
        CombatRandom random = mock(CombatRandom.class);
        when(random.getEscapeRandom()).thenReturn(NO_ESCAPE);

        // test
        final int EXPECTED = fighter2.getHealth() - (int) (tornado.getDamage() + (fighter1.getStrength() * tornado.getStrengthMultiply()));
        new Round(fighter1, fighter2, empowerment, random, mockUi).fight();
        assertEquals(EXPECTED, fighter2.getHealth());
    }

    @Test
    void hit_from_god_seckill() {
        final double HIT = -1.0;
        FighterStatus fighter1 = CombatTestUtil.createSimpleFixedFighter();
        FighterStatus fighter2 = CombatTestUtil.createSimpleFixedFighter();
        SkillModel skill = DEFAULT_SKILL_FACTORY.create(SkillIdentity.HIT_FROM_GOD);
        Empowerment empowerment = new Empowerment(skill);
        CombatRandom random = mock(CombatRandom.class);
        when(random.getHitFromGodRandom()).thenReturn(HIT);

        // test
        int EXPECTED = 1;
        new Round(fighter1, fighter2, empowerment, random, mockUi).fight();
        assertEquals(EXPECTED, fighter2.getHealth());
    }

    @Test
    void disarm_snatch_weapon_correct() {
        FighterStatus fighter1 = CombatTestUtil.createSimpleFixedFighter();
        FighterStatus fighter2 = CombatTestUtil.createSimpleFixedFighter();
        Weapon weapon = DEFAULT_WEAPON_FACTORY.create(WeaponIdentity.TRIDENT);
        final int DAMAGE = weapon.getDamage().lower();
        fighter2.changeWeapon(new Empowerment(weapon));
        SkillModel skill = DEFAULT_SKILL_FACTORY.create(SkillIdentity.DISARM);
        Empowerment empowerment = new Empowerment(skill);
        CombatRandom random = mock(CombatRandom.class);
        when(random.getEscapeRandom()).thenReturn(NO_ESCAPE);
        when(random.getWeaponDamageRandom(anyInt(), anyInt())).thenReturn(DAMAGE);

        // test
        final int EXPECTED = fighter2.getHealth() - DAMAGE;
        new Round(fighter1, fighter2, empowerment, random, mockUi).fight();
        assertEquals(EXPECTED, fighter2.getHealth());
    }

    @Test
    void shadow_move_activated() {
        final int DEFAULT_ROUND = 3;
        FighterStatus fighter = CombatTestUtil.createFighterWithShadowMove();
        fighter.getFighterFlag().shadowMoveFlag = true;
        fighter.getFighterFlag().shadowMoveRound = DEFAULT_ROUND;
        ShadowMove shadowMove = (ShadowMove) DEFAULT_SKILL_FACTORY.create(SkillIdentity.SHADOW_MOVE);
        final int EXPECTED_SPEED = (int) (fighter.getSpeed() * (1 + shadowMove.getSpeedMultiply()));
        final double EXPECTED_BIG_PERCENTAGE_DAMAGE = fighter.getAdvancedAttribute().bigExtraPercentageDamage + shadowMove.getDamageMultiply();
        final double EXPECTED_MEDIUM_PERCENTAGE_DAMAGE = fighter.getAdvancedAttribute().mediumExtraPercentageDamage + shadowMove.getDamageMultiply();
        final double EXPECTED_SMALL_PERCENTAGE_DAMAGE = fighter.getAdvancedAttribute().smallExtraPercentageDamage + shadowMove.getDamageMultiply();
        final double EXPECTED_THROW_PERCENTAGE_DAMAGE = fighter.getAdvancedAttribute().throwExtraPercentageDamage + shadowMove.getDamageMultiply();
        final double EXPECTED_UNARMED_PERCENTAGE_DAMAGE = fighter.getAdvancedAttribute().unarmedExtraPercentageDamage + shadowMove.getDamageMultiply();
        final double EXPECTED_SKILL_PERCENTAGE_DAMAGE = fighter.getAdvancedAttribute().skillExtraPercentageDamage + shadowMove.getDamageMultiply();
        fighter.updateStatusByFlag();
        assertEquals(EXPECTED_SPEED, fighter.getSpeed());
        assertEquals(EXPECTED_BIG_PERCENTAGE_DAMAGE, fighter.getAdvancedAttribute().bigExtraPercentageDamage);
        assertEquals(EXPECTED_MEDIUM_PERCENTAGE_DAMAGE, fighter.getAdvancedAttribute().mediumExtraPercentageDamage);
        assertEquals(EXPECTED_SMALL_PERCENTAGE_DAMAGE, fighter.getAdvancedAttribute().smallExtraPercentageDamage);
        assertEquals(EXPECTED_THROW_PERCENTAGE_DAMAGE, fighter.getAdvancedAttribute().throwExtraPercentageDamage);
        assertEquals(EXPECTED_UNARMED_PERCENTAGE_DAMAGE, fighter.getAdvancedAttribute().unarmedExtraPercentageDamage);
        assertEquals(EXPECTED_SKILL_PERCENTAGE_DAMAGE, fighter.getAdvancedAttribute().skillExtraPercentageDamage);
    }
}
