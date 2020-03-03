package bigfight.model.weapon;

import bigfight.model.warrior.component.EmpowermentFactory;
import bigfight.model.weapon.struct.WeaponIdentity;

public class  WeaponFactory {
    private WeaponData weaponData;

    public WeaponFactory(WeaponData weaponData) {
        this.weaponData = weaponData;
    }

    public Weapon create(WeaponIdentity identity) {
        return new Weapon(new WeaponModel(weaponData.getWithStar(identity, 1)));
    }
}
