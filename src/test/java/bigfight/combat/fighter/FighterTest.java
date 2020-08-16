package bigfight.combat.fighter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FighterTest {

    @Test
    void update_health_not_exceed_maximum() {
        Fighter fighter = new FighterBuilderTestUtil().build();
        final int EXPECTED = fighter.getHealth();
        fighter.updateHealth(fighter.getHealth() + 1);
        assertEquals(EXPECTED, fighter.getHealth());
    }
}
