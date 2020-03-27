package bigfight.model.warrior.builder;

import bigfight.data.DataGetter;
import bigfight.model.warrior.component.*;
import bigfight.model.weapon.WeaponManager;
import bigfight.model.skill.SkillManager;

public class Warrior {
    private String name;

    private Speed speed;
    private Agility agility;
    private Strength strength;
    private int level;
    private int health;
    private WeaponManager weaponManager;
    private SkillManager skillManager;

    Warrior(String name, Strength strength, Agility agility, Speed speed, int health, WeaponManager weaponManager, SkillManager skillManager) {
        this.name = name;
        this.strength = strength;
        this.agility = agility;
        this.speed = speed;
        this.health = health;
        level = 1;
        this.weaponManager = weaponManager;
        this.skillManager = skillManager;
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed.value();
    }

    public int getStrength() {
        return strength.value();
    }

    public int getAgility() {
        return agility.value();
    }

    public int getLevel() {
        return level;
    }

    public int getHealth() {
        return health;
    }

    public WeaponManager getWeaponManager() {
        return weaponManager;
    }

    public SkillManager getSkillManager() {
        return skillManager;
    }

    @Override
    public String toString() {
        String result = String.format("Fighter name: %s\n", name)
                + String.format("Level: %d\n", level)
                + String.format("Strength: %d\n", strength.value())
                + String.format("Agility %d\n", agility.value())
                + String.format("Speed %d\n", speed.value())
                + String.format("Health %d\n", health);
        result += "Weapons: " + weaponManager.toString() + System.lineSeparator();
        result += "Skills: " + skillManager.toString() + System.lineSeparator();
        return result;
    }
}
