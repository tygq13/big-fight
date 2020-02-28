package bigfight.model.weapon.struct;

import javafx.util.Pair;

public class WeaponStruct {
    public Pair damage;
    public WeaponType type;
    public String description;
    public WeaponIdentity identity;
    public String name;

    public WeaponStruct(Pair damage, WeaponType type, String description,
                        WeaponIdentity identity, String name) {
        this.damage = damage;
        this.type = type;
        this.description = description;
        this.identity = identity;
        this.name = name;
    }
}
