package bigfight.combat;

import bigfight.combat.attack.BigTypeAttack;
import bigfight.combat.attack.SkillAttack;
import bigfight.combat.attack.UnarmedAttack;
import bigfight.combat.fighter.Fighter;
import bigfight.combat.fighter.FighterBuilderTestUtil;
import bigfight.combat.util.CombatRandom;
import bigfight.model.skill.skills.Roar;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.warrior.component.Empowerment;
import bigfight.model.warrior.component.attr.AdvancedAttribute;
import bigfight.model.weapon.Weapon;
import bigfight.ui.EnUi;
import static bigfight.model.skill.SkillFactoryTestUtil.DEFAULT_SKILL_FACTORY;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CombatAttributeEffectTest {
    private final double EPSILON = 0.001;
    private final double COUNTER_ATTACK = -1.0;
    private final double NO_COUNTER_ATTACK = 1.0;
    private final double NO_COUNTER_ESCAPE = 1.0;
    private final int DAMAGE = 10;

    @Test
    // skill medium, small and throw since they copy from big type.
    void test_extra_percentage_damage_effective_in_attack_example_big() {
        double EXTRA_PERCENTAGE = 0.2;
        AdvancedAttribute advancedAttribute = new AdvancedAttribute();
        advancedAttribute.bigExtraPercentageDamage = EXTRA_PERCENTAGE;
        Fighter fighter1 = new FighterBuilderTestUtil().withAdvancedAttribute(advancedAttribute).build();
        Fighter fighter2 = new FighterBuilderTestUtil().build();
        Weapon weapon = CombatTestUtil.createBigWeapon();
        CombatRandom random = mock(CombatRandom.class);
        when(random.getWeaponDamageRandom(anyInt(), anyInt())).thenReturn(DAMAGE);
        // test
        int expectedHealth = fighter2.getHealth() - (int) (DAMAGE * (1 + EXTRA_PERCENTAGE));
        new BigTypeAttack(fighter1, fighter2, weapon, random, mock(EnUi.class)).attack();
        assertEquals(expectedHealth, fighter2.getHealth());
    }

    @Test
    void test_extra_percentage_damage_effective_in_counter_attack() {
        double EXTRA_PERCENTAGE = 0.2;
        AdvancedAttribute advancedAttribute = new AdvancedAttribute();
        advancedAttribute.bigExtraPercentageDamage = EXTRA_PERCENTAGE;
        Fighter fighter1 = new FighterBuilderTestUtil().build();
        Fighter fighter2 = new FighterBuilderTestUtil().withAdvancedAttribute(advancedAttribute).build();
        Weapon weapon = CombatTestUtil.createBigWeapon();
        fighter2.changeWeapon(new Empowerment(weapon));
        CombatRandom random = mock(CombatRandom.class);
        when(random.getCounterAttackRandom()).thenReturn(COUNTER_ATTACK).thenReturn(NO_COUNTER_ATTACK);
        when(random.getCounterEscapeRandom()).thenReturn(NO_COUNTER_ESCAPE);
        when(random.getWeaponDamageRandom(anyInt(), anyInt())).thenReturn(DAMAGE);
        // test
        int expectedHealth = fighter1.getHealth() - (int) (DAMAGE * (1 + EXTRA_PERCENTAGE));
        new UnarmedAttack(fighter1, fighter2, random, mock(EnUi.class)).attack();
        assertEquals(expectedHealth, fighter1.getHealth());
    }

    @Test
    void test_unarmed_extra_percentage_damage_effective_in_attack() {
        double EXTRA_PERCENTAGE = 0.2;
        AdvancedAttribute advancedAttribute = new AdvancedAttribute();
        advancedAttribute.unarmedExtraPercentageDamage = EXTRA_PERCENTAGE;
        Fighter fighter1 = new FighterBuilderTestUtil().withAdvancedAttribute(advancedAttribute).build();
        Fighter fighter2 = new FighterBuilderTestUtil().build();
        CombatRandom random = mock(CombatRandom.class);
        when(random.getWeaponDamageRandom(anyInt(), anyInt())).thenReturn(DAMAGE);
        // test
        int expectedHealth = fighter2.getHealth() - (int) (DAMAGE * (1 + EXTRA_PERCENTAGE));
        new UnarmedAttack(fighter1, fighter2, random, mock(EnUi.class)).attack();
        assertEquals(expectedHealth, fighter2.getHealth());
    }

    @Test
    // skill medium, small, throw ,unarmed and counter since they copy from big type.
    void test_anti_extra_percentage_damage_effective_in_attack_example_big() {
        double ATTACK_PERCENTAGE = 0.4;
        double DEFENCE_PERCENTAGE = 0.2;
        AdvancedAttribute attackerAttribute = new AdvancedAttribute();
        attackerAttribute.bigExtraPercentageDamage = ATTACK_PERCENTAGE;
        AdvancedAttribute defenderAttribute = new AdvancedAttribute();
        defenderAttribute.antiBigExtraPercentageDamage = DEFENCE_PERCENTAGE;
        Fighter fighter1 = new FighterBuilderTestUtil().withAdvancedAttribute(attackerAttribute).build();
        Fighter fighter2 = new FighterBuilderTestUtil().withAdvancedAttribute(defenderAttribute).build();
        Weapon weapon = CombatTestUtil.createBigWeapon();
        CombatRandom random = mock(CombatRandom.class);
        when(random.getWeaponDamageRandom(anyInt(), anyInt())).thenReturn(DAMAGE);
        // test
        int expectedHealth = fighter2.getHealth() - (int) (DAMAGE * (1 + ATTACK_PERCENTAGE - DEFENCE_PERCENTAGE));
        new BigTypeAttack(fighter1, fighter2, weapon, random, mock(EnUi.class)).attack();
        assertEquals(expectedHealth, fighter2.getHealth());
    }

    @Test
    // skip medium, small, throw, unarmed, counter, and skill.
    void test_evasion_rate_effective_in_attack_example_big() {
        double EXTRA_PERCENTAGE = 0.2;
        AdvancedAttribute advancedAttribute = new AdvancedAttribute();
        advancedAttribute.bigEvasionRate = EXTRA_PERCENTAGE;
        Fighter fighter1 = new FighterBuilderTestUtil().build();
        Fighter fighter2 = new FighterBuilderTestUtil().withAdvancedAttribute(advancedAttribute).build();
        Weapon weapon = CombatTestUtil.createBigWeapon();
        CombatRandom random = mock(CombatRandom.class);
        when(random.getEscapeRandom()).thenReturn((1 - EXTRA_PERCENTAGE) + EPSILON);
        when(random.getWeaponDamageRandom(anyInt(), anyInt())).thenReturn(DAMAGE);
        // test
        int expectedHealth = fighter2.getHealth();
        new BigTypeAttack(fighter1, fighter2, weapon, random, mock(EnUi.class)).attack();
        assertEquals(expectedHealth, fighter2.getHealth());
    }

    @Test
        // skill medium, small, throw ,unarmed and counter since they copy from big type.
    void test_double_hit_effective_in_attack_example_big() {
        final double DOUBLE_HIT_CHANCE = 0.2;
        final double DOUBLE_HIT = -1.0;
        AdvancedAttribute advancedAttribute = new AdvancedAttribute();
        advancedAttribute.doubleHitChance = DOUBLE_HIT_CHANCE;
        Fighter fighter1 = new FighterBuilderTestUtil().build();
        Fighter fighter2 = new FighterBuilderTestUtil().withAdvancedAttribute(advancedAttribute).build();
        Weapon weapon = CombatTestUtil.createBigWeapon();
        CombatRandom random = mock(CombatRandom.class);
        when(random.doubleHitRandom()).thenReturn(DOUBLE_HIT);
        when(random.getWeaponDamageRandom(anyInt(), anyInt())).thenReturn(DAMAGE);
        // test
        int expectedHealth = fighter2.getHealth() - 2 * DAMAGE;
        new BigTypeAttack(fighter1, fighter2, weapon, random, mock(EnUi.class)).attack();
        assertEquals(expectedHealth, fighter2.getHealth());
    }

    @Test
    void skill_extra_percentage_damage_effective_example_roar() {
        double EXTRA_PERCENTAGE = 0.2;
        AdvancedAttribute advancedAttribute = new AdvancedAttribute();
        advancedAttribute.skillExtraPercentageDamage = EXTRA_PERCENTAGE;
        Fighter fighter1 = new FighterBuilderTestUtil().withAdvancedAttribute(advancedAttribute).build();
        Fighter fighter2 = new FighterBuilderTestUtil().build();
        Roar roar = (Roar) DEFAULT_SKILL_FACTORY.create(SkillIdentity.ROAR);

        // test
        int expectedHealth = fighter2.getHealth() - (int) (roar.getDamage() * (1 + EXTRA_PERCENTAGE));
        new SkillAttack(fighter1, fighter2, roar, mock(CombatRandom.class), mock(EnUi.class)).attack();
        assertEquals(expectedHealth, fighter2.getHealth());
    }
}
