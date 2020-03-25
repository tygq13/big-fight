package bigfight;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

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
