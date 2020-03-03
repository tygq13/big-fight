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

    @Test
    void randomGetNew_return_default_empowerment_when_full() {
        // create weapon list and skill set of full size
        final int FULL_WEAPON_SIZE = WeaponIdentity.getSize();
        ArrayList weaponList = mock(ArrayList.class);
        when(weaponList.size()).thenReturn(FULL_WEAPON_SIZE);
        final int FULL_SKILL_SIZE= SkillIdentity.getSize();
        Map skillSet = mock(Map.class);
        when(skillSet.size()).thenReturn(FULL_SKILL_SIZE);

        EmpowermentFactory test = new EmpowermentFactory(null, null);
        Empowerment result = test.randomGetNew(weaponList, skillSet);
        assertNull(result);
    }

    @Test
    void randomGetNew_correctly_gets_the_only_possible_weapon() {
        // construct a full skill set
        final int FULL_SKILL_SIZE= SkillIdentity.getSize();
        Map skillSet = mock(Map.class);
        when(skillSet.size()).thenReturn(FULL_SKILL_SIZE);

        // construct a full weapon set except the first WeaponIdentity, hopefully O(n)
        final int INTENDED_WEAPON_INDEX = 0;
        ArrayList<Weapon> weaponList = new ArrayList<>();
        for(int i = INTENDED_WEAPON_INDEX + 1; i < WeaponIdentity.getSize(); i++) {
            Weapon dummy = mock(Weapon.class);
            when(dummy.getIdentity()).thenReturn(WeaponIdentity.at(i));
            weaponList.add(dummy);
        }
        WeaponIdentity expected = WeaponIdentity.at(INTENDED_WEAPON_INDEX);

        // construct test empowerment factory
        WeaponFactory mockWeaponFactory = mock(WeaponFactory.class);
        WeaponFactory spy = spy(mockWeaponFactory);
        EmpowermentFactory test = new EmpowermentFactory(spy, null);
        test.randomGetNew(weaponList, skillSet);

        // verify that the correct weapon construction is called
        verify(spy, times(1)).create(expected);
    }
}
