package bigfight.combat.fighter.components;

import bigfight.combat.fighter.Fighter;
import bigfight.combat.fighter.FighterBuilderTestUtil;
import bigfight.combat.util.CombatRandom;
import bigfight.model.skill.skills.LuckyOrNot;
import bigfight.model.skill.struct.SkillIdentity;
import org.junit.jupiter.api.Test;

import static bigfight.model.skill.SkillFactoryTestUtil.DEFAULT_SKILL_FACTORY;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class ActiveSkillListTest {

    @Test
    void selectEmpowerment_added_lucky_or_not_buff() {
        final double NO_SELECT = 1.0;
        LuckyOrNot luckyOrNot = (LuckyOrNot) DEFAULT_SKILL_FACTORY.create(SkillIdentity.LUCKY_OR_NOT);
        LuckyOrNot spy = spy(luckyOrNot);
        Fighter fighter = new FighterBuilderTestUtil()
                .withSkill(spy)
                .build();
        CombatRandom random = mock(CombatRandom.class); // no choice but to get into its mechanism
        when(random.selectUnarmed()).thenReturn(NO_SELECT);
        fighter.getCombatSelector().selectEmpowerment(random, fighter.getFighterFlag(), fighter.getBuffs());
        verify(spy).createBuff();
    }
}
