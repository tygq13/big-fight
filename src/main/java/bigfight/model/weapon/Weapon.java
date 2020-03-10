package bigfight.model.weapon;

import bigfight.model.weapon.struct.*;
import javafx.util.Pair;

public class Weapon {
    private WeaponModel model;

    public Weapon(WeaponModel model) {
        this.model = model;
    }

    public Pair<Integer, Integer> getDamage() {
        return model.getDamage();
    }

    public String getName() {
        return model.getName();
    }

    public WeaponType getType() {
        return model.getType();
    }

    public String getDescription() {
        return model.getDescription();
    }

    public WeaponIdentity getIdentity() {
        return model.getIdentity();
    }
}
