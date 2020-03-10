package bigfight.combat.fighter;

import bigfight.model.warrior.component.Empowerment;
import bigfight.model.weapon.Weapon;

public class FighterStatus {
    private int speed;
    private int strength;
    private int agility;
    private int health;
    private int unarmedDamage;
    private Weapon holdingWeapon;
    private double escape;
    private double ignore;
    private double focus;

    public FighterStatus(Fighter fighter) {
        speed = fighter.getSpeed();
        strength = fighter.getStrength();
        agility = fighter.getAgility();
        health = fighter.getHealth();
        unarmedDamage = fighter.getUnarmedDamage();
        escape = 0;
        ignore = 0;
        focus = 0;
    }

    public void changeWeapon(Empowerment empowerment) {
        holdingWeapon = empowerment.getWeapon();
    }

    public int getSpeed() {
        return speed;
    }

    public int getStrength() {
        return strength;
    }

    public int getAgility() {
        return agility;
    }

    public int getHealth() {
        return health;
    }

    public void updateHealth(int newHealth) {
        health = newHealth;
    }

    public int getUnarmedDamage() {
        return unarmedDamage;
    }

    public double getFocus() {
        return focus;
    }

    public double getEscape() {
        return escape;
    }

    public double getIgnore() {
        return ignore;
    }
}
