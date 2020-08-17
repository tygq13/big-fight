package bigfight.combat.attack;

import bigfight.combat.CombatTestUtil;
import bigfight.combat.fighter.Fighter;
import bigfight.combat.fighter.FighterBuilderTestUtil;
import bigfight.combat.util.CombatRandom;
import bigfight.model.weapon.Weapon;
import bigfight.ui.EnUi;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class BigTypeAttackTest {

    @Test
    void ui_is_executed_attack_and_escape() {
        double ESCAPE = 2.0;
        EnUi ui = mock(EnUi.class);
        EnUi uiSpy = spy(ui);
        Fighter fighter1 = new FighterBuilderTestUtil().build();
        Fighter fighter2 = new FighterBuilderTestUtil().build();
        Weapon weapon = CombatTestUtil.createUsableWeapon();
        CombatRandom random = mock(CombatRandom.class);
        when(random.getEscapeRandom()).thenReturn(ESCAPE);
        BigTypeAttack test = new BigTypeAttack(fighter1, fighter2, weapon, random, uiSpy);
        test.attack();
        verify(uiSpy, atLeastOnce()).printWeaponBigAttack(any(), any());
        verify(uiSpy, atLeastOnce()).printDodge(any());
    }

    @Test
    void big_attack_damage_addition_by_strength() {
        final int STRENGTH1 = 100;
        final int STRENGTH2 = 50;
        final int DAMAGE = 10;
        Fighter fighter1 = new FighterBuilderTestUtil().withStrength(STRENGTH1).build();
        Fighter fighter2 = new FighterBuilderTestUtil().withStrength(STRENGTH2).build();
        final int ADDITION = STRENGTH1 - STRENGTH2;
        CombatRandom random = mock(CombatRandom.class);
        when(random.getWeaponDamageRandom(anyInt(), anyInt())).thenReturn(DAMAGE);
        int EXPECTED_HEALTH = fighter2.getHealth() - (DAMAGE + ADDITION);

        new BigTypeAttack(fighter1, fighter2, CombatTestUtil.createBigWeapon(), random, mock(EnUi.class)).attack();
        assertEquals(EXPECTED_HEALTH, fighter2.getHealth());
    }
}
