package bigfight.combat.fighter;

import bigfight.combat.util.CombatRandom;
import bigfight.data.DataConfig;
import bigfight.model.warrior.builder.Warrior;
import bigfight.model.warrior.component.Empowerment;
import bigfight.model.warrior.component.AdvancedAttribute;
import bigfight.model.weapon.Weapon;
import bigfight.model.weapon.struct.Damage;

public class Fighter {
    private int speed;
    private int strength;
    private int agility;
    private int health;
    private int level;
    private AdvancedAttribute advancedAttribute;
    private Damage unarmedDamage;
    private DisposableWeaponList weaponList;
    private ActiveSkillList activeSkillList;
    private SpecialSkillList specialSkillList;
    private String name;

    public Fighter(Warrior warrior) {
        name = warrior.getName();
        speed = warrior.getSpeed();
        strength = warrior.getStrength();
        agility = warrior.getAgility();
        health = warrior.getHealth();
        level = warrior.getLevel();
        advancedAttribute = warrior.getWeaponAttributeCopy();
        unarmedDamage = DataConfig.DEFAULT_UNARMED_DAMAGE;
        weaponList = new DisposableWeaponList(warrior.getWeaponManager().getWeaponList());
        // only get the active skills
        activeSkillList = new ActiveSkillList(warrior.getSkillManager().getSkillMap());
        specialSkillList = new SpecialSkillList(warrior.getSkillManager().getSkillMap());
    }

    public Empowerment selectEmpowerment(CombatRandom random) {
        int totalSize = weaponList.size() + activeSkillList.size();
        if (totalSize == 0 || random.selectUnarmed() < DataConfig.UNARMED_CHANCE) {
            // unarmed attack
            return new Empowerment((Weapon) null);
        }
        int weaponOrSkill = random.selectWeaponOrSkill(totalSize);
        if (weaponOrSkill < weaponList.size() && weaponList.size() > 0) {
            return weaponList.select(random);
        } else {
            // create skills
            return activeSkillList.select(random);
        }
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

    SpecialSkillList getSpecialSkills() {
        return specialSkillList;
    }

    // In this implementation, the chance is not exactly equal to the intended invocation chance
    // nice fun to have, isn't it?
    public void selectAuxiliarySkill(FighterFlag fighterFlag, CombatRandom random) {
        int totalSize = weaponList.size() + specialSkillList.size() + activeSkillList.size();
        specialSkillList.select(fighterFlag, random, totalSize);
    }
}
