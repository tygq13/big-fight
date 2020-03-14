package bigfight.model.warrior;

import bigfight.data.DataGetter;
import bigfight.model.skill.skills.AStepAhead;
import bigfight.model.skill.skills.AgileBody;
import bigfight.model.skill.skills.BornAsStrong;
import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.struct.SkillType;
import bigfight.model.warrior.component.*;
import bigfight.model.weapon.WeaponManager;
import bigfight.algo.BigFightAlgo;
import bigfight.model.skill.SkillManager;

public class Warrior {
    private DataGetter dataGetter;
    private EmpowermentFactory empowermentFactory;

    private Speed speed;
    private Agility agility;
    private Strength strength;
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
        speed = new Speed();
        agility = new Agility();
        strength = new Strength();
        // each attribute is at least one
        int valueLeft = dataGetter.getInitialAttributeTotal() - 3;
        speed.addToBase(1);
        strength.addToBase(1);
        agility.addToBase(1);
        if (valueLeft > 0) {
            int[] attributeList = BigFightAlgo.uniformRandomDistribute(3, valueLeft);
            speed.addToBase(attributeList[0]);
            strength.addToBase(attributeList[1]);
            agility.addToBase(attributeList[2]);
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
            if (newEmpowerment.getSkill() != null && newEmpowerment.getSkill().getType() == SkillType.PERMANENT) {
                SkillModel skillModel = newEmpowerment.getSkill();
                switch (skillModel.getIdentity()) {
                    case BORN_AS_STRONG:
                        BornAsStrong bornAsStrong = (BornAsStrong) skillModel;
                        strength.add(bornAsStrong.upgrade(strength.getBase()));
                        break;
                    case AGILE_BODY:
                        AgileBody agileBody = (AgileBody) skillModel;
                        agility.add(agileBody.upgrade(agility.getBase()));
                        break;
                    case A_STEP_AHEAD:
                        AStepAhead aStepAhead = (AStepAhead) skillModel;
                        speed.add(aStepAhead.upgrade(speed.getBase()));
                }
            }
        }
    }

    public int getSpeed() {
        return speed.value();
    }

    public int getStrength() {
        return strength.value();
    }

    public int getAgility() {
        return agility.value();
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
