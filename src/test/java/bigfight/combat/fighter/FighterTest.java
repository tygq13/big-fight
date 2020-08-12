package bigfight.combat.fighter;

import bigfight.model.warrior.component.*;
import bigfight.model.weapon.Weapon;

import bigfight.combat.util.CombatRandom;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FighterTest {
    private static final double NOT_UNARMED = 1;
    private static final int SELECT_WEAPON = 0;

    private FightableWarrior twoWeaponWarrior() {
        DisposableWeaponList weaponList = new DisposableWeaponList();
        weaponList.add(mock(Weapon.class));
        weaponList.add(mock(Weapon.class));

        FightableWarrior mockWarrior = mock(FightableWarrior.class);
        when(mockWarrior.getDisposableWeapons()).thenReturn(weaponList);
        when(mockWarrior.getSpecialSkills()).thenReturn(mock(SpecialSkillList.class));
        when(mockWarrior.getActiveSkills()).thenReturn(mock(ActiveSkillList.class));
        return mockWarrior;
    }

    private FightableWarrior noEmpowermentWarrior() {
        FightableWarrior mockWarrior = mock(FightableWarrior.class);
        when(mockWarrior.getDisposableWeapons()).thenReturn(mock(DisposableWeaponList.class));
        when(mockWarrior.getSpecialSkills()).thenReturn(mock(SpecialSkillList.class));
        when(mockWarrior.getActiveSkills()).thenReturn(mock(ActiveSkillList.class));
        return mockWarrior;
    }

    @Test
    void selectEmpowerment_weapon_is_discarded_after_selected() {
        FightableWarrior mockWarrior = twoWeaponWarrior();
        CombatRandom random = mock(CombatRandom.class);
        when(random.selectUnarmed()).thenReturn(NOT_UNARMED);
        when(random.selectWeaponOrSkill(anyInt())).thenReturn(SELECT_WEAPON);

        Fighter test = new Fighter(mockWarrior);
        test.selectEmpowerment(random);
        final int EXPECTED = 1;
        assertEquals(EXPECTED, test.getWeaponSize());
    }

    @Test
    void selectEmpowerment_successfully_return_not_null() {
        FightableWarrior mockWarrior = twoWeaponWarrior();
        CombatRandom random = mock(CombatRandom.class);
        when(random.selectUnarmed()).thenReturn(NOT_UNARMED);
        when(random.selectWeaponOrSkill(anyInt())).thenReturn(SELECT_WEAPON);

        Fighter test = new Fighter(mockWarrior);
        Empowerment empowerment = test.selectEmpowerment(random);
        assertNotNull(empowerment.getWeapon());
    }

    @Test
    void selectEmpowerment_gives_unarmed_when_no_weapon_or_skill() {
        FightableWarrior mockWarrior = noEmpowermentWarrior();
        CombatRandom random = mock(CombatRandom.class);
        Fighter test = new Fighter(mockWarrior);
        Empowerment empowerment = test.selectEmpowerment(random);
        assertNull(empowerment.getWeapon());
        assertNull(empowerment.getSkill());
    }

    @Test
    void update_health_not_exceed_maximum() {
        Fighter fighter = new FighterBuilderTestUtil().build();
        final int EXPECTED = fighter.getHealth();
        fighter.updateHealth(fighter.getHealth() + 1);
        assertEquals(EXPECTED, fighter.getHealth());
    }
}
