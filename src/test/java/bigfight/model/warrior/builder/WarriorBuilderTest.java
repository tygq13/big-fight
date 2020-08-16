package bigfight.model.warrior.builder;

import bigfight.model.skill.SkillManager;
import bigfight.model.warrior.component.*;
import bigfight.model.warrior.component.attr.BasicAttribute;
import bigfight.model.warrior.component.attr.Attribute;
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
        final int LEVEL = 1;
        final BasicAttribute strength = new BasicAttribute();
        final BasicAttribute agility = new BasicAttribute();
        final BasicAttribute speed = new BasicAttribute();
        final BasicAttribute health = new BasicAttribute(10);
        final WeaponManager weaponManager = new WeaponManager();
        final SkillManager skillManager = new SkillManager();
        final Attribute attribute = new Attribute(strength, agility, speed, health);
        final Friends friends = new Friends();

        Warrior test = WarriorBuilder.stepBuilder(mock(WarriorDatabase.class))
                .account(account)
                .level(LEVEL)
                .attribute(attribute)
                .weaponManager(weaponManager)
                .skillManager(skillManager)
                .friends(friends)
                .build();
        assertNotNull(test);
        assertEquals(account.getName(), test.getName());
        assertEquals(LEVEL, test.getLevel());
        assertEquals(strength.getBase(), test.getStrength());
        assertEquals(agility.getBase(), test.getAgility());
        assertEquals(speed.getBase(), test.getSpeed());
        assertEquals(health.getBase(), test.getHealth());
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
                .level(1)
                .attribute(mock(Attribute.class))
                .weaponManager(mock(WeaponManager.class))
                .skillManager(mock(SkillManager.class))
                .friends(mock(Friends.class))
                .build();

        assertNotNull(warriorDatabase.get(account.getId()));
    }
}
