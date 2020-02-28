package bigfight.model.weapon;

import bigfight.model.weapon.struct.*;

import javafx.util.Pair;

public class WeaponModel {
    private WeaponStruct data;

    public WeaponModel(WeaponIdentity identity) {
        this(identity, 1);
    }

    public WeaponModel(WeaponIdentity identity, int star) {
        data = WeaponData.ARSENAL.get(identity).withStar(star);
    }

    public Pair getDamage() {
        return data.damage;
    }

    public WeaponType getType() {
        return data.type;
    }

    public String getDescription() {
        return data.description;
    }

    public WeaponIdentity getIdentity() {
        return data.identity;
    }

    public String getName() {
        return data.name;
    }
}
