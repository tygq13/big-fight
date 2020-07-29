package bigfight.combat.attack;

import bigfight.combat.CombatTestUtil;
import bigfight.combat.fighter.FighterStatus;
import bigfight.combat.util.CombatRandom;
import bigfight.ui.EnUi;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atLeastOnce;

class CounterAttackTest {

    @Test
    void ui_is_executed_counter_attack_and_escape() {
        double COUNTER_ATTACK = -1.0;
        double NO_COUNTER_ATTACK = 1.0;
        double ESCAPE = -1.0;
        EnUi ui = mock(EnUi.class);
        EnUi uiSpy = spy(ui);
        FighterStatus fighter1 = CombatTestUtil.createSimpleFixedFighter();
        FighterStatus fighter2 = CombatTestUtil.createSimpleFixedFighter();
        CombatRandom random = mock(CombatRandom.class);
        when(random.getCounterAttackRandom()).thenReturn(COUNTER_ATTACK).thenReturn(NO_COUNTER_ATTACK);
        when(random.getCounterEscapeRandom()).thenReturn(ESCAPE);
        // use unarmed attack as the instance
        UnarmedAttack test = new UnarmedAttack(fighter1, fighter2, random, uiSpy);
        test.attack();
        verify(uiSpy, atLeastOnce()).printCounterAttackWeapon(any(), any());
    }

    @Test
    void ui_is_executed_apparent_death() {
        double NO_ESCAPE = 1.0;
        EnUi ui = mock(EnUi.class);
        EnUi uiSpy = spy(ui);
        FighterStatus fighter1 = CombatTestUtil.createSimpleFixedFighter();
        FighterStatus fighter2 = CombatTestUtil.createDyingFighterWithApparentDeath();
        CombatRandom random = mock(CombatRandom.class);
        when(random.getEscapeRandom()).thenReturn(NO_ESCAPE);
        when(random.getWeaponDamageRandom(anyInt(), anyInt())).thenReturn(fighter1.getUnarmedDamage().lower());
        // use unarmed attack as the instance
        UnarmedAttack test = new UnarmedAttack(fighter1, fighter2, random, uiSpy);
        test.attack();
        verify(uiSpy, atLeastOnce()).printSkillApparentDeath(any());
    }

}
