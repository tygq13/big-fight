package bigfight.combat.attack;

import bigfight.combat.CombatTestUtil;
import bigfight.combat.fighter.Fighter;
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
    void ui_is_executed_in_roar_attack_and_escape() {
        double ESCAPE = -1.0;
        EnUi ui = mock(EnUi.class);
        EnUi uiSpy = spy(ui);
        Fighter fighter1 = CombatTestUtil.createSimpleFixedFighter();
        Fighter fighter2 = CombatTestUtil.createSimpleFixedFighter();
        SkillModel roar = DEFAULT_SKILL_FACTORY.create(SkillIdentity.ROAR);
        CombatRandom random = mock(CombatRandom.class);
        when(random.getEscapeRandom()).thenReturn(ESCAPE);
        SkillAttack test = new SkillAttack(fighter1, fighter2, roar, random, uiSpy);
        test.attack();
        verify(uiSpy, atLeastOnce()).printSkillRoarAttack(any());
        verify(uiSpy, atLeastOnce()).printSkillRoarDodge(any());
    }
}
