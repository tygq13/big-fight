package bigfight.model.weapon;

import bigfight.model.weapon.struct.WeaponIdentity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WeaponModelTest {

    @Test
    void model_initialized_and_accessible_with_example_trident() {
        WeaponIdentity identity = WeaponIdentity.TRIDENT;
        WeaponModel result = new WeaponModel(identity);
        assertNotNull(result.getDamage());
        assertNotNull(result.getName());
        assertNotNull(result.getDescription());
        assertNotNull(result.getIdentity());
        assertNotNull(result.getType());
    }
}
