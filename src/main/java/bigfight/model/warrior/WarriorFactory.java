package bigfight.model.warrior;

import bigfight.algo.BigFightAlgo;
import bigfight.data.DataGetter;
import bigfight.model.skill.SkillManager;
import bigfight.model.skill.skills.*;
import bigfight.model.skill.struct.SkillType;
import bigfight.model.warrior.builder.Warrior;
import bigfight.model.warrior.builder.WarriorBuilder;
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
        initializeEmpowerment(weaponManager, skillManager, strength, agility, speed, health, empowermentFactory);
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

    // todo: move this logic to level-up
    private void initializeEmpowerment(WeaponManager weaponManager, SkillManager skillManager, Strength strength,
                                       Agility agility, Speed speed, Health health, EmpowermentFactory empowermentFactory) {
        Empowerment newEmpowerment =
                empowermentFactory.randomGetNew(weaponManager.getWeaponList(), skillManager.getSkillMap());
        if (newEmpowerment != null) {
            newEmpowerment.addTo(weaponManager, skillManager);
            if (newEmpowerment.getSkill() != null && newEmpowerment.getSkill().getType() == SkillType.PERMANENT) {
                SkillModel skillModel = newEmpowerment.getSkill();
                switch (skillModel.getIdentity()) {
                    case BORN_AS_STRONG:
                        BornAsStrong bornAsStrong = (BornAsStrong) skillModel;
                        bornAsStrong.upgrade(strength);
                        break;
                    case AGILE_BODY:
                        AgileBody agileBody = (AgileBody) skillModel;
                        agileBody.upgrade(agility);
                        break;
                    case A_STEP_AHEAD:
                        AStepAhead aStepAhead = (AStepAhead) skillModel;
                        aStepAhead.upgrade(speed);
                    case STRONG_PHYSIQUE:
                        StrongPhysique strongPhysique = (StrongPhysique) skillModel;
                        strongPhysique.upgrade(health);

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
