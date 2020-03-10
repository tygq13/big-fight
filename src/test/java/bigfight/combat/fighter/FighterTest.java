package bigfight.combat.fighter;

import bigfight.model.skill.SkillManager;
import bigfight.model.warrior.Warrior;
import bigfight.model.warrior.component.Empowerment;
import bigfight.model.weapon.Weapon;
import bigfight.model.weapon.WeaponManager;

import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FighterTest {

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

    @Test
    void selectEmpowerment_weapon_is_discarded_after_selected() {
        Warrior mockWarrior = twoWeaponWarrior();
        // next random number to be zero so that a weapon will be selected
        Random random = mock(Random.class);
        when(random.nextInt()).thenReturn(0);

        Fighter test = new Fighter(mockWarrior);
        test.SelectEmpowerment(random);
        final int EXPECTED = 1;
        assertEquals(EXPECTED, test.getWeaponSize());
    }

    @Test
    void selectEmpowerment_successfully_return_not_null() {
        Warrior mockWarrior = twoWeaponWarrior();
        Random random = mock(Random.class);
        when(random.nextInt()).thenReturn(0);

        Fighter test = new Fighter(mockWarrior);
        Empowerment empowerment = test.SelectEmpowerment(random);
        assertNotNull(empowerment);
    }

    //todo: test selectEmpwerment has good randomisation
}
