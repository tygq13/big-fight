package bigfight.model.weapon.weapons;

import bigfight.model.weapon.struct.*;

import javafx.util.Pair;

public class WeaponModel {
    private WeaponStruct data;

    public WeaponModel(WeaponStruct data) {
        this.data = data;
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
