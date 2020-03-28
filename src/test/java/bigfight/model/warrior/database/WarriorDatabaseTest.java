package bigfight.model.warrior.database;

import bigfight.model.warrior.builder.Warrior;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WarriorDatabaseTest {
    private static WarriorDatabase test = new WarriorDatabase();

    @Test
    void test_create_account_are_not_equal(){
        String NAME = "TEST";
        Account account1 = test.createAccount(NAME);
        Account account2 = test.createAccount(NAME);
        assertNotEquals(account1, account2);
    }

    @Test
    void test_new_account_added_to_database() {
        int initial = test.size();
        String NAME = "TEST";
        test.createAccount(NAME);
        int EXPECTED = initial + 1;
        assertEquals(EXPECTED, test.size());
    }
}
