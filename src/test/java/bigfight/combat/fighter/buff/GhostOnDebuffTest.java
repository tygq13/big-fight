package bigfight.combat.fighter.buff;

import bigfight.combat.fighter.Fighter;
import bigfight.combat.fighter.FighterBuilderTestUtil;
import bigfight.model.skill.skills.GhostOn;
import bigfight.model.skill.skills.Tickle;
import bigfight.model.skill.struct.SkillIdentity;
import org.junit.jupiter.api.Test;

import static bigfight.model.skill.SkillFactoryTestUtil.DEFAULT_SKILL_FACTORY;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GhostOnDebuffTest {

    @Test
    void ghost_one_debuff_invoke_effective() {
        Fighter fighter = new FighterBuilderTestUtil().build();
        GhostOn ghost_one = (GhostOn) DEFAULT_SKILL_FACTORY.create(SkillIdentity.GHOST_ON);
        fighter.addBuff(ghost_one.createDebuff());
        // test
        final double EXPECTED = 0 - ghost_one.getReduction();
        fighter.newRoundUpdate();
        assertEquals(EXPECTED, fighter.getAdvancedAttribute().antiBigExtraPercentageDamage);
        assertEquals(EXPECTED, fighter.getAdvancedAttribute().antiSmallExtraPercentageDamage);
        assertEquals(EXPECTED, fighter.getAdvancedAttribute().antiMediumExtraPercentageDamage);
        assertEquals(EXPECTED, fighter.getAdvancedAttribute().antiThrowExtraPercentageDamage);
        assertEquals(EXPECTED, fighter.getAdvancedAttribute().antiUnarmedExtraPercentageDamage);
        assertEquals(EXPECTED, fighter.getAdvancedAttribute().antiSkillExtraPercentageDamage);
        assertEquals(EXPECTED, fighter.getAdvancedAttribute().bigEvasionRate);
        assertEquals(EXPECTED, fighter.getAdvancedAttribute().mediumEvasionRate);
        assertEquals(EXPECTED, fighter.getAdvancedAttribute().smallEvasionRate);
        assertEquals(EXPECTED, fighter.getAdvancedAttribute().throwEvasionRate);
        assertEquals(EXPECTED, fighter.getAdvancedAttribute().unarmedEvasionRate);
        assertEquals(EXPECTED, fighter.getAdvancedAttribute().skillEvasionRate);
    }

    @Test
    void ghost_one_debuff_clean_up() {
        Fighter fighter = new FighterBuilderTestUtil().build();
        GhostOn ghost_one = (GhostOn) DEFAULT_SKILL_FACTORY.create(SkillIdentity.GHOST_ON);
        fighter.addBuff(ghost_one.createDebuff());
        for (int i = 0; i < ghost_one.getRounds(); i++) {
            fighter.newRoundUpdate();
        }
        // test
        final double EXPECTED = 0;
        fighter.newRoundUpdate();
        assertEquals(EXPECTED, fighter.getAdvancedAttribute().antiBigExtraPercentageDamage);
        assertEquals(EXPECTED, fighter.getAdvancedAttribute().antiSmallExtraPercentageDamage);
        assertEquals(EXPECTED, fighter.getAdvancedAttribute().antiMediumExtraPercentageDamage);
        assertEquals(EXPECTED, fighter.getAdvancedAttribute().antiThrowExtraPercentageDamage);
        assertEquals(EXPECTED, fighter.getAdvancedAttribute().antiUnarmedExtraPercentageDamage);
        assertEquals(EXPECTED, fighter.getAdvancedAttribute().antiSkillExtraPercentageDamage);
        assertEquals(EXPECTED, fighter.getAdvancedAttribute().bigEvasionRate);
        assertEquals(EXPECTED, fighter.getAdvancedAttribute().mediumEvasionRate);
        assertEquals(EXPECTED, fighter.getAdvancedAttribute().smallEvasionRate);
        assertEquals(EXPECTED, fighter.getAdvancedAttribute().throwEvasionRate);
        assertEquals(EXPECTED, fighter.getAdvancedAttribute().unarmedEvasionRate);
        assertEquals(EXPECTED, fighter.getAdvancedAttribute().skillEvasionRate);
    }
}
