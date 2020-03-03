package bigfight.model.weapon;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WeaponManagerTest {
    @Test
    void initialize_weaponList() {
        WeaponManager weaponManager = new WeaponManager();
        assertNotNull(weaponManager.getWeaponList());
    }

    @Test
    void get_size() {
        WeaponManager weaponManager = new WeaponManager();
        Weapon dummy = mock(Weapon.class);
        weaponManager.add(dummy);
        weaponManager.add(dummy);
        assertEquals(2, weaponManager.getSize());
    }
}
