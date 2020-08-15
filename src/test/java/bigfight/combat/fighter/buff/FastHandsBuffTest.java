package bigfight.combat.fighter.buff;

import bigfight.combat.fighter.Fighter;
import bigfight.combat.fighter.FighterBuilderTestUtil;
import bigfight.model.skill.skills.special.FastHands;
import bigfight.model.skill.struct.SkillIdentity;
import org.junit.jupiter.api.Test;

import static bigfight.model.skill.SkillFactoryTestUtil.DEFAULT_SKILL_FACTORY;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FastHandsBuffTest {
    @Test
    void fast_hands_buff_invoke_effective() {
        Fighter fighter = new FighterBuilderTestUtil().build();
        FastHands fastHands = (FastHands) DEFAULT_SKILL_FACTORY.create(SkillIdentity.FAST_HANDS);
        final double EXPECTED = fighter.getAdvancedAttribute().doubleHitChance + fastHands.getDoubleHit();
        fighter.addBuff(fastHands.createBuff());
        fighter.updateStatus();
        assertEquals(EXPECTED, fighter.getAdvancedAttribute().doubleHitChance);
    }

    @Test
    void fast_hands_buff_clean_up() {
        Fighter fighter = new FighterBuilderTestUtil().build();
        FastHands fastHands = (FastHands) DEFAULT_SKILL_FACTORY.create(SkillIdentity.FAST_HANDS);
        final double EXPECTED = fighter.getAdvancedAttribute().doubleHitChance;
        fighter.addBuff(fastHands.createBuff());
        fighter.updateStatus();
        fighter.updateStatus(); // clean up invocation
        assertEquals(EXPECTED, fighter.getAdvancedAttribute().doubleHitChance);
    }
}
