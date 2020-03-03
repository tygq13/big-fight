package bigfight.model.weapon;

import bigfight.model.weapon.struct.*;
import bigfight.model.weapon.struct.WeaponStruct;

import javafx.util.Pair;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class WeaponModelTest {
    @Test
    void model_initialized_by_WeaponStruct_and_accessible() {
        // rely on correct construction of WeaponStruct with the existence of WeaponType.SMALL and WeaponIdentity.TRIDENT
        WeaponStruct testStruct = new WeaponStruct(
                new Pair<>(1, 1),
                WeaponType.SMALL,
                "test",
                WeaponIdentity.TRIDENT,
                "test"
        );
        WeaponModel result = new WeaponModel(testStruct);
        assertNotNull(result.getDamage());
        assertNotNull(result.getName());
        assertNotNull(result.getDescription());
        assertNotNull(result.getIdentity());
        assertNotNull(result.getType());
    }
}
