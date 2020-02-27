package bigfight.model.weapon;

import bigfight.model.weapon.WeaponData.WeaponType;

public class Weapon {
    private int damage;
    private String name;
    private WeaponType type;
    private String description;

    public Weapon(String name, int damage, WeaponType type, String description) {
        this.name = name;
        this.damage = damage;
        this.type = type;
        this.description = description;
    }

    public int getDamage() {
        return damage;
    }

    public String getName() {
        return name;
    }

    public WeaponType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
