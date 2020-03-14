package bigfight;

import bigfight.data.DataGetter;
import bigfight.model.warrior.component.EmpowermentFactory;
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
