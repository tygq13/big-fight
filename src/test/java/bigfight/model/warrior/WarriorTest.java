package bigfight.model.warrior;

import bigfight.data.DataConfig;
import bigfight.data.DataGetter;
import bigfight.model.skill.SkillManager;

import java.lang.Integer;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

class WarriorTest {
    private static DataGetter defaultDataGetter;

    @BeforeAll
    static void setUp() {
        WarriorTest.defaultDataGetter = new DataGetter(new DataConfig());
    }

    // test involve random
    @Test
    void attribute_initialized_equal_to_attribute_total() {
        final int INITIAL_ATTRIBUTE_TOTAL = 20;

        DataGetter dataGetter = mock(DataGetter.class);
        when(dataGetter.getInitialAttributeTotal()).thenReturn(INITIAL_ATTRIBUTE_TOTAL);
        Warrior warrior = new Warrior(dataGetter);
        int total = warrior.getSpeed() + warrior.getStrength() + warrior.getAgility();
        assertEquals(dataGetter.getInitialAttributeTotal(), total);
    }

    // test involve random
    @Test
    void attribute_initialized_equal_to_one_when_bad_attribute_total() {
        final int BAD_INITIAL = 2;

        DataGetter dataGetter = mock(DataGetter.class);
        when(dataGetter.getInitialAttributeTotal()).thenReturn(BAD_INITIAL);
        Warrior warrior = new Warrior(dataGetter);
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
        Warrior warrior = new Warrior(dataGetter);
        assertEquals(1, Integer.compare(warrior.getSpeed(), 0));
        assertEquals(1, Integer.compare(warrior.getStrength(), 0));
        assertEquals(1, Integer.compare(warrior.getAgility(), 0));
    }

    @Test
    void weapons_is_initialized() {
        Warrior warrior = new Warrior(WarriorTest.defaultDataGetter);
        assertNotNull(warrior.getWeaponList());
    }

    @Test
    void level_is_initialized_larger_than_1() {
        Warrior warrior = new Warrior(WarriorTest.defaultDataGetter);
        assertEquals(1, Integer.compare(warrior.getLevel(), 0));
    }

    @Test
    void skills_set_are_initialized() {
        Warrior warrior = new Warrior(WarriorTest.defaultDataGetter);
        assertNotNull(warrior.getSkillManager());
    }

}
