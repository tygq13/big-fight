package bigfight.model.warrior;

import bigfight.data.DataConfig;
import bigfight.data.DataGetter;

import java.lang.Integer;

import bigfight.model.warrior.component.Empowerment;
import bigfight.model.warrior.component.EmpowermentFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

class WarriorTest {
    private static DataGetter defaultDataGetter;
    private static EmpowermentFactory dummyEmpowermentFactory;

    @BeforeAll
    static void setUp() {
        WarriorTest.defaultDataGetter = new DataGetter(new DataConfig());
        WarriorTest.dummyEmpowermentFactory = mock(EmpowermentFactory.class);
    }

    // test involve random
    @Test
    void attribute_initialized_equal_to_attribute_total() {
        final int INITIAL_ATTRIBUTE_TOTAL = 20;

        DataGetter dataGetter = mock(DataGetter.class);
        when(dataGetter.getInitialAttributeTotal()).thenReturn(INITIAL_ATTRIBUTE_TOTAL);
        Warrior warrior = new Warrior(dataGetter, dummyEmpowermentFactory);
        int total = warrior.getSpeed() + warrior.getStrength() + warrior.getAgility();
        assertEquals(dataGetter.getInitialAttributeTotal(), total);
    }

    // test involve random
    @Test
    void attribute_initialized_equal_to_one_when_bad_attribute_total() {
        final int BAD_INITIAL = 2;

        DataGetter dataGetter = mock(DataGetter.class);
        when(dataGetter.getInitialAttributeTotal()).thenReturn(BAD_INITIAL);
        Warrior warrior = new Warrior(dataGetter, dummyEmpowermentFactory);
        assertEquals(1, warrior.getSpeed());
        assertEquals(1, warrior.getStrength());
        assertEquals(1, warrior.getAgility());
    }

    // test involve random
    @Test
    void attribute_initialzed_larger_than_zero_with_small_attribute_total() {
        final int SMALL_INITIAL_VALUE = 4;

        DataGetter dataGetter = mock(DataGetter.class);
        when(dataGetter.getInitialAttributeTotal()).thenReturn(SMALL_INITIAL_VALUE);
        Warrior warrior = new Warrior(dataGetter, dummyEmpowermentFactory);
        assertEquals(1, Integer.compare(warrior.getSpeed(), 0));
        assertEquals(1, Integer.compare(warrior.getStrength(), 0));
        assertEquals(1, Integer.compare(warrior.getAgility(), 0));
    }

    @Test
    void health_initialize_equal_to_health_data() {
        final int TEST_HEALTH = 65;
        DataGetter dataGetter = mock(DataGetter.class);
        when(dataGetter.getInitialHealth()).thenReturn(TEST_HEALTH);
        Warrior warrior = new Warrior(dataGetter, dummyEmpowermentFactory);
        assertEquals(TEST_HEALTH, warrior.getHealth());
    }

    @Test
    void weapons_is_initialized() {
        Warrior warrior = new Warrior(WarriorTest.defaultDataGetter, dummyEmpowermentFactory);
        assertNotNull(warrior.getWeaponManager());
    }

    @Test
    void level_is_initialized_equal_to_1() {
        Warrior warrior = new Warrior(WarriorTest.defaultDataGetter, dummyEmpowermentFactory);
        assertEquals(1, Integer.compare(warrior.getLevel(), 0));
    }

    @Test
    void skills_set_are_initialized() {
        Warrior warrior = new Warrior(WarriorTest.defaultDataGetter, dummyEmpowermentFactory);
        assertNotNull(warrior.getSkillManager());
    }

    @Test
    void first_weapon_or_skill_is_gotten() {
        Warrior warrior = new Warrior(WarriorTest.defaultDataGetter, dummyEmpowermentFactory);
        int result = warrior.getSkillManager().getSize() + warrior.getWeaponManager().getSize();
        assertEquals(1, result);
    }

    @Test
    void test_level_up_correct() {
        Warrior warrior = new Warrior(WarriorTest.defaultDataGetter, dummyEmpowermentFactory);
    }
}
