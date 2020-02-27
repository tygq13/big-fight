package bigfight.data;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DataGetterTest {

    @Test
    void get_initial_attribute_total() {
        DataConfig dataConfig = new DataConfig();
        DataGetter dataGetter = new DataGetter(dataConfig);
        assertEquals(dataConfig.getInitialAttributeTotal(), dataGetter.getInitialAttributeTotal());
    }
}
