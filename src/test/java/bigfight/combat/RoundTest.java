// do functional test rather than unit test
package bigfight.combat;

import bigfight.combat.fighter.FighterStatus;

import bigfight.combat.util.CombatAlgo;
import bigfight.combat.util.CombatRandom;
import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.warrior.component.Empowerment;
import bigfight.model.weapon.Weapon;
import bigfight.model.weapon.struct.WeaponIdentity;
import bigfight.ui.EnUi;
import bigfight.ui.Uiable;

import org.junit.jupiter.api.Test;
import static bigfight.model.weapon.WeaponFactoryUtil.DEFAULT_WEAPON_FACTORY;
import static bigfight.model.skill.SkillFactoryUtil.DEFAULT_SKILL_FACTORY;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoundTest {
    private final double NO_ESCAPE = 1.0;
    private final double NO_THROW = 1.0;

    private static Uiable mockUi = mock(EnUi.class);

    @Test
    void round_give_damage_in_simple_weapon_attack_with_example_trident() {
        FighterStatus fighter1 = CombatTestUtil.createSimpleFixedFighter();
        FighterStatus fighter2 = CombatTestUtil.createSimpleFixedFighter();
        Weapon weapon = DEFAULT_WEAPON_FACTORY.create(WeaponIdentity.TRIDENT);
        Empowerment empowerment = new Empowerment(weapon);
        CombatRandom random = mock(CombatRandom.class);
        when(random.getEscapeRandom()).thenReturn(NO_ESCAPE);
        when(random.getThrowWeaponRandom()).thenReturn(NO_THROW);
        when(random.getWeaponDamageRandom(anyInt(), anyInt())).thenReturn(weapon.getDamage().lower());

        // test
        int damage = weapon.getDamage().lower();
        final int EXPECTED_HEALTH = fighter2.getHealth() - damage;
        new Round(fighter1, fighter2, empowerment, random, mockUi).fight();
        assertEquals(EXPECTED_HEALTH, fighter2.getHealth());
    }

    @Test
    void round_give_damage_in_simple_skill_attack_with_example_roar() {
        FighterStatus fighter1 = CombatTestUtil.createSimpleFixedFighter();
        FighterStatus fighter2 = CombatTestUtil.createSimpleFixedFighter();
        SkillModel skill = DEFAULT_SKILL_FACTORY.create(SkillIdentity.ROAR);
        Empowerment empowerment = new Empowerment(skill);
        CombatRandom random = mock(CombatRandom.class);
        when(random.getEscapeRandom()).thenReturn(NO_ESCAPE);

        // test
        final int DAMAGE = 15; // the damage of one start ROAR is fixed at 15.
        final int EXPECTED_HEALTH = fighter2.getHealth() - DAMAGE;
        new Round(fighter1, fighter2, empowerment, random, mockUi).fight();
        assertEquals(EXPECTED_HEALTH, fighter2.getHealth());
    }

    @Test
    void round_give_damage_in_simple_attack_with_unarmed() {
        FighterStatus fighter1 = CombatTestUtil.createSimpleFixedFighter();
        FighterStatus fighter2 = CombatTestUtil.createSimpleFixedFighter();
        Empowerment empowerment = CombatTestUtil.createUnarmedEmpowerment();
        CombatRandom random = mock(CombatRandom.class);
        when(random.getEscapeRandom()).thenReturn(NO_ESCAPE);
        when(random.getWeaponDamageRandom(anyInt(), anyInt())).thenReturn(fighter1.getUnarmedDamage().lower());

        int EXPECTED_HEALTH = fighter2.getHealth() - fighter1.getUnarmedDamage().lower();
        new Round(fighter1, fighter2, empowerment, random, mockUi).fight();
        assertEquals(EXPECTED_HEALTH, fighter2.getHealth());
    }

    @Test
    void round_ignore_round_by_speed() {
        final int SPEED1 = 10;
        final int SPEED2 = 5;
        FighterStatus fighter1 = CombatTestUtil.createCustomFighter(SPEED1, 5, 5, 100, 10);
        FighterStatus fighter2 = CombatTestUtil.createCustomFighter(SPEED2, 5, 5, 100, 10);
        double ignore = CombatAlgo.ignoreBySpeed(SPEED1, SPEED2);
        CombatRandom random = mock(CombatRandom.class);
        when(random.getSpeedIgnoreRandom()).thenReturn(ignore - Math.ulp(1));

        int EXPECTED = 1;
        int result = new Round(fighter1, fighter2, CombatTestUtil.createUnarmedEmpowerment(), random, mockUi).fight();
        assertEquals(EXPECTED, result);
    }

    @Test
    void round_attack_miss_by_agility() {
        final int AGILITY1 = 10;
        final int AGILITY2 = 5;
        FighterStatus fighter1 = CombatTestUtil.createCustomFighter(5, AGILITY1, 5, 100, 10);
        FighterStatus fighter2 = CombatTestUtil.createCustomFighter(5, AGILITY2, 5, 100, 10);
        double escape = CombatAlgo.escapeByAgility(AGILITY2, AGILITY1);
        CombatRandom random = mock(CombatRandom.class);
        when(random.getEscapeRandom()).thenReturn(escape - Math.ulp(1));
        when(random.getThrowWeaponRandom()).thenReturn(NO_THROW);
        int EXPECTED_HEALTH = fighter2.getHealth(); // escaped, no damage done

        new Round(fighter1, fighter2, CombatTestUtil.createUnarmedEmpowerment(), random, mockUi).fight();
        assertEquals(EXPECTED_HEALTH, fighter2.getHealth());
    }

    @Test
    void round_attack_multiplied_by_strength() {
        final int STRENGTH1 = 100;
        final int STRENGTH2 = 50;
        FighterStatus fighter1 = CombatTestUtil.createCustomFighter(5, 5, STRENGTH1, 100, 10);
        FighterStatus fighter2 = CombatTestUtil.createCustomFighter(5, 5, STRENGTH2, 100, 10);
        double multiply = CombatAlgo.multiplyByStrength(STRENGTH1, STRENGTH2);
        CombatRandom random = mock(CombatRandom.class);
        when(random.getEscapeRandom()).thenReturn(NO_ESCAPE);
        when(random.getThrowWeaponRandom()).thenReturn(NO_THROW);
        when(random.getWeaponDamageRandom(anyInt(), anyInt())).thenReturn(fighter1.getUnarmedDamage().lower());
        int EXPECTED_HEALTH = fighter2.getHealth() - (int) (fighter1.getUnarmedDamage().lower() * (1 + multiply));

        new Round(fighter1, fighter2, CombatTestUtil.createUnarmedEmpowerment(), random, mockUi).fight();
        assertEquals(EXPECTED_HEALTH, fighter2.getHealth());
    }

    @Test
    void round_counter_attack_both_unarmed_do_damage() {
        final double COUNTER_ATTACK = -1.0;
        final double NO_COUNTER_ATTACK = 1;
        FighterStatus fighter1 = CombatTestUtil.createSimpleFixedFighter();
        FighterStatus fighter2 = CombatTestUtil.createSimpleFixedFighter();
        Empowerment empowerment = CombatTestUtil.createUnarmedEmpowerment();
        CombatRandom random = mock(CombatRandom.class);
        when(random.getThrowWeaponRandom()).thenReturn(NO_THROW);
        when(random.getEscapeRandom()).thenReturn(NO_ESCAPE);
        when(random.getCounterAttackRandom()).thenReturn(COUNTER_ATTACK).thenReturn(NO_COUNTER_ATTACK);
        when(random.getCounterEscapeRandom()).thenReturn(NO_ESCAPE);
        when(random.getWeaponDamageRandom(anyInt(), anyInt())).thenReturn(fighter1.getUnarmedDamage().lower());

        int EXPECTED_HEALTH_1 = fighter1.getHealth() - fighter2.getUnarmedDamage().lower();
        int EXPECTED_HEALTH_2 = fighter2.getHealth() - fighter1.getUnarmedDamage().lower();
        new Round(fighter1, fighter2, empowerment, random, mockUi).fight();
        assertEquals(EXPECTED_HEALTH_1, fighter1.getHealth());
        assertEquals(EXPECTED_HEALTH_2, fighter2.getHealth());
    }

    @Test
    void round_throw_weapon_cause_damage() {
        final double THROW_WEAPON = 0.05;
        FighterStatus fighter1 = CombatTestUtil.createSimpleFixedFighter();
        FighterStatus fighter2 = CombatTestUtil.createSimpleFixedFighter();
        Weapon weapon = DEFAULT_WEAPON_FACTORY.create(WeaponIdentity.TRIDENT);
        Empowerment empowerment = new Empowerment(weapon);
        CombatRandom random = mock(CombatRandom.class);
        when(random.getThrowWeaponRandom()).thenReturn(THROW_WEAPON);
        when(random.getEscapeRandom()).thenReturn(NO_ESCAPE);
        when(random.getWeaponDamageRandom(anyInt(), anyInt())).thenReturn(weapon.getDamage().lower());
        double multiply = CombatAlgo.multiplyByAgility(fighter1.getAgility(), fighter2.getAgility());
        int EXPECTED_HEALTH = fighter2.getHealth() - (int) (weapon.getDamage().lower() * (1 + multiply));

        new Round(fighter1, fighter2, empowerment, random, mockUi).fight();
        assertEquals(EXPECTED_HEALTH, fighter2.getHealth());
    }

    @Test
    void round_throw_weapon_loss_weapon() {
        final double THROW_WEAPON = 0.05;
        FighterStatus fighter1 = CombatTestUtil.createSimpleFixedFighter();
        FighterStatus fighter2 = CombatTestUtil.createSimpleFixedFighter();
        Weapon weapon = DEFAULT_WEAPON_FACTORY.create(WeaponIdentity.TRIDENT);
        Empowerment empowerment = new Empowerment(weapon);
        CombatRandom random = mock(CombatRandom.class);
        when(random.getThrowWeaponRandom()).thenReturn(THROW_WEAPON);

        new Round(fighter1, fighter2, empowerment, random, mockUi).fight();
        assertNull(fighter1.getHoldingWeapon());
    }
}
