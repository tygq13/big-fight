package bigfight.combat.fighter.buff;

import bigfight.combat.fighter.Fighter;

public interface Buff {
    void invoke(Fighter fighter);
    boolean isEnd();
}
