package bigfight.combat.fighter.buff;

import bigfight.combat.fighter.Fighter;
import bigfight.combat.fighter.FighterBuilderTestUtil;
import bigfight.model.skill.skills.LuckyOrNot;
import bigfight.model.skill.struct.SkillIdentity;
import org.junit.jupiter.api.Test;

import static bigfight.model.skill.SkillFactoryTestUtil.DEFAULT_SKILL_FACTORY;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LuckyOrNotBuffTest {
    @Test
    void lucky_or_not_buff_invoke_effective() {
        Fighter fighter = new FighterBuilderTestUtil().build();
        LuckyOrNot luckyOrNot = (LuckyOrNot) DEFAULT_SKILL_FACTORY.create(SkillIdentity.LUCKY_OR_NOT);
        final double EXPECTED = fighter.getAdvancedAttribute().skillHitRate + luckyOrNot.getExtraHitRate();
        fighter.addBuff(luckyOrNot.createBuff());
        fighter.updateStatus();
        assertEquals(EXPECTED, fighter.getAdvancedAttribute().skillHitRate);
    }

    @Test
    void lucky_or_not_buff_clean_up() {
        Fighter fighter = new FighterBuilderTestUtil().build();
        LuckyOrNot luckyOrNot = (LuckyOrNot) DEFAULT_SKILL_FACTORY.create(SkillIdentity.LUCKY_OR_NOT);
        final double EXPECTED = fighter.getAdvancedAttribute().skillHitRate;
        fighter.addBuff(luckyOrNot.createBuff());
        fighter.updateStatus();
        fighter.updateStatus(); // clean up invocation
        assertEquals(EXPECTED, fighter.getAdvancedAttribute().skillHitRate);
    }
}
