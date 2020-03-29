package bigfight.logic.command;

import bigfight.model.warrior.builder.Warrior;

import bigfight.ui.Uiable;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class FightCommandTest {

    @Test
    void test_fight_command_with_friend_successful() {
        final int FRIEND_INDEX = 0;
        Warrior mainCharacter = mock(Warrior.class);
        Warrior opponent = mock(Warrior.class);
        when(mainCharacter.getFriend(FRIEND_INDEX)).thenReturn(0);
        FightCommand test = new FightCommand(FRIEND_INDEX);
        mainCharacter.execute(test, mock(Uiable.class));
    }
}
