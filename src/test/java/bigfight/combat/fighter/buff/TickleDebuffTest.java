package bigfight.combat.fighter.buff;

import bigfight.combat.fighter.Fighter;
import bigfight.combat.fighter.FighterBuilderTestUtil;
import bigfight.model.skill.skills.Tickle;
import bigfight.model.skill.struct.SkillIdentity;
import org.junit.jupiter.api.Test;

import static bigfight.model.skill.SkillFactoryTestUtil.DEFAULT_SKILL_FACTORY;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TickleDebuffTest {

    @Test
    void tickle_debuff_invoke_effective() {
        final int DAMAGE = 10;
        Fighter fighter = new FighterBuilderTestUtil().build();
        Tickle tickle = (Tickle) DEFAULT_SKILL_FACTORY.create(SkillIdentity.TICKLE);
        fighter.addBuff(tickle.createBuff(DAMAGE));
        // test
        final int EXPECTED_HEALTH = fighter.getHealth() - DAMAGE;
        fighter.updateStatus();
        assertEquals(EXPECTED_HEALTH, fighter.getHealth());
    }

    @Test
    void tickle_debuff_clean_up() {
        final int DAMAGE = 10;
        Fighter fighter = new FighterBuilderTestUtil().build();
        Tickle tickle = (Tickle) DEFAULT_SKILL_FACTORY.create(SkillIdentity.TICKLE);
        final int ROUNDS = tickle.getMaxRounds();
        fighter.addBuff(tickle.createBuff(DAMAGE));
        for(int i = 0; i < ROUNDS; i++) {
            fighter.updateStatus();
        }
        // test
        final int EXPECTED_HEALTH = fighter.getHealth();
        fighter.updateStatus();
        assertEquals(EXPECTED_HEALTH, fighter.getHealth());
    }
}
