package bigfight.model.weapon;

import bigfight.model.weapon.struct.WeaponIdentity;
import bigfight.model.weapon.struct.WeaponStruct;
import bigfight.model.weapon.struct.WeaponType;

import bigfight.model.weapon.weapons.WeaponModel;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WeaponTest {

    @Test
    void weapon_initialized_and_accessible() {
        // rely on correct construction of WeaponStruct with the existence of WeaponType.SMALL and WeaponIdentity.TRIDENT
        WeaponStruct testStruct = new WeaponStruct(
                new Pair<>(1, 1),
                WeaponType.SMALL,
                "test",
                WeaponIdentity.TRIDENT,
                "test"
        );
        Weapon result = new Weapon(new WeaponModel(testStruct));
        assertNotNull(result.getDamage());
        assertNotNull(result.getName());
        assertNotNull(result.getDescription());
        assertNotNull(result.getIdentity());
        assertNotNull(result.getType());
    }
}
