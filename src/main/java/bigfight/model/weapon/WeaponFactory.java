package bigfight.model.weapon;

import bigfight.model.weapon.struct.WeaponIdentity;

public class WeaponFactory {

    public static Weapon create(WeaponIdentity identity) {
        return new Weapon(new WeaponModel(identity));
    }
}
