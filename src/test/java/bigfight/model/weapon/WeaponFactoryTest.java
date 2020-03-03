package bigfight.model.weapon;

import bigfight.model.weapon.struct.WeaponIdentity;

import bigfight.model.weapon.struct.WeaponStruct;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WeaponFactoryTest {

    @Test
    void weapon_create_by_identity() {
        // rely on existence of WeaponIdentity.TRIDENT
        WeaponStruct mockStruct = mock(WeaponStruct.class);
        WeaponData mockData = mock(WeaponData.class);
        when(mockData.getWithStar(any(WeaponIdentity.class), anyInt())).thenReturn(mockStruct);
        WeaponFactory testFactory = new WeaponFactory(mockData);
        Weapon result = testFactory.create(WeaponIdentity.TRIDENT);
        assertNotNull(result);
    }
}
