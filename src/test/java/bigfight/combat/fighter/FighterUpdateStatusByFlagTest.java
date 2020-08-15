package bigfight.combat.fighter;

import bigfight.combat.CombatTestUtil;
import bigfight.model.skill.skills.special.MineWater;
import bigfight.model.skill.skills.special.ShadowMove;
import bigfight.model.skill.struct.SkillIdentity;
import org.junit.jupiter.api.Test;

import static bigfight.model.skill.SkillFactoryTestUtil.DEFAULT_SKILL_FACTORY;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FighterUpdateStatusByFlagTest {

    @Test
    void shadow_move_activated() {
        final int DEFAULT_ROUND = 3;
        Fighter fighter = CombatTestUtil.createFighterWithShadowMove();
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

    @Test
    void mine_water_activated() {
        final int HEALTH = 200;
        final double DAMAGE_PERCENTAGE = 0.5;
        Fighter fighter = CombatTestUtil.createFighterWithMineWater(HEALTH);
        fighter.getFighterFlag().mineWaterFlag = true;
        fighter.updateHealth(fighter.getHealth() - (int) (fighter.getHealth() * DAMAGE_PERCENTAGE));
        MineWater mineWater = (MineWater) DEFAULT_SKILL_FACTORY.create(SkillIdentity.MINE_WATER);
        final int EXPECTED_HEALTH = fighter.getHealth() + (int) (HEALTH * mineWater.getRegeneratePercentage());
        fighter.updateStatusByFlag();
        assertEquals(EXPECTED_HEALTH, fighter.getHealth());
    }

    @Test
    void mine_water_minimum_heal() {
        final int HEALTH = 50;
        final double DAMAGE_PERCENTAGE = 0.5;
        Fighter fighter = CombatTestUtil.createFighterWithMineWater(HEALTH);
        fighter.getFighterFlag().mineWaterFlag = true;
        fighter.updateHealth(fighter.getHealth() - (int) (fighter.getHealth() * DAMAGE_PERCENTAGE));
        MineWater mineWater = (MineWater) DEFAULT_SKILL_FACTORY.create(SkillIdentity.MINE_WATER);
        final int EXPECTED_HEALTH = fighter.getHealth() + (int) (mineWater.getRegeneratePercentage() * 100);
        fighter.updateStatusByFlag();
        assertEquals(EXPECTED_HEALTH, fighter.getHealth());
    }

    @Test
    void test_tickle_causes_damage() {
        Fighter fighter = new FighterBuilderTestUtil().build();
        final int TICKLE_ROUNDS = 1;
        final int TICKLE_DAMAGE = 10;
        fighter.getFighterFlag().tickledRounds = TICKLE_ROUNDS;
        fighter.getFighterFlag().tickledDamage = TICKLE_DAMAGE;
        // test
        final int EXPECTED_HEALTH = fighter.getHealth() - TICKLE_DAMAGE;
        final int EXPECTED_REMAINING_ROUNDS = TICKLE_ROUNDS - 1;
        fighter.updateStatusByFlag();
        assertEquals(EXPECTED_HEALTH, fighter.getHealth());
        assertEquals(EXPECTED_REMAINING_ROUNDS, fighter.getFighterFlag().tickledRounds);

    }
}
