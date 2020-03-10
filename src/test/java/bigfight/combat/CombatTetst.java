package bigfight.combat;

import bigfight.combat.fighter.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CombatTetst {

    @Test
    void Combat_correctly_return_outcome() {
        Fighter fighter1 = mock(Fighter.class);
        Fighter fighter2 = mock(Fighter.class);
        Combat combat = new Combat(fighter1, fighter2);
        assertTrue(combat.start());
    }

}
