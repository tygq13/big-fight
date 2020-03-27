package bigfight.model.warrior;

import bigfight.model.warrior.builder.Warrior;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class WarriorListTest {

    @Test
    void find_by_warrior_name_successful() {
        final String NAME_ONE = "test1";
        final String NAME_TWO = "test2";
        Warrior warrior1 = mock(Warrior.class);
        when(warrior1.getName()).thenReturn(NAME_ONE);
        Warrior warrior2 = mock(Warrior.class);
        when(warrior2.getName()).thenReturn(NAME_TWO);
        WarriorList test = new WarriorList();
        test.add(warrior1);
        test.add(warrior2);

        //test
        Warrior EXPECTED = test.find(NAME_ONE);
        assertEquals(EXPECTED.getName(), NAME_ONE);
    }

    @Test
    void find_by_warrior_name_fail_return_null() {
        final String NON_EXISTENT_NAME = "test";
        WarriorList test = new WarriorList();
        assertNull(test.find(NON_EXISTENT_NAME));
    }
}
