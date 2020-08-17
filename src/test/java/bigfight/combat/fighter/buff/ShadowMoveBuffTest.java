package bigfight.combat.fighter.buff;

import bigfight.combat.fighter.Fighter;
import bigfight.combat.fighter.FighterBuilderTestUtil;
import bigfight.model.skill.skills.special.ShadowMove;
import bigfight.model.skill.struct.SkillIdentity;
import org.junit.jupiter.api.Test;

import static bigfight.model.skill.SkillFactoryTestUtil.DEFAULT_SKILL_FACTORY;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShadowMoveBuffTest {
    @Test
    void shadow_move_buff_invoke_effective() {
        Fighter fighter = new FighterBuilderTestUtil().build();
        ShadowMove shadowMove = (ShadowMove) DEFAULT_SKILL_FACTORY.create(SkillIdentity.SHADOW_MOVE);
        final int EXPECTED_SPEED = (int) (fighter.getSpeed() * (1 + shadowMove.getSpeedMultiply()));
        final double EXPECTED_BIG_PERCENTAGE_DAMAGE = fighter.getAdvancedAttribute().bigExtraPercentageDamage + shadowMove.getDamageMultiply();
        final double EXPECTED_MEDIUM_PERCENTAGE_DAMAGE = fighter.getAdvancedAttribute().mediumExtraPercentageDamage + shadowMove.getDamageMultiply();
        final double EXPECTED_SMALL_PERCENTAGE_DAMAGE = fighter.getAdvancedAttribute().smallExtraPercentageDamage + shadowMove.getDamageMultiply();
        final double EXPECTED_THROW_PERCENTAGE_DAMAGE = fighter.getAdvancedAttribute().throwExtraPercentageDamage + shadowMove.getDamageMultiply();
        final double EXPECTED_UNARMED_PERCENTAGE_DAMAGE = fighter.getAdvancedAttribute().unarmedExtraPercentageDamage + shadowMove.getDamageMultiply();
        final double EXPECTED_SKILL_PERCENTAGE_DAMAGE = fighter.getAdvancedAttribute().skillExtraPercentageDamage + shadowMove.getDamageMultiply();
        fighter.addBuff(shadowMove.createBuff());
        fighter.newRoundUpdate();
        assertEquals(EXPECTED_SPEED, fighter.getSpeed());
        assertEquals(EXPECTED_BIG_PERCENTAGE_DAMAGE, fighter.getAdvancedAttribute().bigExtraPercentageDamage);
        assertEquals(EXPECTED_MEDIUM_PERCENTAGE_DAMAGE, fighter.getAdvancedAttribute().mediumExtraPercentageDamage);
        assertEquals(EXPECTED_SMALL_PERCENTAGE_DAMAGE, fighter.getAdvancedAttribute().smallExtraPercentageDamage);
        assertEquals(EXPECTED_THROW_PERCENTAGE_DAMAGE, fighter.getAdvancedAttribute().throwExtraPercentageDamage);
        assertEquals(EXPECTED_UNARMED_PERCENTAGE_DAMAGE, fighter.getAdvancedAttribute().unarmedExtraPercentageDamage);
        assertEquals(EXPECTED_SKILL_PERCENTAGE_DAMAGE, fighter.getAdvancedAttribute().skillExtraPercentageDamage);
    }

    @Test
    void shadow_move_buff_round_limitation() {
        Fighter fighter = new FighterBuilderTestUtil().build();
        ShadowMove shadowMove = (ShadowMove) DEFAULT_SKILL_FACTORY.create(SkillIdentity.SHADOW_MOVE);
        final int DEFAULT_ROUND = shadowMove.getMaxRound();
        final int EXPECTED_SPEED = (fighter.getSpeed());
        final double EXPECTED_BIG_PERCENTAGE_DAMAGE = fighter.getAdvancedAttribute().bigExtraPercentageDamage;
        final double EXPECTED_MEDIUM_PERCENTAGE_DAMAGE = fighter.getAdvancedAttribute().mediumExtraPercentageDamage;
        final double EXPECTED_SMALL_PERCENTAGE_DAMAGE = fighter.getAdvancedAttribute().smallExtraPercentageDamage;
        final double EXPECTED_THROW_PERCENTAGE_DAMAGE = fighter.getAdvancedAttribute().throwExtraPercentageDamage;
        final double EXPECTED_UNARMED_PERCENTAGE_DAMAGE = fighter.getAdvancedAttribute().unarmedExtraPercentageDamage;
        final double EXPECTED_SKILL_PERCENTAGE_DAMAGE = fighter.getAdvancedAttribute().skillExtraPercentageDamage;
        fighter.addBuff(shadowMove.createBuff());
        for (int i = 0; i < DEFAULT_ROUND + 1; i ++) {
            fighter.newRoundUpdate();
        }
        assertEquals(EXPECTED_SPEED, fighter.getSpeed());
        assertEquals(EXPECTED_BIG_PERCENTAGE_DAMAGE, fighter.getAdvancedAttribute().bigExtraPercentageDamage);
        assertEquals(EXPECTED_MEDIUM_PERCENTAGE_DAMAGE, fighter.getAdvancedAttribute().mediumExtraPercentageDamage);
        assertEquals(EXPECTED_SMALL_PERCENTAGE_DAMAGE, fighter.getAdvancedAttribute().smallExtraPercentageDamage);
        assertEquals(EXPECTED_THROW_PERCENTAGE_DAMAGE, fighter.getAdvancedAttribute().throwExtraPercentageDamage);
        assertEquals(EXPECTED_UNARMED_PERCENTAGE_DAMAGE, fighter.getAdvancedAttribute().unarmedExtraPercentageDamage);
        assertEquals(EXPECTED_SKILL_PERCENTAGE_DAMAGE, fighter.getAdvancedAttribute().skillExtraPercentageDamage);
    }
}
