package bigfight.combat.fighter;

import bigfight.combat.util.CombatRandom;
import bigfight.data.DataConfig;
import bigfight.model.skill.skills.FastHands;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.skill.struct.SkillList;
import bigfight.model.warrior.builder.Warrior;
import bigfight.model.warrior.component.Empowerment;
import bigfight.model.warrior.component.AdvancedAttribute;
import bigfight.model.weapon.Weapon;
import bigfight.model.weapon.struct.Damage;

import java.util.ArrayList;

public class Fighter {
    private int speed;
    private int strength;
    private int agility;
    private int health;
    private int level;
    private AdvancedAttribute advancedAttribute;
    private Damage unarmedDamage;
    private ArrayList<Weapon> weaponList;
    private SkillList activeSkillList;
    private SkillList specialSkillList;
    private String name;

    public Fighter(Warrior warrior) {
        name = warrior.getName();
        speed = warrior.getSpeed();
        strength = warrior.getStrength();
        agility = warrior.getBasicAttribute();
        health = warrior.getHealth();
        level = warrior.getLevel();
        advancedAttribute = warrior.getWeaponAttributeCopy();
        unarmedDamage = DataConfig.DEFAULT_UNARMED_DAMAGE;
        weaponList = (ArrayList<Weapon>) warrior.getWeaponManager().getWeaponList().clone();
        // only get the active skills
        activeSkillList = new SkillList();
        activeSkillList.addActiveFromMap(warrior.getSkillManager().getSkillMap());
        specialSkillList = new SkillList();
        specialSkillList.addSpecialFromMap(warrior.getSkillManager().getSkillMap());
    }

    public Empowerment selectEmpowerment(CombatRandom random) {
        int totalSize = weaponList.size() + activeSkillList.size();
        if (totalSize == 0 || random.selectUnarmed() < DataConfig.UNARMED_CHANCE) {
            // unarmed attack
            Weapon weapon = null;
            return new Empowerment(weapon);
        }
        Empowerment empowerment;
        int weaponOrSkill = random.selectWeaponOrSkill(totalSize);
        if (weaponOrSkill < weaponList.size() && weaponList.size() > 0) {
            // create weapon
            int luckyDraw = random.selectWhichEmpowerment(weaponList.size());
            empowerment = new Empowerment(weaponList.get(luckyDraw));
            weaponList.remove(luckyDraw);
        } else {
            // create skills
            int luckyDraw = random.selectWhichEmpowerment(activeSkillList.size());
            empowerment =  new Empowerment(activeSkillList.get(luckyDraw));
        }
        return empowerment;
    }

    public String getName() {
        return name;
    }

    public int getWeaponSize() {
        return weaponList.size();
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

    public AdvancedAttribute getAdvancedAttribute() {
        return advancedAttribute;
    }

    public Damage getUnarmedDamage() {
        return unarmedDamage;
    }

    SkillList getSpecialSkills() {
        return specialSkillList;
    }

    public void selectAuxiliarySkill(FighterFlag fighterFlag, CombatRandom random) {
        int totalSize = weaponList.size() + specialSkillList.size() + activeSkillList.size();
        if (specialSkillList.contains(SkillIdentity.FAST_HANDS)) {
            FastHands fastHands = (FastHands) specialSkillList.get(SkillIdentity.FAST_HANDS);
            double chance = (1.0 / totalSize) * fastHands.getExtraChance();
            if (random.selectAuxiliarySkillRandom() < chance) {
                fighterFlag.fastHandsFlag = true;
            }
        }
    }
}
