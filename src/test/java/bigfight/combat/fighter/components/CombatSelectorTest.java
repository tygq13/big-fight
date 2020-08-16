package bigfight.combat.fighter.components;

import bigfight.combat.fighter.FightableWarrior;
import bigfight.combat.fighter.Fighter;
import bigfight.combat.util.CombatRandom;
import bigfight.model.warrior.component.Empowerment;
import bigfight.model.weapon.Weapon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CombatSelectorTest {
    private final double NOT_UNARMED = 1;
    private final int SELECT_WEAPON = 0;

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
        test.getCombatSelector().selectEmpowerment(random, test.getFighterFlag());
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
        Empowerment empowerment =test.getCombatSelector().selectEmpowerment(random, test.getFighterFlag());;
        assertNotNull(empowerment.getWeapon());
    }

    @Test
    void selectEmpowerment_gives_unarmed_when_no_weapon_or_skill() {
        FightableWarrior mockWarrior = noEmpowermentWarrior();
        CombatRandom random = mock(CombatRandom.class);
        Fighter test = new Fighter(mockWarrior);
        Empowerment empowerment = test.getCombatSelector().selectEmpowerment(random, test.getFighterFlag());;
        assertNull(empowerment.getWeapon());
        assertNull(empowerment.getSkill());
    }
}
