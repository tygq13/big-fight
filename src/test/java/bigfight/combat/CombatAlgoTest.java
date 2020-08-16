package bigfight.combat;

import bigfight.combat.util.CombatAlgo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CombatAlgoTest {

    @Test
    void ignoreBySpeed_bound_correct() {
        final int SMALLER = 5;
        final int LARGER = 100;
        double smallToLarger = CombatAlgo.ignoreBySpeed(SMALLER, LARGER);
        assertTrue(smallToLarger >= 0);
        assertTrue(smallToLarger <= 1);
        double largerToSmall = CombatAlgo.ignoreBySpeed(LARGER, SMALLER);
        assertTrue(largerToSmall >= 0);
        assertTrue(largerToSmall <= 1);
    }

    @Test
    void escapeByAgility_bound_correct() {
        final int SMALLER = 5;
        final int LARGER = 100;
        double smallToLarger = CombatAlgo.escapeByAgility(SMALLER, LARGER);
        assertTrue(smallToLarger >= 0);
        assertTrue(smallToLarger <= 1);
        double largerToSmall = CombatAlgo.escapeByAgility(LARGER, SMALLER);
        assertTrue(largerToSmall >= 0);
        assertTrue(largerToSmall <= 1);
    }

    @Test
    void extraDamageByAttribute_bound_correct() {
        final int SMALLER = 5;
        final int LARGER = 10;
        double smallToLarger = CombatAlgo.extraDamageByAttribute(SMALLER, LARGER);
        assertEquals(0, smallToLarger);
        double largerToSmall = CombatAlgo.extraDamageByAttribute(LARGER, SMALLER);
        assertEquals(LARGER - SMALLER, largerToSmall);
    }
}
