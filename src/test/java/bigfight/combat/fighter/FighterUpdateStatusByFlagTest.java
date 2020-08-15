package bigfight.combat.fighter;

import bigfight.combat.CombatTestUtil;
import bigfight.model.skill.skills.special.MineWater;
import bigfight.model.skill.struct.SkillIdentity;
import org.junit.jupiter.api.Test;

import static bigfight.model.skill.SkillFactoryTestUtil.DEFAULT_SKILL_FACTORY;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FighterUpdateStatusByFlagTest {

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
