package bigfight.model.warrior.builder;

import bigfight.model.skill.SkillManager;
import bigfight.model.warrior.component.Agility;
import bigfight.model.warrior.component.Friends;
import bigfight.model.warrior.component.Speed;
import bigfight.model.warrior.component.Strength;
import bigfight.model.warrior.database.Account;
import bigfight.model.warrior.database.WarriorDatabase;
import bigfight.model.weapon.WeaponManager;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WarriorBuilderTest {

    @Test
    void build_warrior_successful() {
        final Account account = mock(Account.class);
        when(account.getName()).thenReturn("TEST");
        final Strength STRENGTH = new Strength();
        final Agility AGILITY = new Agility();
        final Speed SPEED = new Speed();
        final int HEALTH = 10;
        final WeaponManager WEAPON_MANAGER = new WeaponManager();
        final SkillManager SKILL_MANAGER = new SkillManager();
        final Friends friends = new Friends();

        Warrior test = WarriorBuilder.stepBuilder(mock(WarriorDatabase.class))
                .account(account)
                .strength(STRENGTH)
                .agility(AGILITY)
                .speed(SPEED)
                .health(HEALTH)
                .weaponManager(WEAPON_MANAGER)
                .skillManager(SKILL_MANAGER)
                .friends(friends)
                .build();
        assertNotNull(test);
        assertEquals(account.getName(), test.getName());
        assertEquals(STRENGTH.getBase(), test.getStrength());
        assertEquals(AGILITY.getBase(), test.getAgility());
        assertEquals(SPEED.getBase(), test.getSpeed());
        assertEquals(HEALTH, test.getHealth());
        assertNotNull(test.getWeaponManager());
        assertNotNull(test.getSkillManager());
        assertNotNull(test.getFriends());
    }

    @Test
    void test_warrior_build_is_inserted_into_database() {
        WarriorDatabase warriorDatabase = new WarriorDatabase();
        final Account account = warriorDatabase.createAccount("TEST");

        WarriorBuilder.stepBuilder(warriorDatabase)
                .account(account)
                .strength(mock(Strength.class))
                .agility(mock(Agility.class))
                .speed(mock(Speed.class))
                .health(0)
                .weaponManager(mock(WeaponManager.class))
                .skillManager(mock(SkillManager.class))
                .friends(mock(Friends.class))
                .build();

        assertNotNull(warriorDatabase.get(account.getId()));
    }
}
