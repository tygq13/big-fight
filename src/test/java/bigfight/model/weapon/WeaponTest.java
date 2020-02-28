package bigfight.model.weapon;

import bigfight.model.weapon.struct.WeaponIdentity;
import bigfight.model.weapon.struct.WeaponType;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WeaponTest {

    // this is to test weapon's damage field can be initialized and accessed
    @Test
    void weapon_initialized() {
        WeaponIdentity identity = WeaponIdentity.TRIDENT;
        Weapon result = new Weapon(new WeaponModel(identity));
        assertNotNull(result.getDamage());
        assertNotNull(result.getName());
        assertNotNull(result.getDescription());
        assertNotNull(result.getIdentity());
        assertNotNull(result.getType());
    }
}
