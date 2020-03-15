package bigfight.model.weapon.weapons;

import bigfight.model.weapon.Weapon;
import bigfight.model.weapon.struct.WeaponStruct;

public class Trident extends WeaponModel {
    final int TRIDENT_REST_ROUND = 1;

    public Trident(WeaponStruct weaponStruct) {
        super(weaponStruct);
    }

    public int getRestRound() {
        return TRIDENT_REST_ROUND;
    }
}
