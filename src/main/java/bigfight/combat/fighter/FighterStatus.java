package bigfight.combat.fighter;

import bigfight.model.skill.skills.ShadowMove;
import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.skill.struct.SkillList;
import bigfight.model.warrior.component.Empowerment;
import bigfight.model.warrior.component.AdvancedAttribute;
import bigfight.model.weapon.Weapon;
import bigfight.model.weapon.struct.Damage;

import java.net.PortUnreachableException;

public class FighterStatus {
    private String name;
    private int speed;
    private int strength;
    private int agility;
    private int health;
    private int level;
    private AdvancedAttribute advancedAttribute;
    private Damage unarmedDamage;
    private Weapon holdingWeapon;
    private SkillList specialSkills;
    private FighterFlag fighterFlag;

    public FighterStatus(Fighter fighter) {
        name = fighter.getName();
        speed = fighter.getSpeed();
        strength = fighter.getStrength();
        agility = fighter.getAgility();
        health = fighter.getHealth();
        level = fighter.getLevel();
        advancedAttribute = fighter.getAdvancedAttribute();
        unarmedDamage = fighter.getUnarmedDamage();
        specialSkills = fighter.getSpecialSkills();
        fighterFlag = new FighterFlag();
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

    public int getLevel() {
        return level;
    }

    public void updateHealth(int newHealth) {
        health = newHealth;
    }

    public Damage getUnarmedDamage() {
        return unarmedDamage;
    }

    public Weapon getHoldingWeapon() {
        return holdingWeapon;
    }

    public AdvancedAttribute getAdvancedAttribute() {
        return advancedAttribute;
    }

    public FighterFlag getFighterFlag() {
        return fighterFlag;
    }

    public boolean hasSkill(SkillIdentity identity) {
        return specialSkills != null && specialSkills.contains(identity);
    }

    public SkillModel getSkill(SkillIdentity identity) {
        return specialSkills.get(identity);
    }

    public void removeSkill(SkillIdentity identity) {
        specialSkills.remove(identity);
    }

    public void updateStatusByFlag() {
        if (fighterFlag.shadowMoveFlag) {
            // todo: throw exception if doesn't have
            // todo: multiply by base speed instead of speed
            ShadowMove shadowMove = (ShadowMove) specialSkills.get(SkillIdentity.SHADOW_MOVE);
            if (fighterFlag.shadowMoveRound == 0) {
                fighterFlag.shadowMoveFlag = false;
                speed /= (1 + shadowMove.getSpeedMultiply());
                advancedAttribute.bigExtraPercentageDamage -= shadowMove.getDamageMultiply();
                advancedAttribute.mediumExtraPercentageDamage -= shadowMove.getDamageMultiply();
                advancedAttribute.smallExtraPercentageDamage -= shadowMove.getDamageMultiply();
                advancedAttribute.throwExtraPercentageDamage -= shadowMove.getDamageMultiply();
                advancedAttribute.unarmedExtraPercentageDamage -= shadowMove.getDamageMultiply();
                advancedAttribute.skillExtraPercentageDamage -= shadowMove.getDamageMultiply();
            } else {
                fighterFlag.shadowMoveRound -= 1;
                speed *= (1 + shadowMove.getSpeedMultiply());
                advancedAttribute.bigExtraPercentageDamage += shadowMove.getDamageMultiply();
                advancedAttribute.mediumExtraPercentageDamage += shadowMove.getDamageMultiply();
                advancedAttribute.smallExtraPercentageDamage += shadowMove.getDamageMultiply();
                advancedAttribute.throwExtraPercentageDamage += shadowMove.getDamageMultiply();
                advancedAttribute.unarmedExtraPercentageDamage += shadowMove.getDamageMultiply();
                advancedAttribute.skillExtraPercentageDamage += shadowMove.getDamageMultiply();
            }
        }
    }
}
