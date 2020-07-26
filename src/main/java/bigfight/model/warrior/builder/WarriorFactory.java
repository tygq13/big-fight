package bigfight.model.warrior.builder;

import bigfight.algo.BigFightAlgo;
import bigfight.data.DataConfig;
import bigfight.data.DataGetter;
import bigfight.model.skill.SkillManager;
import bigfight.model.skill.skills.*;
import bigfight.model.skill.struct.SkillType;
import bigfight.model.warrior.component.*;
import bigfight.model.warrior.database.Account;
import bigfight.model.warrior.database.WarriorDatabase;
import bigfight.model.warrior.npc.NpcIdentity;
import bigfight.model.weapon.WeaponManager;

public class WarriorFactory {

    public Warrior create(DataGetter dataGetter, EmpowermentFactory empowermentFactory, WarriorDatabase warriorDatabase, String name) {
        Account account = warriorDatabase.createAccount(name);
        Speed speed = new Speed();
        Agility agility = new Agility();
        Strength strength = new Strength();
        initializeAttributes(strength, agility, speed, dataGetter);
        Health health = new Health(dataGetter.getInitialHealth());
        WeaponManager weaponManager = new WeaponManager();
        SkillManager skillManager = new SkillManager();
        Friends friends = getInitialFriends(warriorDatabase);
        Warrior warrior = WarriorBuilder.stepBuilder(warriorDatabase)
                .account(account)
                .level(1)
                .strength(strength)
                .agility(agility)
                .speed(speed)
                .health(health)
                .weaponManager(weaponManager)
                .skillManager(skillManager)
                .friends(friends)
                .build();

        giveEmpowerment(warrior, empowermentFactory);
        return warrior;
    }

    public void warriorLevelUp(Warrior warrior, EmpowermentFactory empowermentFactory) {
        warrior.level += 1;
        warrior.health.addToAddition(DataConfig.LEVEL_UP_HEALTH_ADDITION);
        int[] distribution = BigFightAlgo.uniformRandomDistribute(3, DataConfig.LEVEL_UP_ATTRIBUTE_ADDITION_NORMAL);
        warrior.strength.addToBase(distribution[0]);
        warrior.agility.addToBase(distribution[1]);
        warrior.speed.addToBase(distribution[2]);
        giveEmpowerment(warrior, empowermentFactory);
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

    private void giveEmpowerment(Warrior warrior, EmpowermentFactory empowermentFactory) {
        Empowerment newEmpowerment =
                empowermentFactory.randomGetNew(warrior.weaponManager.getWeaponList(), warrior.skillManager.getSkillMap());
        if (newEmpowerment != null) {
            newEmpowerment.addTo(warrior.weaponManager, warrior.skillManager);
            if (newEmpowerment.getSkill() != null && newEmpowerment.getSkill().getType() == SkillType.PERMANENT) {
                SkillModel skillModel = newEmpowerment.getSkill();
                switch (skillModel.getIdentity()) {
                    case BORN_AS_STRONG:
                        BornAsStrong bornAsStrong = (BornAsStrong) skillModel;
                        bornAsStrong.upgrade(warrior.strength);
                        break;
                    case AGILE_BODY:
                        AgileBody agileBody = (AgileBody) skillModel;
                        agileBody.upgrade(warrior.agility);
                        break;
                    case A_STEP_AHEAD:
                        AStepAhead aStepAhead = (AStepAhead) skillModel;
                        aStepAhead.upgrade(warrior.speed);
                        break;
                    case STRONG_PHYSIQUE:
                        StrongPhysique strongPhysique = (StrongPhysique) skillModel;
                        strongPhysique.upgrade(warrior.health);

                }
            }
        }
    }

    private Friends getInitialFriends(WarriorDatabase warriorDatabase) {
        Friends friends = new Friends();
        for(NpcIdentity identity : NpcIdentity.getArray()) {
            friends.add(identity.getValue());
        }
        return friends;
    }
}
