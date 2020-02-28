package bigfight.model.weapon;

import bigfight.model.weapon.struct.WeaponIdentity;
import bigfight.model.weapon.struct.WeaponStruct;
import bigfight.model.weapon.struct.WeaponStructArray;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WeaponDataTest {

    @Test
    void test_weapon_struct_accessible_with_example_trident_one() {
        WeaponStruct test = WeaponData.TRIDENT_ONE;
        assertNotNull(test.name);
        assertNotNull(test.description);
        assertNotNull(test.identity);
        assertNotNull(test.type);
    }

    @Test
    void test_weapon_struct_array_accessible_with_example_trident() {
        WeaponStructArray test = WeaponData.TRIDENT_ARRAY;
        assertNotNull(test.withStar(1));
    }

    @Test
    void test_weapon_map_accessible_with_example_trident() {
        WeaponIdentity identity = WeaponIdentity.TRIDENT;
        WeaponStructArray tridentArray = WeaponData.ARSENAL.get(identity);
        assertNotNull(tridentArray);
    }

    @Test
    void test_all_weapon_mappable_by_identity() {
        // iterate through all weapon identities
        for (WeaponIdentity identity : WeaponIdentity.values()) {
            assertNotNull(WeaponData.ARSENAL.get(identity));
        }
    }
}
