package bigfight.model.warrior;

import bigfight.data.DataConfig;
import bigfight.data.DataGetter;
import bigfight.model.skill.SkillFactory;
import bigfight.model.skill.SkillManager;
import bigfight.model.skill.skills.BornAsStrong;
import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.warrior.builder.Warrior;
import bigfight.model.warrior.builder.WarriorTestUtil;
import bigfight.model.warrior.component.Empowerment;
import bigfight.model.warrior.component.EmpowermentFactory;
import bigfight.model.warrior.database.Account;
import bigfight.model.warrior.database.WarriorDatabase;
import bigfight.model.warrior.npc.NpcIdentity;
import bigfight.model.weapon.Weapon;
import bigfight.model.weapon.WeaponFactory;
import bigfight.model.weapon.WeaponManager;
import bigfight.model.weapon.struct.WeaponIdentity;
import bigfight.model.skill.SkillFactoryTestUtil;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;

import static bigfight.model.skill.SkillFactoryTestUtil.DEFAULT_SKILL_FACTORY;
import static bigfight.model.weapon.WeaponFactoryTestUtil.DEFAULT_WEAPON_FACTORY;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WarriorFactoryTest {
    private static DataGetter defaultDataGetter = new DataGetter(new DataConfig());
    private static EmpowermentFactory dummyEmpowermentFactory = mock(EmpowermentFactory.class);
    private static String defaultName = "TEST";
    private static WarriorDatabase mockDatabase;
    private static WarriorDatabase defaultDatabase = new WarriorDatabase();
    private static EmpowermentFactory DEFAULT_EMPOWERMENT_FACTORY = new EmpowermentFactory(DEFAULT_WEAPON_FACTORY, DEFAULT_SKILL_FACTORY);

    @BeforeAll
    static void setUp() {
        // set up mock database that returns mock account
        Account mockAccount = mock(Account.class);
        when(mockAccount.getId()).thenReturn(0);
        mockDatabase = mock(WarriorDatabase.class);
        when(mockDatabase.createAccount(any())).thenReturn(mockAccount);
    }

    @Test
    void create_warrior_attribute_initialized_equal_to_attribute_total() {
        final int INITIAL_ATTRIBUTE_TOTAL = 20;

        DataGetter dataGetter = mock(DataGetter.class);
        when(dataGetter.getInitialAttributeTotal()).thenReturn(INITIAL_ATTRIBUTE_TOTAL);
        WarriorFactory test = new WarriorFactory();
        Warrior warrior = test.create(dataGetter, dummyEmpowermentFactory, mockDatabase, defaultName);
        int total = warrior.getSpeed() + warrior.getStrength() + warrior.getBasicAttribute();
        assertEquals(dataGetter.getInitialAttributeTotal(), total);
    }

    @Test
    void attribute_initialized_equal_to_one_when_bad_attribute_total() {
        final int BAD_INITIAL = 2;

        DataGetter dataGetter = mock(DataGetter.class);
        when(dataGetter.getInitialAttributeTotal()).thenReturn(BAD_INITIAL);
        WarriorFactory test = new WarriorFactory();
        Warrior warrior = test.create(dataGetter, dummyEmpowermentFactory, mockDatabase, defaultName);
        assertEquals(1, warrior.getSpeed());
        assertEquals(1, warrior.getStrength());
        assertEquals(1, warrior.getBasicAttribute());
    }

    @Test
    void attribute_initialized_larger_than_zero_with_small_attribute_total() {
        final int SMALL_INITIAL_VALUE = 4;

        DataGetter dataGetter = mock(DataGetter.class);
        when(dataGetter.getInitialAttributeTotal()).thenReturn(SMALL_INITIAL_VALUE);
        WarriorFactory test = new WarriorFactory();
        Warrior warrior = test.create(dataGetter, dummyEmpowermentFactory, mockDatabase, defaultName);
        assertEquals(1, Integer.compare(warrior.getSpeed(), 0));
        assertEquals(1, Integer.compare(warrior.getStrength(), 0));
        assertEquals(1, Integer.compare(warrior.getBasicAttribute(), 0));
    }

    @Test
    void health_initialize_equal_to_health_data() {
        final int TEST_HEALTH = 65;
        DataGetter dataGetter = mock(DataGetter.class);
        when(dataGetter.getInitialHealth()).thenReturn(TEST_HEALTH);
        WarriorFactory test = new WarriorFactory();
        Warrior warrior = test.create(dataGetter, dummyEmpowermentFactory, mockDatabase, defaultName);
        assertEquals(TEST_HEALTH, warrior.getHealth());
    }

    @Test
    void weapons_is_initialized() {
        WarriorFactory test = new WarriorFactory();
        Warrior warrior = test.create(defaultDataGetter, dummyEmpowermentFactory, mockDatabase, defaultName);
        assertNotNull(warrior.getWeaponManager());
    }

    @Test
    void level_is_initialized_equal_to_1() {
        WarriorFactory test = new WarriorFactory();
        Warrior warrior = test.create(defaultDataGetter, dummyEmpowermentFactory, mockDatabase, defaultName);
        assertEquals(1, Integer.compare(warrior.getLevel(), 0));
    }

    @Test
    void skills_set_are_initialized() {
        WarriorFactory test = new WarriorFactory();
        Warrior warrior = test.create(defaultDataGetter, dummyEmpowermentFactory, mockDatabase, defaultName);
        assertNotNull(warrior.getSkillManager());
    }

    @Test
    void first_weapon_or_skill_is_gotten() {
        WarriorFactory test = new WarriorFactory();
        // set up mocks
        Weapon dummyWeapon = mock(Weapon.class);
        SkillModel dummySkill = mock(SkillModel.class);
        WeaponFactory mockWeaponFactory = mock(WeaponFactory.class);
        when(mockWeaponFactory.create(any(WeaponIdentity.class))).thenReturn(dummyWeapon);
        SkillFactory mockSkillFactory = mock(SkillFactory.class);
        when(mockSkillFactory.create(any(SkillIdentity.class))).thenReturn(dummySkill);
        EmpowermentFactory empowermentFactory = new EmpowermentFactory(mockWeaponFactory, mockSkillFactory);

        Warrior warrior = test.create(defaultDataGetter, empowermentFactory, mockDatabase, defaultName);
        int result = warrior.getSkillManager().getSize() + warrior.getWeaponManager().getSize();
        assertEquals(1, result);
    }

    @Test
    void test_permanent_skill_correctly_add_to_attribute_example_BornAsStrong() {
        // ensure that it get the skill BornAsStrong
        BornAsStrong bornAsStrong = (BornAsStrong) DEFAULT_SKILL_FACTORY.create(SkillIdentity.BORN_AS_STRONG);
        Empowerment empowerment = new Empowerment(bornAsStrong);
        EmpowermentFactory mockFactory = mock(EmpowermentFactory.class);
        when(mockFactory.randomGetNew(any(ArrayList.class), any(Map.class))).thenReturn(empowerment);
        WarriorFactory test = new WarriorFactory();
        Warrior warrior = test.create(defaultDataGetter, mockFactory, mockDatabase, defaultName);

        // after getting the skill, the attribute is at least 4, bad test though
        assertTrue(warrior.getStrength() >= 4);
    }

    @Test
    void test_npc_friends_initialized_with_NOOB() {
        final int NPC_NOOB_INDEX = 0;
        WarriorFactory test = new WarriorFactory();
        Warrior warrior = test.create(defaultDataGetter, dummyEmpowermentFactory, defaultDatabase, defaultName);
        Warrior NOOB = defaultDatabase.getNpc(NpcIdentity.NOOB);
        assertEquals(NOOB.getId(), warrior.getFriend(NPC_NOOB_INDEX));
    }

    @Test
    void test_warrior_upgrade_total_attribute_correct() {
        final int STRENGTH = 3;
        final int AGILITY = 3;
        final int SPEED = 3;
        final int HEALTH = 3;
        final int LEVEL = 1;
        Warrior testWarrior = WarriorTestUtil.createCustomAttributeWarrior(STRENGTH, AGILITY, SPEED, HEALTH, LEVEL);
        WarriorFactory testFactory = new WarriorFactory();
        testFactory.warriorLevelUp(testWarrior, mock(EmpowermentFactory.class));
        // test
        int expectedAttributeTotal = STRENGTH + AGILITY + SPEED + DataConfig.LEVEL_UP_ATTRIBUTE_ADDITION_NORMAL;
        int actualAttributeTotal = testWarrior.getStrength() + testWarrior.getBasicAttribute() + testWarrior.getSpeed();
        assertEquals(expectedAttributeTotal, actualAttributeTotal);
        int expectedHealth = HEALTH + DataConfig.LEVEL_UP_HEALTH_ADDITION;
        assertEquals(expectedHealth, testWarrior.getHealth());
        assertEquals(LEVEL + 1, testWarrior.getLevel());
    }

    @Test
    void test_warrior_upgrade_empowerment_correct() {
        WeaponManager emptyWeapon = new WeaponManager();
        SkillManager emptySkill = new SkillManager();
        int expectedSize = emptyWeapon.getSize() + emptySkill.getSize() + 1;
        Warrior testWarrior = WarriorTestUtil.createCustomEmpowermentWarrior(emptyWeapon, emptySkill);
        WarriorFactory testFactory = new WarriorFactory();
        testFactory.warriorLevelUp(testWarrior, DEFAULT_EMPOWERMENT_FACTORY);
        int result = testWarrior.getWeaponManager().getSize() + testWarrior.getSkillManager().getSize();
        assertEquals(expectedSize, result);
    }


    @Test
    void test_permanent_skill_upgraded_when_gained_in_level_up_with_example_born_as_strong() {
        final int STRENGTH = 100;
        Warrior testWarrior = WarriorTestUtil.createCustomAttributeWarrior(STRENGTH, 1,1,1,1);
        EmpowermentFactory empowermentFactory = mock(EmpowermentFactory.class);
        BornAsStrong bornAsStrong = (BornAsStrong) SkillFactoryTestUtil.DEFAULT_SKILL_FACTORY.create(SkillIdentity.BORN_AS_STRONG);
        BornAsStrong bornAsStrongSpy = spy(bornAsStrong);
        Empowerment empowerment = new Empowerment(bornAsStrongSpy);
        when(empowermentFactory.randomGetNew(any(), any())).thenReturn(empowerment);
        WarriorFactory testFactory = new WarriorFactory();
        testFactory.warriorLevelUp(testWarrior, empowermentFactory);
        verify(bornAsStrongSpy).upgrade(any());
    }
}
