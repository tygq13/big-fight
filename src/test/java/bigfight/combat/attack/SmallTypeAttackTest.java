package bigfight.combat.attack;

import bigfight.combat.CombatTestUtil;
import bigfight.combat.fighter.Fighter;
import bigfight.combat.fighter.FighterBuilderTestUtil;
import bigfight.combat.util.CombatRandom;
import bigfight.model.weapon.Weapon;
import bigfight.ui.EnUi;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SmallTypeAttackTest {

    @Test
    void ui_is_executed_attack_and_escape() {
        double ESCAPE = -1.0;
        EnUi ui = mock(EnUi.class);
        EnUi uiSpy = spy(ui);
        Fighter fighter1 = new FighterBuilderTestUtil().build();
        Fighter fighter2 = new FighterBuilderTestUtil().build();
        Weapon weapon = CombatTestUtil.createUsableWeapon();
        CombatRandom random = mock(CombatRandom.class);
        when(random.getEscapeRandom()).thenReturn(ESCAPE);
        SmallTypeAttack test = new SmallTypeAttack(fighter1, fighter2, weapon, random, uiSpy);
        test.attack();
        verify(uiSpy, atLeastOnce()).printWeaponSmallAttack(any(), any());
        verify(uiSpy, atLeastOnce()).printDodge(any());
    }
}
