package bigfight.model.weapon.struct;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class WeaponStructArrayTest {
    @Mock WeaponStruct weaponStruct;
    @InjectMocks WeaponStructArray weaponStructArray;

    @Test
    void test_weapon_struct_array_get_with_star() {
        assertEquals(weaponStruct, weaponStructArray.withStar(1));
    }

}
