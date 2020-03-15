package bigfight.combat.fighter;

import bigfight.data.DataConfig;
import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.skill.struct.SkillList;
import bigfight.model.warrior.Warrior;
import bigfight.model.warrior.component.Empowerment;
import bigfight.model.weapon.Weapon;

import java.util.ArrayList;
import java.util.Random;

public class Fighter {
    private int speed;
    private int strength;
    private int agility;
    private int health;
    private int unarmedDamage;
    private ArrayList<Weapon> weaponList;
    private SkillList skillList;

    public Fighter(Warrior warrior) {
        speed = warrior.getSpeed();
        strength = warrior.getStrength();
        agility = warrior.getAgility();
        health = warrior.getHealth();
        unarmedDamage = DataConfig.DEFAULT_UNARMED_DAMAGE;
        weaponList = (ArrayList<Weapon>) warrior.getWeaponManager().getWeaponList().clone();
        // only get the active skills
        skillList = new SkillList();
        skillList.addNonPermanentFromMap(warrior.getSkillManager().getSkillMap());
    }

    public Empowerment SelectEmpowerment(Random random) {
        int weaponOrSkill = random.nextInt(weaponList.size() + skillList.size());
        if (weaponOrSkill >= weaponList.size()) {
            // create skills
            int luckyDraw = random.nextInt(skillList.size());
            return new Empowerment(skillList.get(luckyDraw));
        } else {
            int luckyDraw = random.nextInt(weaponList.size());
            Empowerment empowerment = new Empowerment(weaponList.get(luckyDraw));
            weaponList.remove(luckyDraw);
            return empowerment;
        }
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

    public int getUnarmedDamage() {
        return unarmedDamage;
    }

    public SkillList getPassiveSkills() {
        SkillList result = new SkillList();
        for(int i = 0; i < skillList.size(); i++) {
            SkillModel model = skillList.get(i);
            if (model.getIdentity() == SkillIdentity.APPARENT_DEATH) {
                result.add(model);
            }
        }
        return result;
    }
}
