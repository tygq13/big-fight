package bigfight.algo;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

class BigFightAlgoTest {

    @Test
    void uniform_random_distribution_with_sum_equal_to_total() {
        int testSize = 5;
        int total = 23;
        int[] actual = BigFightAlgo.uniformRandomDistribute(testSize, total);
        assertEquals(total, Arrays.stream(actual).sum());
    }
}
