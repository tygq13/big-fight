package bigfight.model.weapon;

import bigfight.model.weapon.WeaponData.WeaponType;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WeaponListTest {
    @Test
    void initialize_weaponList() {
        WeaponList weaponList = new WeaponList();
        assertNotNull(weaponList.getWeaponList());
    }

    @Test
    void get_size() {
        WeaponList weaponList = new WeaponList();
        Weapon dummy = mock(Weapon.class);
        weaponList.add(dummy);
        weaponList.add(dummy);
        assertEquals(2, weaponList.getSize());
    }
}
