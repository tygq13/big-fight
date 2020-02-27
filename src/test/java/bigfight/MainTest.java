package bigfight;

import bigfight.data.DataGetter;
import bigfight.ui.Ui;
import bigfight.command.FightCommand;
import bigfight.model.warrior.WarriorFactory;
import bigfight.data.DataConfig;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MainTest {
    @Mock Ui ui;
    @Mock FightCommand FightCommand;
    @Mock WarriorFactory warriorFactory;
    @InjectMocks private Main mainRunning;

    @Test
    void ui_welcome() {
        mainRunning.run();
        verify(ui).showWelcome();
    }


    @Test
    void fight_command() {
        mainRunning.run();
        verify(FightCommand).execute();
    }

    @Test
    void character_initialized() {
        mainRunning.run();
        verify(warriorFactory, times(2)).create(any(DataGetter.class));
    }

    @Test
    void dataConfig_initialized() {
        Main main = new Main();
        assertNotNull(main.dataConfig);
    }

    @Test
    void modelFactory_initialized() {
        Main main = new Main();
        assertNotNull(main.warriorFactory);
    }
}
