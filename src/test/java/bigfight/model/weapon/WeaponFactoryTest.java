package bigfight.model.weapon;

import bigfight.model.weapon.struct.WeaponIdentity;

import org.junit.jupiter.api.Test;

class WeaponFactoryTest {

    @Test
    void weapon_create_by_identity() {
        Weapon weapon = WeaponFactory.create(WeaponIdentity.TRIDENT);
    }
}
