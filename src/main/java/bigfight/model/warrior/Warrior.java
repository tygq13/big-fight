package bigfight.model.warrior;

import bigfight.data.DataGetter;
import bigfight.model.warrior.component.Empowerment;
import bigfight.model.warrior.component.EmpowermentFactory;
import bigfight.model.weapon.WeaponManager;
import bigfight.algo.BigFightAlgo;
import bigfight.model.skill.SkillManager;

public class Warrior {
    private DataGetter dataGetter;
    private EmpowermentFactory empowermentFactory;

    private int speed;
    private int strength;
    private int agility;
    private int level;
    private int health;
    private WeaponManager weaponManager;
    private SkillManager skillManager;

    public Warrior(DataGetter dataGetter, EmpowermentFactory empowermentFactory) {
        this.dataGetter = dataGetter;
        this.empowermentFactory = empowermentFactory;
        weaponManager = new WeaponManager();
        skillManager = new SkillManager();
        initializeAttributes();
        initializeHealth();
        initializeFirstEmpowerment();
        level = 1;
    }

    private void initializeAttributes() {
        // each attribute is at least one
        int valueLeft = dataGetter.getInitialAttributeTotal() - 3;
        speed = 1;
        strength = 1;
        agility = 1;
        if (valueLeft > 0) {
            int[] attributeList = BigFightAlgo.uniformRandomDistribute(3, valueLeft);
            speed += attributeList[0];
            strength += attributeList[1];
            agility += attributeList[2];
        }
    }

    private void initializeHealth() {
        health   = dataGetter.getInitialHealth();
    }

    private void initializeFirstEmpowerment() {
        Empowerment newEmpowerment =
                empowermentFactory.randomGetNew(weaponManager.getWeaponList(), skillManager.getSkillMap());
        if (newEmpowerment != null) {
            newEmpowerment.addTo(weaponManager, skillManager);
        }
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

}
