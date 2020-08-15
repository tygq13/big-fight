package bigfight.combat;

import bigfight.combat.attack.BigTypeAttack;
import bigfight.combat.attack.UnarmedAttack;
import bigfight.combat.fighter.Fighter;
import bigfight.combat.fighter.FighterBuilderTestUtil;
import bigfight.combat.util.CombatRandom;
import bigfight.model.warrior.component.Empowerment;
import bigfight.model.warrior.component.AdvancedAttribute;
import bigfight.model.weapon.Weapon;
import bigfight.ui.EnUi;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CombatAttributeEffectTest {
    private final static double EPSILON = 0.001;
    private final double NO_ESCAPE = 1.0;
    private final double NO_THROW = 1.0;
    private final double COUNTER_ATTACK = -1.0;
    private final double NO_COUNTER_ATTACK = 1.0;
    private final double NO_COUNTER_ESCAPE = 1.0;

    @Test
    // skill medium, small and throw since they copy from big type.
    void test_extra_percentage_damage_effective_in_attack_example_big() {
        double EXTRA_PERCENTAGE = 0.2;
        int WEAPON_DAMAGE = 10;
        AdvancedAttribute advancedAttribute = new AdvancedAttribute();
        advancedAttribute.bigExtraPercentageDamage = EXTRA_PERCENTAGE;
        Fighter fighter1 = new FighterBuilderTestUtil().withAdvancedAttribute(advancedAttribute).build();
        Fighter fighter2 = new FighterBuilderTestUtil().build();
        Weapon weapon = CombatTestUtil.createBigWeapon();
        CombatRandom random = mock(CombatRandom.class);
        when(random.getEscapeRandom()).thenReturn(NO_ESCAPE);
        when(random.getThrowWeaponRandom()).thenReturn(NO_THROW);
        when(random.getWeaponDamageRandom(anyInt(), anyInt())).thenReturn(WEAPON_DAMAGE);
        // test
        int expectedHealth = fighter2.getHealth() - (int) (WEAPON_DAMAGE * (1 + EXTRA_PERCENTAGE));
        new BigTypeAttack(fighter1, fighter2, weapon, random, mock(EnUi.class)).attack();
        assertEquals(expectedHealth, fighter2.getHealth());
    }

    @Test
    void test_extra_percentage_damage_effective_in_counter_attack() {
        double EXTRA_PERCENTAGE = 0.2;
        int WEAPON_DAMAGE = 10;
        AdvancedAttribute advancedAttribute = new AdvancedAttribute();
        advancedAttribute.bigExtraPercentageDamage = EXTRA_PERCENTAGE;
        Fighter fighter1 = new FighterBuilderTestUtil().build();
        Fighter fighter2 = new FighterBuilderTestUtil().withAdvancedAttribute(advancedAttribute).build();
        Weapon weapon = CombatTestUtil.createBigWeapon();
        fighter2.changeWeapon(new Empowerment(weapon));
        CombatRandom random = mock(CombatRandom.class);
        when(random.getEscapeRandom()).thenReturn(NO_ESCAPE);
        when(random.getCounterAttackRandom()).thenReturn(COUNTER_ATTACK).thenReturn(NO_COUNTER_ATTACK);
        when(random.getCounterEscapeRandom()).thenReturn(NO_COUNTER_ESCAPE);
        when(random.getWeaponDamageRandom(anyInt(), anyInt())).thenReturn(WEAPON_DAMAGE);
        // test
        int expectedHealth = fighter1.getHealth() - (int) (WEAPON_DAMAGE * (1 + EXTRA_PERCENTAGE));
        new UnarmedAttack(fighter1, fighter2, random, mock(EnUi.class)).attack();
        assertEquals(expectedHealth, fighter1.getHealth());
    }

    @Test
    void test_unarmed_extra_percentage_damage_effective_in_attack() {
        double EXTRA_PERCENTAGE = 0.2;
        int UNARMED_DAMAGE = 10;
        AdvancedAttribute advancedAttribute = new AdvancedAttribute();
        advancedAttribute.unarmedExtraPercentageDamage = EXTRA_PERCENTAGE;
        Fighter fighter1 = new FighterBuilderTestUtil().withAdvancedAttribute(advancedAttribute).build();
        Fighter fighter2 = new FighterBuilderTestUtil().build();
        CombatRandom random = mock(CombatRandom.class);
        when(random.getEscapeRandom()).thenReturn(NO_ESCAPE);
        when(random.getWeaponDamageRandom(anyInt(), anyInt())).thenReturn(UNARMED_DAMAGE);
        // test
        int expectedHealth = fighter2.getHealth() - (int) (UNARMED_DAMAGE * (1 + EXTRA_PERCENTAGE));
        new UnarmedAttack(fighter1, fighter2, random, mock(EnUi.class)).attack();
        assertEquals(expectedHealth, fighter2.getHealth());
    }

    @Test
    // skill medium, small, throw ,unarmed and counter since they copy from big type.
    void test_anti_extra_percentage_damage_effective_in_attack_example_big() {
        double EXTRA_PERCENTAGE = 0.2;
        int WEAPON_DAMAGE = 10;
        AdvancedAttribute advancedAttribute = new AdvancedAttribute();
        advancedAttribute.antiBigExtraPercentageDamage = EXTRA_PERCENTAGE;
        Fighter fighter1 = new FighterBuilderTestUtil().build();
        Fighter fighter2 = new FighterBuilderTestUtil().withAdvancedAttribute(advancedAttribute).build();
        Weapon weapon = CombatTestUtil.createBigWeapon();
        CombatRandom random = mock(CombatRandom.class);
        when(random.getEscapeRandom()).thenReturn(NO_ESCAPE);
        when(random.getThrowWeaponRandom()).thenReturn(NO_THROW);
        when(random.getWeaponDamageRandom(anyInt(), anyInt())).thenReturn(WEAPON_DAMAGE);
        // test
        int expectedHealth = fighter2.getHealth() - (int) (WEAPON_DAMAGE * (1 - EXTRA_PERCENTAGE));
        new BigTypeAttack(fighter1, fighter2, weapon, random, mock(EnUi.class)).attack();
        assertEquals(expectedHealth, fighter2.getHealth());
    }

    @Test
    // skip medium, small, throw, unarmed, counter, and skill.
    void test_evasion_rate_effective_in_attack_example_big() {
        double EXTRA_PERCENTAGE = 0.2;
        int WEAPON_DAMAGE = 10;
        AdvancedAttribute advancedAttribute = new AdvancedAttribute();
        advancedAttribute.bigEvasionRate = EXTRA_PERCENTAGE;
        Fighter fighter1 = new FighterBuilderTestUtil().build();
        Fighter fighter2 = new FighterBuilderTestUtil().withAdvancedAttribute(advancedAttribute).build();
        Weapon weapon = CombatTestUtil.createBigWeapon();
        CombatRandom random = mock(CombatRandom.class);
        when(random.getEscapeRandom()).thenReturn(EXTRA_PERCENTAGE - EPSILON);
        when(random.getThrowWeaponRandom()).thenReturn(NO_THROW);
        when(random.getWeaponDamageRandom(anyInt(), anyInt())).thenReturn(WEAPON_DAMAGE);
        // test
        int expectedHealth = fighter2.getHealth();
        new BigTypeAttack(fighter1, fighter2, weapon, random, mock(EnUi.class)).attack();
        assertEquals(expectedHealth, fighter2.getHealth());
    }

    @Test
        // skill medium, small, throw ,unarmed and counter since they copy from big type.
    void test_double_hit_effective_in_attack_example_big() {
        final double DOUBLE_HIT_CHANCE = 0.2;
        final int WEAPON_DAMAGE = 10;
        final double DOUBLE_HIT = -1.0;
        AdvancedAttribute advancedAttribute = new AdvancedAttribute();
        advancedAttribute.doubleHitChance = DOUBLE_HIT_CHANCE;
        Fighter fighter1 = new FighterBuilderTestUtil().build();
        Fighter fighter2 = new FighterBuilderTestUtil().withAdvancedAttribute(advancedAttribute).build();
        Weapon weapon = CombatTestUtil.createBigWeapon();
        CombatRandom random = mock(CombatRandom.class);
        when(random.getEscapeRandom()).thenReturn(NO_ESCAPE);
        when(random.getThrowWeaponRandom()).thenReturn(NO_THROW);
        when(random.doubleHitRandom()).thenReturn(DOUBLE_HIT);
        when(random.getWeaponDamageRandom(anyInt(), anyInt())).thenReturn(WEAPON_DAMAGE);
        // test
        int expectedHealth = fighter2.getHealth() - 2 * WEAPON_DAMAGE;
        new BigTypeAttack(fighter1, fighter2, weapon, random, mock(EnUi.class)).attack();
        assertEquals(expectedHealth, fighter2.getHealth());
    }
}
