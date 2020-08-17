// do functional test rather than unit test
package bigfight.combat;

import bigfight.combat.fighter.Fighter;

import bigfight.combat.fighter.FighterBuilderTestUtil;
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
import static bigfight.model.weapon.WeaponFactoryTestUtil.DEFAULT_WEAPON_FACTORY;
import static bigfight.model.skill.SkillFactoryTestUtil.DEFAULT_SKILL_FACTORY;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoundTest {
    private static Uiable mockUi = mock(EnUi.class);

    @Test
    void round_give_damage_in_simple_weapon_attack_with_example_trident() {
        Fighter fighter1 = new FighterBuilderTestUtil().build();
        Fighter fighter2 = new FighterBuilderTestUtil().build();
        Weapon weapon = DEFAULT_WEAPON_FACTORY.create(WeaponIdentity.TRIDENT);
        Empowerment empowerment = new Empowerment(weapon);
        CombatRandom random = mock(CombatRandom.class);
        when(random.getWeaponDamageRandom(anyInt(), anyInt())).thenReturn(weapon.getDamage().lower());

        // test
        int damage = weapon.getDamage().lower();
        final int EXPECTED_HEALTH = fighter2.getHealth() - damage;
        new Round(fighter1, fighter2, empowerment, random, mockUi).fight();
        assertEquals(EXPECTED_HEALTH, fighter2.getHealth());
    }

    @Test
    void round_give_damage_in_simple_skill_attack_with_example_roar() {
        Fighter fighter1 = new FighterBuilderTestUtil().build();
        Fighter fighter2 = new FighterBuilderTestUtil().build();
        SkillModel skill = DEFAULT_SKILL_FACTORY.create(SkillIdentity.ROAR);
        Empowerment empowerment = new Empowerment(skill);
        CombatRandom random = mock(CombatRandom.class);

        // test
        final int DAMAGE = 15; // the damage of one start ROAR is fixed at 15.
        final int EXPECTED_HEALTH = fighter2.getHealth() - DAMAGE;
        new Round(fighter1, fighter2, empowerment, random, mockUi).fight();
        assertEquals(EXPECTED_HEALTH, fighter2.getHealth());
    }

    @Test
    void round_give_damage_in_simple_attack_with_unarmed() {
        Fighter fighter1 = new FighterBuilderTestUtil().build();
        Fighter fighter2 = new FighterBuilderTestUtil().build();
        Empowerment empowerment = CombatTestUtil.createUnarmedEmpowerment();
        CombatRandom random = mock(CombatRandom.class);
        when(random.getWeaponDamageRandom(anyInt(), anyInt())).thenReturn(fighter1.getUnarmedDamage().lower());

        int EXPECTED_HEALTH = fighter2.getHealth() - fighter1.getUnarmedDamage().lower();
        new Round(fighter1, fighter2, empowerment, random, mockUi).fight();
        assertEquals(EXPECTED_HEALTH, fighter2.getHealth());
    }

    @Test
    void round_ignore_round_by_speed() {
        final int SPEED1 = 10;
        final int SPEED2 = 5;
        Fighter fighter1 = new FighterBuilderTestUtil().withSpeed(SPEED1).build();
        Fighter fighter2 = new FighterBuilderTestUtil().withSpeed(SPEED2).build();
        double ignore = CombatAlgo.ignoreBySpeed(SPEED1, SPEED2);
        CombatRandom random = mock(CombatRandom.class);
        when(random.getSpeedIgnoreRandom()).thenReturn(ignore - Math.ulp(1));

        int EXPECTED = 1;
        new Round(fighter1, fighter2, CombatTestUtil.createUnarmedEmpowerment(), random, mockUi).fight();
        int result = fighter2.getFighterFlag().ignored;
        assertEquals(EXPECTED, result);
    }

    @Test
    void round_attack_miss_by_agility() {
        final int AGILITY1 = 10;
        final int AGILITY2 = 5;
        Fighter fighter1 = new FighterBuilderTestUtil().withAgility(AGILITY1).build();
        Fighter fighter2 = new FighterBuilderTestUtil().withAgility(AGILITY2).build();
        double escape = CombatAlgo.escapeByAgility(AGILITY2, AGILITY1);
        CombatRandom random = mock(CombatRandom.class);
        when(random.getEscapeRandom()).thenReturn(escape - Math.ulp(1));
        int EXPECTED_HEALTH = fighter2.getHealth(); // escaped, no damage done

        new Round(fighter1, fighter2, CombatTestUtil.createUnarmedEmpowerment(), random, mockUi).fight();
        assertEquals(EXPECTED_HEALTH, fighter2.getHealth());
    }

    @Test
    void round_counter_attack_both_unarmed_do_damage() {
        final double COUNTER_ATTACK = -1.0;
        final double NO_COUNTER_ATTACK = 1;
        Fighter fighter1 = new FighterBuilderTestUtil().build();
        Fighter fighter2 = new FighterBuilderTestUtil().build();
        Empowerment empowerment = CombatTestUtil.createUnarmedEmpowerment();
        CombatRandom random = mock(CombatRandom.class);
        when(random.getCounterAttackRandom()).thenReturn(COUNTER_ATTACK).thenReturn(NO_COUNTER_ATTACK);
        when(random.getWeaponDamageRandom(anyInt(), anyInt())).thenReturn(fighter1.getUnarmedDamage().lower());

        int EXPECTED_HEALTH_1 = fighter1.getHealth() - fighter2.getUnarmedDamage().lower();
        int EXPECTED_HEALTH_2 = fighter2.getHealth() - fighter1.getUnarmedDamage().lower();
        new Round(fighter1, fighter2, empowerment, random, mockUi).fight();
        assertEquals(EXPECTED_HEALTH_1, fighter1.getHealth());
        assertEquals(EXPECTED_HEALTH_2, fighter2.getHealth());
    }

    @Test
    void round_throw_weapon_cause_damage() {
        final double THROW_WEAPON = 1.0;
        final int DAMAGE = 10;
        Fighter fighter1 = new FighterBuilderTestUtil().build();
        Fighter fighter2 = new FighterBuilderTestUtil().build();
        Weapon weapon = CombatTestUtil.createBigWeapon();
        Empowerment empowerment = new Empowerment(weapon);
        CombatRandom random = mock(CombatRandom.class);
        when(random.getThrowWeaponRandom()).thenReturn(THROW_WEAPON);
        when(random.getWeaponDamageRandom(anyInt(), anyInt())).thenReturn(DAMAGE);
        int EXPECTED_HEALTH = fighter2.getHealth() - DAMAGE;

        new Round(fighter1, fighter2, empowerment, random, mockUi).fight();
        assertEquals(EXPECTED_HEALTH, fighter2.getHealth());
    }

    @Test
    void round_throw_weapon_loss_weapon() {
        final double THROW_WEAPON = 1.0;
        Fighter fighter1 = new FighterBuilderTestUtil().build();
        Fighter fighter2 = new FighterBuilderTestUtil().build();
        Weapon weapon = CombatTestUtil.createBigWeapon();
        Empowerment empowerment = new Empowerment(weapon);
        CombatRandom random = mock(CombatRandom.class);
        when(random.getThrowWeaponRandom()).thenReturn(THROW_WEAPON);

        new Round(fighter1, fighter2, empowerment, random, mockUi).fight();
        assertNull(fighter1.getHoldingWeapon());
    }

    @Test
    void fight_method_call_new_round_update() {
        Fighter fighter1 = new FighterBuilderTestUtil().build();
        Fighter fighter2 = new FighterBuilderTestUtil().build();
        Fighter spy1 = spy(fighter1);
        Fighter spy2 = spy(fighter2);
        CombatRandom random = mock(CombatRandom.class);
        new Round(spy1, spy2, CombatTestUtil.createUnarmedEmpowerment(), random, mockUi);
        verify(spy1, atLeastOnce()).newRoundUpdate();
        verify(spy2, atLeastOnce()).newRoundUpdate();
    }
}
