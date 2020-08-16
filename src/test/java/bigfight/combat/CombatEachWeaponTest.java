package bigfight.combat;

import bigfight.combat.attack.BigTypeAttack;
import bigfight.combat.fighter.Fighter;
import bigfight.combat.fighter.FighterBuilderTestUtil;
import bigfight.combat.util.CombatRandom;
import bigfight.model.warrior.component.Empowerment;
import bigfight.model.weapon.Weapon;
import bigfight.model.weapon.struct.WeaponIdentity;
import bigfight.ui.EnUi;
import bigfight.ui.Uiable;

import org.junit.jupiter.api.Test;
import static bigfight.model.weapon.WeaponFactoryTestUtil.DEFAULT_WEAPON_FACTORY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CombatEachWeaponTest {
    private static Uiable mockUi = mock(EnUi.class);

    private final double ESCAPE = 1.0;
    private final double THROW = 1.0;

    @Test
    void test_trident_rest_one_round() {
        Fighter fighter1 = new FighterBuilderTestUtil().build();
        Fighter fighter2 = new FighterBuilderTestUtil().build();
        Weapon trident = DEFAULT_WEAPON_FACTORY.create(WeaponIdentity.TRIDENT);
        CombatRandom random = mock(CombatRandom.class);

        // test
        final int EXPECTED = 1;
        // case of normal attack
        new BigTypeAttack(fighter1, fighter2, trident, random, mockUi).attack();
        int noThrowResult = fighter1.getFighterFlag().ignored;
        assertEquals(EXPECTED, noThrowResult);
        // case of throwing out the weapon
        final int EXPECTED_THROW = EXPECTED + 1;
        when(random.getThrowWeaponRandom()).thenReturn(THROW);
        new BigTypeAttack(fighter1, fighter2, trident, random, mockUi).attack();
        int throwResult = fighter1.getFighterFlag().ignored;
        assertEquals(EXPECTED_THROW, throwResult);
    }

    @Test
    void test_gas_hammer_ignore_one_round() {
        final double IGNORE = 0.0;
        Fighter fighter1 = new FighterBuilderTestUtil().build();
        Fighter fighter2 = new FighterBuilderTestUtil().build();
        Weapon gasHammer = DEFAULT_WEAPON_FACTORY.create(WeaponIdentity.GAS_HAMMER);
        CombatRandom random = mock(CombatRandom.class);
        when(random.getIgnoreRandom()).thenReturn(IGNORE);

        final int EXPECTED = 1;
        // case of normal attack
        new BigTypeAttack(fighter1, fighter2, gasHammer, random, mockUi).attack();
        int noThrowResult = fighter2.getFighterFlag().ignored;
        assertEquals(EXPECTED, noThrowResult);
        // case of throwing out the weapon
        final int EXPECTED_THROW = EXPECTED + 1;
        when(random.getThrowWeaponRandom()).thenReturn(THROW);
        new BigTypeAttack(fighter1, fighter2, gasHammer, random, mockUi).attack();
        int throwResult = fighter2.getFighterFlag().ignored;
        assertEquals(EXPECTED_THROW, throwResult);
    }

    @Test
    void test_death_scythe_unescapable() {
        Fighter fighter1 = new FighterBuilderTestUtil().build();
        // defender has high escape
        Fighter fighter2 = new FighterBuilderTestUtil().withAgility(100).build();
        Weapon demonScythe = DEFAULT_WEAPON_FACTORY.create(WeaponIdentity.DEMON_SCYTHE);
        Empowerment empowerment = new Empowerment(demonScythe);
        CombatRandom random = mock(CombatRandom.class);
        when(random.getEscapeRandom()).thenReturn(ESCAPE);
        when(random.getWeaponDamageRandom(anyInt(), anyInt())).thenReturn(demonScythe.getDamage().lower());

        final int EXPECTED = fighter2.getHealth() - demonScythe.getDamage().lower();
        new Round(fighter1, fighter2, empowerment, random, mockUi).fight();
        assertEquals(EXPECTED, fighter2.getHealth());
        when(random.getThrowWeaponRandom()).thenReturn(THROW);
        // case of throwing out the weapon
        // recover fighter2 status
        fighter2 = new FighterBuilderTestUtil().build();
        new Round(fighter1, fighter2, empowerment, random, mockUi).fight();
        assertEquals(EXPECTED, fighter2.getHealth());
    }

    @Test
    void test_judge_pencil_unescapable() {
        Fighter fighter1 = new FighterBuilderTestUtil().build();
        Fighter fighter2 = new FighterBuilderTestUtil().withAgility(100).build();
        Weapon judgePencil = DEFAULT_WEAPON_FACTORY.create(WeaponIdentity.JUDGE_PENCIL);
        Empowerment empowerment = new Empowerment(judgePencil);
        CombatRandom random = mock(CombatRandom.class);
        when(random.getEscapeRandom()).thenReturn(ESCAPE);
        when(random.getWeaponDamageRandom(anyInt(), anyInt())).thenReturn(judgePencil.getDamage().lower());

        final int EXPECTED = fighter2.getHealth() - judgePencil.getDamage().lower();
        // case of normal attack
        new Round(fighter1, fighter2, empowerment, random, mockUi).fight();
        assertEquals(EXPECTED, fighter2.getHealth());
        when(random.getThrowWeaponRandom()).thenReturn(THROW);
        // case of throwing out the weapon
        // recover fighter2 status
        fighter2 = new FighterBuilderTestUtil().withAgility(100).build();
        new Round(fighter1, fighter2, empowerment, random, mockUi).fight();
        assertEquals(EXPECTED, fighter2.getHealth());
    }

}
