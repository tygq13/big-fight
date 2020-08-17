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

    @Test
    void newRound_clean_up_flag() {
        Fighter fighter = new FighterBuilderTestUtil().build();
        fighter.getFighterFlag().noSelectSkill = 1;
        // test
        final int EXPECTED_NO_SELECT_SKILL = 0;
        final int EXPECTED_ROUND = 1;
        fighter.newRoundUpdate();
        assertEquals(EXPECTED_NO_SELECT_SKILL, fighter.getFighterFlag().noSelectSkill);
        assertEquals(EXPECTED_ROUND, fighter.getFighterFlag().rounds);
    }
}
