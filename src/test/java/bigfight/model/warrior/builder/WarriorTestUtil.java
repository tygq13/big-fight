package bigfight.model.warrior.builder;

import bigfight.model.skill.SkillManager;
import bigfight.model.warrior.component.*;
import bigfight.model.warrior.component.BasicAttribute;
import bigfight.model.warrior.database.Account;
import bigfight.model.warrior.database.WarriorDatabase;
import bigfight.model.weapon.WeaponManager;

import static org.mockito.Mockito.*;

import static org.mockito.Mockito.mock;

public class WarriorTestUtil {

    public static Warrior createCustomAttributeWarrior(int strength, int agility, int speed, int health, int level) {
        final Account account = mock(Account.class);
        when(account.getName()).thenReturn("TEST");
        final int LEVEL = level;
        final BasicAttribute strengthInit = new BasicAttribute(strength);
        final BasicAttribute agilityInit = new BasicAttribute(agility);
        final BasicAttribute speedInit = new BasicAttribute(speed);
        final BasicAttribute healthInit = new BasicAttribute(health);
        final WeaponManager weaponManager = new WeaponManager();
        final SkillManager skillManager = new SkillManager();
        final Attribute attribute = new Attribute(strengthInit, agilityInit, speedInit, healthInit);
        final Friends friends = new Friends();

        Warrior warrior = WarriorBuilder.stepBuilder(mock(WarriorDatabase.class))
                .account(account)
                .level(LEVEL)
                .attribute(attribute)
                .weaponManager(weaponManager)
                .skillManager(skillManager)
                .friends(friends)
                .build();
        return warrior;
    }

    public static Warrior createCustomEmpowermentWarrior(WeaponManager weaponManager, SkillManager skillManager) {
        final Account account = mock(Account.class);
        when(account.getName()).thenReturn("TEST");
        final int LEVEL = 1;
        final BasicAttribute strengthInit = new BasicAttribute(1);
        final BasicAttribute agilityInit = new BasicAttribute(1);
        final BasicAttribute speedInit = new BasicAttribute(1);
        final BasicAttribute healthInit = new BasicAttribute(1);
        final Attribute attribute = new Attribute(strengthInit, agilityInit, speedInit, healthInit);
        final Friends friends = new Friends();

        Warrior warrior = WarriorBuilder.stepBuilder(mock(WarriorDatabase.class))
                .account(account)
                .level(LEVEL)
                .attribute(attribute)
                .weaponManager(weaponManager)
                .skillManager(skillManager)
                .friends(friends)
                .build();
        return warrior;
    }
}
