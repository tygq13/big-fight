package bigfight.model.weapon;

import bigfight.model.weapon.struct.WeaponIdentity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WeaponDataTest {

    @Test
    void test_all_weapon_identity_mappable() {
        // iterate through all weapon identities
        WeaponData weaponData = new WeaponData();
        for (WeaponIdentity identity : WeaponIdentity.values()) {
            assertNotNull(weaponData.get(identity));
        }
    }
}
