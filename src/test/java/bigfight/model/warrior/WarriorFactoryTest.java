package bigfight.model.warrior;

import bigfight.data.DataConfig;
import bigfight.data.DataGetter;
import bigfight.model.skill.SkillData;
import bigfight.model.skill.SkillFactory;
import bigfight.model.skill.skills.BornAsStrong;
import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.warrior.builder.Warrior;
import bigfight.model.warrior.component.Empowerment;
import bigfight.model.warrior.component.EmpowermentFactory;
import bigfight.model.weapon.Weapon;
import bigfight.model.weapon.WeaponFactory;
import bigfight.model.weapon.struct.WeaponIdentity;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WarriorFactoryTest {
    private static DataGetter defaultDataGetter = new DataGetter(new DataConfig());
    private static EmpowermentFactory dummyEmpowermentFactory = mock(EmpowermentFactory.class);
    private static SkillData defaultSkillData = new SkillData();
    private static SkillFactory defaultSkillFactory = new SkillFactory(defaultSkillData);
    private static String defaultName = "TEST";

    @Test
    void create_warrior_attribute_initialized_equal_to_attribute_total() {
        final int INITIAL_ATTRIBUTE_TOTAL = 20;

        DataGetter dataGetter = mock(DataGetter.class);
        when(dataGetter.getInitialAttributeTotal()).thenReturn(INITIAL_ATTRIBUTE_TOTAL);
        WarriorFactory test = new WarriorFactory();
        Warrior warrior = test.create(dataGetter, dummyEmpowermentFactory, defaultName);
        int total = warrior.getSpeed() + warrior.getStrength() + warrior.getAgility();
        assertEquals(dataGetter.getInitialAttributeTotal(), total);
    }

    @Test
    void attribute_initialized_equal_to_one_when_bad_attribute_total() {
        final int BAD_INITIAL = 2;

        DataGetter dataGetter = mock(DataGetter.class);
        when(dataGetter.getInitialAttributeTotal()).thenReturn(BAD_INITIAL);
        WarriorFactory test = new WarriorFactory();
        Warrior warrior = test.create(dataGetter, dummyEmpowermentFactory, defaultName);
        assertEquals(1, warrior.getSpeed());
        assertEquals(1, warrior.getStrength());
        assertEquals(1, warrior.getAgility());
    }

    @Test
    void attribute_initialized_larger_than_zero_with_small_attribute_total() {
        final int SMALL_INITIAL_VALUE = 4;

        DataGetter dataGetter = mock(DataGetter.class);
        when(dataGetter.getInitialAttributeTotal()).thenReturn(SMALL_INITIAL_VALUE);
        WarriorFactory test = new WarriorFactory();
        Warrior warrior = test.create(dataGetter, dummyEmpowermentFactory, defaultName);
        assertEquals(1, Integer.compare(warrior.getSpeed(), 0));
        assertEquals(1, Integer.compare(warrior.getStrength(), 0));
        assertEquals(1, Integer.compare(warrior.getAgility(), 0));
    }

    @Test
    void health_initialize_equal_to_health_data() {
        final int TEST_HEALTH = 65;
        DataGetter dataGetter = mock(DataGetter.class);
        when(dataGetter.getInitialHealth()).thenReturn(TEST_HEALTH);
        WarriorFactory test = new WarriorFactory();
        Warrior warrior = test.create(dataGetter, dummyEmpowermentFactory, defaultName);
        assertEquals(TEST_HEALTH, warrior.getHealth());
    }

    @Test
    void weapons_is_initialized() {
        WarriorFactory test = new WarriorFactory();
        Warrior warrior = test.create(defaultDataGetter, dummyEmpowermentFactory, defaultName);
        assertNotNull(warrior.getWeaponManager());
    }

    @Test
    void level_is_initialized_equal_to_1() {
        WarriorFactory test = new WarriorFactory();
        Warrior warrior = test.create(defaultDataGetter, dummyEmpowermentFactory, defaultName);
        assertEquals(1, Integer.compare(warrior.getLevel(), 0));
    }

    @Test
    void skills_set_are_initialized() {
        WarriorFactory test = new WarriorFactory();
        Warrior warrior = test.create(defaultDataGetter, dummyEmpowermentFactory, defaultName);
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

        Warrior warrior = test.create(defaultDataGetter, empowermentFactory, defaultName);
        int result = warrior.getSkillManager().getSize() + warrior.getWeaponManager().getSize();
        assertEquals(1, result);
    }

    @Test
    void test_permanent_skill_correctly_add_to_attribute_example_BornAsStrong() {
        // ensure that it get the skill BornAsStrong
        BornAsStrong bornAsStrong = (BornAsStrong) defaultSkillFactory.create(SkillIdentity.BORN_AS_STRONG);
        Empowerment empowerment = new Empowerment(bornAsStrong);
        EmpowermentFactory mockFactory = mock(EmpowermentFactory.class);
        when(mockFactory.randomGetNew(any(ArrayList.class), any(Map.class))).thenReturn(empowerment);
        WarriorFactory test = new WarriorFactory();
        Warrior warrior = test.create(defaultDataGetter, mockFactory, defaultName);

        // after getting the skill, the attribute is at least 4, bad test though
        assertTrue(warrior.getStrength() >= 4);
    }
}
