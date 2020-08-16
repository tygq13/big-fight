package bigfight.combat.attack;

import bigfight.combat.fighter.Fighter;
import bigfight.combat.fighter.FighterBuilderTestUtil;
import bigfight.combat.util.CombatRandom;
import bigfight.data.DataConfig;
import bigfight.model.warrior.component.attr.AdvancedAttribute;
import bigfight.model.warrior.component.attr.AttackAttribute;
import bigfight.model.warrior.component.attr.DefenceAttribute;
import bigfight.ui.EnUi;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AttackCalculatorTest {
    double EPSILON = 0.01;

    @Test
    void critical_attack_effective() {
        final int DAMAGE = 10;
        final double EXTRA_PERCENTAGE = 0.2;
        AdvancedAttribute advancedAttribute = new AdvancedAttribute();
        advancedAttribute.skillCriticalChance = EXTRA_PERCENTAGE;
        DefenceAttribute defenceAttribute = advancedAttribute.skillDefenceAttribute();
        AttackAttribute attackAttribute = advancedAttribute.skillAttackAttribute();
        CombatRandom random = mock(CombatRandom.class);
        when(random.getCriticalAttackRandom()).thenReturn(1 - EXTRA_PERCENTAGE + EPSILON);
        // test
        int EXPECTED_DAMAGE = (int) (10 * DataConfig.CRITICAL_DAMAGE_BASE);
        int result = new AttackCalculator(attackAttribute, defenceAttribute, random).damageAttributeMultiply(DAMAGE);
        assertEquals(EXPECTED_DAMAGE, result);
    }

    @Test
    void anti_critical_chance_effective() {
        final int DAMAGE = 10;
        final double EXTRA_PERCENTAGE = 0.2;
        final double CRITICAL_ATTACK = 1.0;
        AdvancedAttribute advancedAttribute = new AdvancedAttribute();
        advancedAttribute.skillCriticalChance = EXTRA_PERCENTAGE;
        advancedAttribute.antiSkillCriticalChance = EXTRA_PERCENTAGE;
        DefenceAttribute defenceAttribute = advancedAttribute.skillDefenceAttribute();
        AttackAttribute attackAttribute = advancedAttribute.skillAttackAttribute();
        CombatRandom random = mock(CombatRandom.class);
        when(random.getCriticalAttackRandom()).thenReturn(CRITICAL_ATTACK);
        // test
        int result = new AttackCalculator(attackAttribute, defenceAttribute, random).damageAttributeMultiply(DAMAGE);
        assertEquals(DAMAGE, result);
    }

    @Test
    void anti_critical_damage_effective() {
        final int DAMAGE = 10;
        final double EXTRA_PERCENTAGE = 0.2;
        final double CRITICAL_DAMAGE = 0.4;
        final double ANTI_CRITICAL_DAMAGE = 0.2;
        AdvancedAttribute advancedAttribute = new AdvancedAttribute();
        advancedAttribute.skillCriticalChance = EXTRA_PERCENTAGE;
        advancedAttribute.skillCriticalDamagePercentage = CRITICAL_DAMAGE;
        advancedAttribute.antiSkillCriticalDamagePercentage = ANTI_CRITICAL_DAMAGE;
        DefenceAttribute defenceAttribute = advancedAttribute.skillDefenceAttribute();
        AttackAttribute attackAttribute = advancedAttribute.skillAttackAttribute();
        CombatRandom random = mock(CombatRandom.class);
        when(random.getCriticalAttackRandom()).thenReturn(1 - EXTRA_PERCENTAGE + EPSILON);
        // test
        int EXPECTED_DAMAGE = (int) (10 * (DataConfig.CRITICAL_DAMAGE_BASE + CRITICAL_DAMAGE - ANTI_CRITICAL_DAMAGE));
        int result = new AttackCalculator(attackAttribute, defenceAttribute, random).damageAttributeMultiply(DAMAGE);
        assertEquals(EXPECTED_DAMAGE, result);
    }
}
