package bigfight.combat;

import bigfight.combat.fighter.FighterStatus;
import bigfight.combat.util.CombatRandom;
import bigfight.model.warrior.component.Empowerment;
import bigfight.model.weapon.Weapon;
import bigfight.model.weapon.WeaponFactory;
import bigfight.model.weapon.struct.WeaponIdentity;
import bigfight.ui.EnUi;
import bigfight.ui.Uiable;

import org.junit.jupiter.api.Test;
import static bigfight.model.weapon.WeaponFactoryUtil.DEFAULT_WEAPON_FACTORY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CombatEachWeaponTest {
    private static Uiable mockUi = mock(EnUi.class);

    private final double NO_ESCAPE = 1.0;
    private final double ESCAPE = 0.0;
    private final double NO_THROW = 1.0;
    private final double THROW = 0.0;

    @Test
    void test_trident_rest_one_round() {
        FighterStatus fighter1 = CombatTestUtil.createSimpleFixedFighter();
        FighterStatus fighter2 = CombatTestUtil.createSimpleFixedFighter();
        Weapon trident = DEFAULT_WEAPON_FACTORY.create(WeaponIdentity.TRIDENT);
        Empowerment empowerment = new Empowerment(trident);
        CombatRandom random = mock(CombatRandom.class);
        when(random.getEscapeRandom()).thenReturn(NO_ESCAPE);
        when(random.getThrowWeaponRandom()).thenReturn(NO_THROW);

        // test
        final int EXPECTED = -1;
        // case of normal attack
        int noThrowResult = new Round(fighter1, fighter2, empowerment, random, mockUi).fight();
        assertEquals(EXPECTED, noThrowResult);
        // case of throwing out the weapon
        when(random.getThrowWeaponRandom()).thenReturn(THROW);
        int throwResult = new Round(fighter1, fighter2, empowerment, random, mockUi).fight();
        assertEquals(EXPECTED, throwResult);
    }

    @Test
    void test_gas_hammer_ignore_one_round() {
        final double IGNORE = 0.0;
        FighterStatus fighter1 = CombatTestUtil.createSimpleFixedFighter();
        FighterStatus fighter2 = CombatTestUtil.createSimpleFixedFighter();
        Weapon gasHammer = DEFAULT_WEAPON_FACTORY.create(WeaponIdentity.GAS_HAMMER);
        Empowerment empowerment = new Empowerment(gasHammer);
        CombatRandom random = mock(CombatRandom.class);
        when(random.getEscapeRandom()).thenReturn(NO_ESCAPE);
        when(random.getThrowWeaponRandom()).thenReturn(NO_THROW);
        when(random.getIgnoreRandom()).thenReturn(IGNORE);

        final int EXPECTED = 1;
        // case of normal attack
        int noThrowResult = new Round(fighter1, fighter2, empowerment, random, mockUi).fight();
        assertEquals(EXPECTED, noThrowResult);
        // case of throwing out the weapon
        when(random.getThrowWeaponRandom()).thenReturn(THROW);
        int throwResult = new Round(fighter1, fighter2, empowerment, random, mockUi).fight();
        assertEquals(EXPECTED, throwResult);
    }

    @Test
    void test_death_scythe_unescapable() {
        FighterStatus fighter1 = CombatTestUtil.createSimpleFixedFighter();
        // defender has high escape
        FighterStatus fighter2 = CombatTestUtil.createCustomFighter(5,100,5, 100, 10);
        Weapon demonScythe = DEFAULT_WEAPON_FACTORY.create(WeaponIdentity.DEMON_SCYTHE);
        Empowerment empowerment = new Empowerment(demonScythe);
        CombatRandom random = mock(CombatRandom.class);
        when(random.getEscapeRandom()).thenReturn(ESCAPE);
        when(random.getThrowWeaponRandom()).thenReturn(NO_THROW);
        when(random.getWeaponDamageRandom(anyInt(), anyInt())).thenReturn(demonScythe.getDamage().lower());

        final int EXPECTED = fighter2.getHealth() - demonScythe.getDamage().lower();
        new Round(fighter1, fighter2, empowerment, random, mockUi).fight();
        assertEquals(EXPECTED, fighter2.getHealth());
        when(random.getThrowWeaponRandom()).thenReturn(THROW);
        // case of throwing out the weapon
        // recover fighter2 status
        fighter2 = CombatTestUtil.createSimpleFixedFighter();
        new Round(fighter1, fighter2, empowerment, random, mockUi).fight();
        assertEquals(EXPECTED, fighter2.getHealth());
    }

    @Test
    void test_judge_pencil_unescapable() {
        FighterStatus fighter1 = CombatTestUtil.createSimpleFixedFighter();
        FighterStatus fighter2 = CombatTestUtil.createCustomFighter(5,100,5, 100, 10);
        Weapon judgePencil = DEFAULT_WEAPON_FACTORY.create(WeaponIdentity.JUDGE_PENCIL);
        Empowerment empowerment = new Empowerment(judgePencil);
        CombatRandom random = mock(CombatRandom.class);
        when(random.getEscapeRandom()).thenReturn(ESCAPE);
        when(random.getThrowWeaponRandom()).thenReturn(NO_THROW);
        when(random.getWeaponDamageRandom(anyInt(), anyInt())).thenReturn(judgePencil.getDamage().lower());

        final int EXPECTED = fighter2.getHealth() - judgePencil.getDamage().lower();
        // case of normal attack
        new Round(fighter1, fighter2, empowerment, random, mockUi).fight();
        assertEquals(EXPECTED, fighter2.getHealth());
        when(random.getThrowWeaponRandom()).thenReturn(THROW);
        // case of throwing out the weapon
        // recover fighter2 status
        fighter2 = CombatTestUtil.createCustomFighter(5,100,5, 100, 10);
        new Round(fighter1, fighter2, empowerment, random, mockUi).fight();
        assertEquals(EXPECTED, fighter2.getHealth());
    }

}
