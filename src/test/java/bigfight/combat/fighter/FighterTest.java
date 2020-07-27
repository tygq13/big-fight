package bigfight.combat.fighter;

import bigfight.model.skill.SkillManager;
import bigfight.model.warrior.builder.Warrior;
import bigfight.model.warrior.component.*;
import bigfight.model.weapon.Weapon;
import bigfight.model.weapon.WeaponManager;

import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FighterTest {

    private Warrior twoWeaponWarrior() {
        ArrayList<Weapon> weaponList = new ArrayList<Weapon>();
        weaponList.add(mock(Weapon.class));
        weaponList.add(mock(Weapon.class));
        WeaponManager mockManager = mock(WeaponManager.class);
        when(mockManager.getWeaponList()).thenReturn(weaponList);

        Warrior mockWarrior = mock(Warrior.class);
        when(mockWarrior.getWeaponManager()).thenReturn(mockManager);
        when(mockWarrior.getSkillManager()).thenReturn(mock(SkillManager.class));
        return mockWarrior;
    }

    private Warrior noEmpowermentWarrior() {
        ArrayList<Weapon> weaponList = new ArrayList<Weapon>();
        WeaponManager mockManager = mock(WeaponManager.class);
        when(mockManager.getWeaponList()).thenReturn(weaponList);

        Warrior mockWarrior = mock(Warrior.class);
        when(mockWarrior.getWeaponManager()).thenReturn(mockManager);
        when(mockWarrior.getSkillManager()).thenReturn(mock(SkillManager.class));
        return mockWarrior;
    }

    @Test
    void selectEmpowerment_weapon_is_discarded_after_selected() {
        final int SELECT_WEAPON = 0;
        Warrior mockWarrior = twoWeaponWarrior();
        Random random = mock(Random.class);
        when(random.nextInt()).thenReturn(SELECT_WEAPON);

        Fighter test = new Fighter(mockWarrior);
        test.SelectEmpowerment(random);
        final int EXPECTED = 1;
        assertEquals(EXPECTED, test.getWeaponSize());
    }

    @Test
    void selectEmpowerment_successfully_return_not_null() {
        final int SELECT_WEAPON = 0;
        Warrior mockWarrior = twoWeaponWarrior();
        Random random = mock(Random.class);
        when(random.nextInt()).thenReturn(SELECT_WEAPON);

        Fighter test = new Fighter(mockWarrior);
        Empowerment empowerment = test.SelectEmpowerment(random);
        assertNotNull(empowerment.getWeapon());
    }

    @Test
    void selectEmpowerment_gives_unarmed_when_no_weapon_or_skill() {
        Warrior mockWarrior = noEmpowermentWarrior();
        Random random = mock(Random.class);
        Fighter test = new Fighter(mockWarrior);
        Empowerment empowerment = test.SelectEmpowerment(random);
        assertNull(empowerment.getWeapon());
        assertNull(empowerment.getSkill());
    }
}
