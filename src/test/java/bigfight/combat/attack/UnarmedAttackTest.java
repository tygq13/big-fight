package bigfight.combat.attack;

import bigfight.combat.fighter.Fighter;
import bigfight.combat.fighter.FighterBuilderTestUtil;
import bigfight.combat.util.CombatRandom;
import bigfight.ui.EnUi;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UnarmedAttackTest {

    @Test
    void ui_is_executed_attack_and_escape() {
        double ESCAPE = 2.0;
        EnUi ui = mock(EnUi.class);
        EnUi uiSpy = spy(ui);
        Fighter fighter1 = new FighterBuilderTestUtil().build();
        Fighter fighter2 = new FighterBuilderTestUtil().build();
        CombatRandom random = mock(CombatRandom.class);
        when(random.getEscapeRandom()).thenReturn(ESCAPE);
        UnarmedAttack test = new UnarmedAttack(fighter1, fighter2, random, uiSpy);
        test.attack();
        verify(uiSpy, atLeastOnce()).printUnarmedAttack(any());
        verify(uiSpy, atLeastOnce()).printDodge(any());
    }
}
