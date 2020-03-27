package bigfight.combat.fighter;

import bigfight.data.DataConfig;
import bigfight.model.skill.struct.SkillList;
import bigfight.model.warrior.builder.Warrior;
import bigfight.model.warrior.component.Empowerment;
import bigfight.model.weapon.Weapon;
import bigfight.model.weapon.struct.Damage;

import java.util.ArrayList;
import java.util.Random;

public class Fighter {
    private int speed;
    private int strength;
    private int agility;
    private int health;
    private Damage unarmedDamage;
    private ArrayList<Weapon> weaponList;
    private SkillList activeSkillList;
    private SkillList specialSkillList;
    private String name;

    public Fighter(Warrior warrior) {
        name = warrior.getName();
        speed = warrior.getSpeed();
        strength = warrior.getStrength();
        agility = warrior.getAgility();
        health = warrior.getHealth();
        unarmedDamage = DataConfig.DEFAULT_UNARMED_DAMAGE;
        weaponList = (ArrayList<Weapon>) warrior.getWeaponManager().getWeaponList().clone();
        // only get the active skills
        activeSkillList = new SkillList();
        activeSkillList.addActiveFromMap(warrior.getSkillManager().getSkillMap());
        specialSkillList = new SkillList();
        specialSkillList.addSpecialFromMap(warrior.getSkillManager().getSkillMap());
    }

    public Empowerment SelectEmpowerment(Random random) {
        int totalSize = weaponList.size() + activeSkillList.size();
        int weaponOrSkill = totalSize > 0 ? random.nextInt(totalSize + 1) : 0;
        Empowerment empowerment;
        if (weaponOrSkill < weaponList.size() && weaponList.size() > 0) {
            // create weapon
            int luckyDraw = random.nextInt(weaponList.size());
            empowerment = new Empowerment(weaponList.get(luckyDraw));
            weaponList.remove(luckyDraw);
        } else if (weaponOrSkill > weaponList.size() && activeSkillList.size() > 0){
            // create skills
            int luckyDraw = random.nextInt(activeSkillList.size());
            empowerment =  new Empowerment(activeSkillList.get(luckyDraw));
        } else {
            // create unarmed
            Weapon weapon = null;
            empowerment = new Empowerment(weapon);
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

    public Damage getUnarmedDamage() {
        return unarmedDamage;
    }

    SkillList getSpecialSkills() {
        return specialSkillList;
    }
}
