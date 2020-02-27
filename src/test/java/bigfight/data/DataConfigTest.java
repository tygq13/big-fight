package bigfight.data;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DataConfigTest {

    @Test
    void initial_attribute_total_initialized() {
        DataConfig dataConfig = new DataConfig();
        assertNotEquals(0, dataConfig.getInitialAttributeTotal());
    }
}
