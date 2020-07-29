package bigfight.combat.fighter;

import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.skill.struct.SkillList;
import bigfight.model.warrior.component.Empowerment;
import bigfight.model.warrior.component.AdvancedAttribute;
import bigfight.model.weapon.Weapon;
import bigfight.model.weapon.struct.Damage;

public class FighterStatus {
    private String name;
    private int speed;
    private int strength;
    private int agility;
    private int health;
    private AdvancedAttribute advancedAttribute;
    private Damage unarmedDamage;
    private Weapon holdingWeapon;
    private SkillList specialSkills;
    private double escape;
    private double ignore;
    private double focus;

    public FighterStatus(Fighter fighter) {
        name = fighter.getName();
        speed = fighter.getSpeed();
        strength = fighter.getStrength();
        agility = fighter.getAgility();
        health = fighter.getHealth();
        advancedAttribute = fighter.getAdvancedAttribute();
        unarmedDamage = fighter.getUnarmedDamage();
        specialSkills = fighter.getSpecialSkills();
        escape = 0;
        ignore = 0;
        focus = 0;
    }

    public void changeWeapon(Empowerment empowerment) {
        holdingWeapon = empowerment.getWeapon();
    }

    public String getName() {
        return name;
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

    public Damage getUnarmedDamage() {
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

    public AdvancedAttribute getAdvancedAttribute() {
        return advancedAttribute;
    }

    public boolean hasSkill(SkillIdentity identity) {
        return specialSkills.contains(identity);
    }

    public SkillModel getSkill(SkillIdentity identity) {
        return specialSkills.get(identity);
    }

    public void removeSkill(SkillIdentity identity) {
        specialSkills.remove(identity);
    }
}
