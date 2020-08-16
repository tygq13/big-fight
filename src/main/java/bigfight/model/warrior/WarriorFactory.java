package bigfight.model.warrior;

import bigfight.algo.BigFightAlgo;
import bigfight.data.DataConfig;
import bigfight.data.DataGetter;
import bigfight.model.skill.SkillManager;
import bigfight.model.skill.skills.permanent.*;
import bigfight.model.skill.struct.SkillType;
import bigfight.model.warrior.builder.Warrior;
import bigfight.model.warrior.builder.WarriorBuilder;
import bigfight.model.warrior.component.*;
import bigfight.model.warrior.component.attr.BasicAttribute;
import bigfight.model.warrior.component.attr.Attribute;
import bigfight.model.warrior.database.Account;
import bigfight.model.warrior.database.WarriorDatabase;
import bigfight.model.warrior.npc.NpcIdentity;
import bigfight.model.weapon.WeaponManager;

public class WarriorFactory {

    public Warrior create(DataGetter dataGetter, EmpowermentFactory empowermentFactory, WarriorDatabase warriorDatabase, String name) {
        Account account = warriorDatabase.createAccount(name);
        BasicAttribute speed = new BasicAttribute();
        BasicAttribute agility = new BasicAttribute();
        BasicAttribute strength = new BasicAttribute();
        initializeAttributes(strength, agility, speed, dataGetter);
        BasicAttribute health = new BasicAttribute(dataGetter.getInitialHealth());
        WeaponManager weaponManager = new WeaponManager();
        SkillManager skillManager = new SkillManager();
        Friends friends = getInitialFriends(warriorDatabase);
        Attribute attribute = new Attribute(strength, agility, speed, health);
        Warrior warrior = WarriorBuilder.stepBuilder(warriorDatabase)
                .account(account)
                .level(1)
                .attribute(attribute)
                .weaponManager(weaponManager)
                .skillManager(skillManager)
                .friends(friends)
                .build();

        giveEmpowerment(warrior, empowermentFactory);
        return warrior;
    }

    public void warriorLevelUp(Warrior warrior, EmpowermentFactory empowermentFactory) {
        warrior.levelUp();
        warrior.getHealthObj().addToAddition(DataConfig.LEVEL_UP_HEALTH_ADDITION);
        int[] distribution = BigFightAlgo.uniformRandomDistribute(3, DataConfig.LEVEL_UP_ATTRIBUTE_ADDITION_NORMAL);
        warrior.getStrengthObj().addToBase(distribution[0]);
        warrior.getAgilityObj().addToBase(distribution[1]);
        warrior.getSpeedObj().addToBase(distribution[2]);
        giveEmpowerment(warrior, empowermentFactory);
    }

    private void initializeAttributes(BasicAttribute strength, BasicAttribute agility, BasicAttribute speed, DataGetter dataGetter) {
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
                empowermentFactory.randomGetNew(warrior.getWeaponManager().getWeaponList(), warrior.getSkillManager().getSkillMap());
        if (newEmpowerment != null) {
            newEmpowerment.addTo(warrior.getWeaponManager(), warrior.getSkillManager());
            if (newEmpowerment.getSkill() != null && newEmpowerment.getSkill().getType() == SkillType.PERMANENT) {
                PermanentSkill permanentSkill = (PermanentSkill) newEmpowerment.getSkill();
                permanentSkill.upgrade(warrior.getAttribute());
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
