package bigfight.model.warrior.builder;

import bigfight.model.skill.SkillManager;
import bigfight.model.warrior.component.*;
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
        final Strength strengthInit = new Strength(strength);
        final Agility agilityInit = new Agility(agility);
        final Speed speedInit = new Speed(speed);
        final Health healthInit = new Health(health);
        final WeaponManager weaponManager = new WeaponManager();
        final SkillManager skillManager = new SkillManager();
        final Friends friends = new Friends();

        Warrior warrior = WarriorBuilder.stepBuilder(mock(WarriorDatabase.class))
                .account(account)
                .level(LEVEL)
                .strength(strengthInit)
                .agility(agilityInit)
                .speed(speedInit)
                .health(healthInit)
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
        final Strength strengthInit = new Strength(1);
        final Agility agilityInit = new Agility(1);
        final Speed speedInit = new Speed(1);
        final Health healthInit = new Health(1);
        final Friends friends = new Friends();

        Warrior warrior = WarriorBuilder.stepBuilder(mock(WarriorDatabase.class))
                .account(account)
                .level(LEVEL)
                .strength(strengthInit)
                .agility(agilityInit)
                .speed(speedInit)
                .health(healthInit)
                .weaponManager(weaponManager)
                .skillManager(skillManager)
                .friends(friends)
                .build();
        return warrior;
    }
}
