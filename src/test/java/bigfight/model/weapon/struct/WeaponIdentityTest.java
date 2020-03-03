package bigfight.model.weapon.struct;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WeaponIdentityTest {

    @Test
    void test_size_correct() {
        int size = WeaponIdentity.values().length;
        assertEquals(size, WeaponIdentity.getSize());
    }

    @Test
    void test_get_enum_at_index() {
        WeaponIdentity identity = WeaponIdentity.values()[0];
        assertEquals(identity, WeaponIdentity.at(0));
    }
}
