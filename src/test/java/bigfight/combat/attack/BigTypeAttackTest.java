package bigfight.combat.attack;

import bigfight.combat.CombatTestUtil;
import bigfight.combat.fighter.Fighter;
import bigfight.combat.util.CombatRandom;
import bigfight.model.weapon.Weapon;
import bigfight.ui.EnUi;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class BigTypeAttackTest {

    @Test
    void ui_is_executed_attack_and_escape() {
        double ESCAPE = -1.0;
        EnUi ui = mock(EnUi.class);
        EnUi uiSpy = spy(ui);
        Fighter fighter1 = CombatTestUtil.createSimpleFixedFighter();
        Fighter fighter2 = CombatTestUtil.createSimpleFixedFighter();
        Weapon weapon = CombatTestUtil.createUsableWeapon();
        CombatRandom random = mock(CombatRandom.class);
        when(random.getEscapeRandom()).thenReturn(ESCAPE);
        BigTypeAttack test = new BigTypeAttack(fighter1, fighter2, weapon, random, uiSpy);
        test.attack();
        verify(uiSpy, atLeastOnce()).printWeaponBigAttack(any(), any());
        verify(uiSpy, atLeastOnce()).printDodge(any());
    }
}
