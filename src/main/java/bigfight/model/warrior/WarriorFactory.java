package bigfight.model.warrior;

import bigfight.algo.BigFightAlgo;
import bigfight.data.DataGetter;
import bigfight.model.skill.SkillManager;
import bigfight.model.skill.skills.AStepAhead;
import bigfight.model.skill.skills.AgileBody;
import bigfight.model.skill.skills.BornAsStrong;
import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.struct.SkillType;
import bigfight.model.warrior.builder.Warrior;
import bigfight.model.warrior.builder.WarriorBuilder;
import bigfight.model.warrior.component.*;
import bigfight.model.warrior.database.Account;
import bigfight.model.warrior.database.WarriorDatabase;
import bigfight.model.weapon.WeaponManager;

public class WarriorFactory {

    public Warrior create(DataGetter dataGetter, EmpowermentFactory empowermentFactory, WarriorDatabase warriorDatabase, String name) {
        Account account = warriorDatabase.createAccount(name);
        Speed speed = new Speed();
        Agility agility = new Agility();
        Strength strength = new Strength();
        initializeAttributes(strength, agility, speed, dataGetter);
        int health = dataGetter.getInitialHealth();
        WeaponManager weaponManager = new WeaponManager();
        SkillManager skillManager = new SkillManager();
        Friends friends = new Friends();
        initializeEmpowerment(weaponManager, skillManager, strength, agility, speed, empowermentFactory);
        Warrior warrior = WarriorBuilder.stepBuilder(warriorDatabase)
                .account(account)
                .strength(strength)
                .agility(agility)
                .speed(speed)
                .health(health)
                .weaponManager(weaponManager)
                .skillManager(skillManager)
                .friends(friends)
                .build();
        return warrior;
    }

    private void initializeAttributes(Strength strength, Agility agility, Speed speed, DataGetter dataGetter) {
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

    private void initializeEmpowerment(WeaponManager weaponManager, SkillManager skillManager, Strength strength,
                                       Agility agility, Speed speed, EmpowermentFactory empowermentFactory) {
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
}
