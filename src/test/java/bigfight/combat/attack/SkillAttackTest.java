package bigfight.combat.attack;

import bigfight.combat.fighter.Fighter;
import bigfight.combat.fighter.FighterBuilderTestUtil;
import bigfight.combat.util.CombatRandom;
import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.ui.EnUi;
import org.junit.jupiter.api.Test;

import static bigfight.model.skill.SkillFactoryTestUtil.DEFAULT_SKILL_FACTORY;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SkillAttackTest {

    @Test
    void ui_is_executed_i_attack_and_escape_example_roar() {
        double ESCAPE = 2.0;
        EnUi ui = mock(EnUi.class);
        EnUi uiSpy = spy(ui);
        Fighter fighter1 = new FighterBuilderTestUtil().build();
        Fighter fighter2 = new FighterBuilderTestUtil().build();
        SkillModel roar = DEFAULT_SKILL_FACTORY.create(SkillIdentity.ROAR);
        CombatRandom random = mock(CombatRandom.class);
        when(random.getEscapeRandom()).thenReturn(ESCAPE);
        SkillAttack test = new SkillAttack(fighter1, fighter2, roar, random, uiSpy);
        test.attack();
        verify(uiSpy, atLeastOnce()).printSkillAttack(any(), any());
        verify(uiSpy, atLeastOnce()).printSkillDodge(any(), any());
    }
}
