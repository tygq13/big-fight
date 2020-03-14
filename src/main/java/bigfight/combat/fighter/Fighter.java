package bigfight.combat.fighter;

import bigfight.data.DataConfig;
import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.skill.struct.SkillType;
import bigfight.model.warrior.Warrior;
import bigfight.model.warrior.component.Empowerment;
import bigfight.model.weapon.Weapon;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class Fighter {
    private int speed;
    private int strength;
    private int agility;
    private int health;
    private int unarmedDamage;
    private ArrayList<Weapon> weaponList;
    private ArrayList<SkillModel> skillList;

    public Fighter(Warrior warrior) {
        speed = warrior.getSpeed();
        strength = warrior.getStrength();
        agility = warrior.getAgility();
        health = warrior.getHealth();
        unarmedDamage = DataConfig.DEFAULT_UNARMED_DAMAGE;
        weaponList = (ArrayList<Weapon>) warrior.getWeaponManager().getWeaponList().clone();
        // only get the active skills
        skillList = new ArrayList<>();
        for (Map.Entry<SkillIdentity, SkillModel> model: warrior.getSkillManager().getSkillMap().entrySet()) {
            if (model.getValue().getType() != SkillType.PERMANENT) {
                skillList.add(model.getValue());
            }
        }
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
}
