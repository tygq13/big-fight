package bigfight.combat.fighter;

import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.skill.struct.SkillList;
import bigfight.model.warrior.component.Empowerment;
import bigfight.model.weapon.Weapon;

public class FighterStatus {
    private int speed;
    private int strength;
    private int agility;
    private int health;
    private int unarmedDamage;
    private Weapon holdingWeapon;
    private SkillList passiveSkills;
    private double escape;
    private double ignore;
    private double focus;

    public FighterStatus(Fighter fighter) {
        speed = fighter.getSpeed();
        strength = fighter.getStrength();
        agility = fighter.getAgility();
        health = fighter.getHealth();
        unarmedDamage = fighter.getUnarmedDamage();
        passiveSkills = fighter.getPassiveSkills();
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

    public Weapon getHoldingWeapon() {
        return holdingWeapon;
    }

    public boolean hasSkill(SkillIdentity identity) {
        return passiveSkills.contains(identity);
    }

    public SkillModel getSkill(SkillIdentity identity) {
        return passiveSkills.get(identity);
    }

    public void removeSkill(SkillIdentity identity) {
        passiveSkills.remove(identity);
    }
}
