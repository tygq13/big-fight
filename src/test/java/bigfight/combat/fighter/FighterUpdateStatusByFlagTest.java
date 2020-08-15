package bigfight.combat.fighter;

import bigfight.combat.CombatTestUtil;
import bigfight.model.skill.skills.special.MineWater;
import bigfight.model.skill.struct.SkillIdentity;
import org.junit.jupiter.api.Test;

import static bigfight.model.skill.SkillFactoryTestUtil.DEFAULT_SKILL_FACTORY;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FighterUpdateStatusByFlagTest {

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
