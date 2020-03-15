package bigfight.model.weapon;

import bigfight.model.weapon.struct.WeaponIdentity;
import bigfight.model.weapon.weapons.*;

public class  WeaponFactory {
    private WeaponData weaponData;

    public WeaponFactory(WeaponData weaponData) {
        this.weaponData = weaponData;
    }

    public Weapon create(WeaponIdentity identity) {
        switch (identity) {
            case TRIDENT:
                return new Weapon(new Trident(weaponData.getWithStar(identity, 1)));
            case GAS_HAMMER:
                return new Weapon(new GasHammer(weaponData.getWithStar(identity, 1)));
            case METEOR_BALL:
                return new Weapon(new MeteorBall(weaponData.getWithStar(identity, 1)));
            case DEMON_SCYTHE:
                return new Weapon(new DemonScythe(weaponData.getWithStar(identity, 1)));
            case JUDGE_PENCIL:
                return new Weapon(new JudgePencil(weaponData.getWithStar(identity, 1)));
            default:
                // better to throw exception
                return null;
        }
    }
}
