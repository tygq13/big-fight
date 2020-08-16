package bigfight.combat.attack;

import bigfight.combat.CombatTestUtil;
import bigfight.combat.fighter.Fighter;
import bigfight.combat.fighter.FighterBuilderTestUtil;
import bigfight.combat.util.CombatRandom;
import bigfight.model.weapon.Weapon;
import bigfight.ui.EnUi;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ThrowTypeAttackTest {

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
        ThrowTypeAttack test = new ThrowTypeAttack(fighter1, fighter2, weapon, random, uiSpy);
        test.attack();
        verify(uiSpy, atLeastOnce()).printWeaponThrowAttack(any(), any());
        verify(uiSpy, atLeastOnce()).printDodge(any());
    }

    @Test
    void throw_attack_damage_addition_by_agility() {
        final double NO_ESCAPE = 1.0;
        final int AGILITY1 = 100;
        final int AGILITY2 = 50;
        final int DAMAGE = 10;
        Fighter fighter1 = new FighterBuilderTestUtil().withAgility(AGILITY1).build();
        Fighter fighter2 = new FighterBuilderTestUtil().withAgility(AGILITY2).build();
        final int ADDITION = AGILITY1 - AGILITY2;
        CombatRandom random = mock(CombatRandom.class);
        when(random.getEscapeRandom()).thenReturn(NO_ESCAPE);
        when(random.getWeaponDamageRandom(anyInt(), anyInt())).thenReturn(DAMAGE);
        int EXPECTED_HEALTH = fighter2.getHealth() - (DAMAGE + ADDITION) * 2;

        new ThrowTypeAttack(fighter1, fighter2, CombatTestUtil.createThrowWeapon(), random, mock(EnUi.class)).attack();
        assertEquals(EXPECTED_HEALTH, fighter2.getHealth());
    }
}
