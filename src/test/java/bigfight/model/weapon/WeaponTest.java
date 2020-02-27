package bigfight.model.weapon;

import bigfight.model.weapon.WeaponData.WeaponType;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WeaponTest {

    // this is to test weapon's damage field can be initialized and accessed
    @Test
    void weapon_damage_initialized_and_accessed() {
        final int DAMAGE = 3;
        Weapon weapon = new Weapon(any(String.class), DAMAGE, any(WeaponType.class), any(String.class));
        assertEquals(DAMAGE, weapon.getDamage());
    }

    @Test
    void weapon_name_initialized_and_accessed() {
        final String NAME = "test";
        Weapon weapon = new Weapon(NAME, anyInt(), any(WeaponType.class), any(String.class));
        assertEquals(NAME, weapon.getName());
    }

    @Test
    void weapon_get_type_and_accessed() {
        final WeaponType testType = WeaponType.SMALL;
        Weapon weapon = new Weapon(any(String.class), anyInt(), testType, any(String.class));
        assertEquals(testType, weapon.getType());
    }

    @Test
    void weapon_get_description_and_accessed() {
        final String DESCRIPTION = "test";
        Weapon weapon = new Weapon(any(String.class), anyInt(), any(WeaponType.class), DESCRIPTION);
        assertEquals(DESCRIPTION, weapon.getDescription());
    }
}
