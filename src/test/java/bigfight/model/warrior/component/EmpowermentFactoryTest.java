package bigfight.model.warrior.component;

import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.weapon.Weapon;

import java.util.ArrayList;
import java.util.Map;

import bigfight.model.weapon.WeaponFactory;
import bigfight.model.weapon.struct.WeaponIdentity;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class EmpowermentFactoryTest {

    private ArrayList createFullSizeWeaponList() {
        final int FULL_WEAPON_SIZE = WeaponIdentity.getSize();
        ArrayList weaponList = mock(ArrayList.class);
        when(weaponList.size()).thenReturn(FULL_WEAPON_SIZE);
        return weaponList;
    }

    private Map createFullSizeSkillSet() {
        final int FULL_SKILL_SIZE= SkillIdentity.getSize();
        Map skillSet = mock(Map.class);
        when(skillSet.size()).thenReturn(FULL_SKILL_SIZE);
        return skillSet;
    }

    private ArrayList<Weapon> createFullExceptOneWeaponList(int index) {
        ArrayList<Weapon> weaponList = new ArrayList<>();
        for(int i = 0; i < WeaponIdentity.getSize(); i++) {
            if (i != index) {
                Weapon dummy = mock(Weapon.class);
                when(dummy.getIdentity()).thenReturn(WeaponIdentity.at(i));
                weaponList.add(dummy);
            }
        }
        return weaponList;
    }

    @Test
    void randomGetNew_return_default_empowerment_when_full() {
        // create weapon list and skill set of full size
        ArrayList weaponList = createFullSizeWeaponList();
        Map skillSet = createFullSizeSkillSet();

        EmpowermentFactory test = new EmpowermentFactory(null, null);
        Empowerment result = test.randomGetNew(weaponList, skillSet);
        assertNull(result);
    }

    @Test
    void randomGetNew_correctly_gets_the_only_possible_weapon() {
        Map skillSet = createFullSizeSkillSet();
        final int INTENDED_WEAPON_INDEX = 0;
        ArrayList<Weapon> weaponList = createFullExceptOneWeaponList(INTENDED_WEAPON_INDEX);

        WeaponIdentity expected = WeaponIdentity.at(INTENDED_WEAPON_INDEX);

        // construct test empowerment factory
        WeaponFactory mockWeaponFactory = mock(WeaponFactory.class);
        WeaponFactory weaponFactorySpy = spy(mockWeaponFactory);
        EmpowermentFactory test = new EmpowermentFactory(weaponFactorySpy, null);
        test.randomGetNew(weaponList, skillSet);
        // verify that the correct weapon construction is called
        verify(weaponFactorySpy, times(1)).create(expected);
    }
}
