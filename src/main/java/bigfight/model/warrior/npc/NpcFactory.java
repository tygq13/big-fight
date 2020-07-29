package bigfight.model.warrior.npc;

import bigfight.model.skill.SkillManager;
import bigfight.model.warrior.builder.Warrior;
import bigfight.model.warrior.builder.WarriorBuilder;
import bigfight.model.warrior.component.BasicAttribute;
import bigfight.model.warrior.database.Account;
import bigfight.model.warrior.database.WarriorDatabase;
import bigfight.model.weapon.WeaponManager;

public class NpcFactory {
    public static Warrior create(NpcIdentity identity, Account account, WarriorDatabase warriorDatabase) {
        switch (identity) {
            case NOOB:
                return createNoob(account, warriorDatabase);
            default:
                // should add exception handler
                return null;
        }
    }

    private static Warrior createNoob(Account account, WarriorDatabase warriorDatabase) {
        BasicAttribute strength = new BasicAttribute(2);
        BasicAttribute basicAttribute = new BasicAttribute(2);
        BasicAttribute speed = new BasicAttribute(2);
        BasicAttribute health = new BasicAttribute(50);
        WeaponManager weaponManager = new WeaponManager(); // no weapon
        SkillManager skillManager = new SkillManager(); // no skill
        return WarriorBuilder.stepBuilder(warriorDatabase)
                .account(account)
                .level(1)
                .strength(strength)
                .agility(basicAttribute)
                .speed(speed)
                .health(health)
                .weaponManager(weaponManager)
                .skillManager(skillManager)
                .friends(null)
                .build();
    }
}
