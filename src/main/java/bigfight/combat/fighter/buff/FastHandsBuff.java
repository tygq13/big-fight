package bigfight.combat.fighter.buff;

import bigfight.combat.fighter.Fighter;
import bigfight.model.skill.skills.special.FastHands;

public class FastHandsBuff implements Buff{
    private FastHands fastHands;
    private boolean invoked = false;
    private boolean isEnd = false;

    public FastHandsBuff(FastHands fastHands) {
        this.fastHands = fastHands;
    }

    @Override
    public void invoke(Fighter fighter) {
        if (!invoked) {
            fighter.getAdvancedAttribute().doubleHitChance += fastHands.getDoubleHit();
            invoked = true;
        } else {
            fighter.getAdvancedAttribute().doubleHitChance -= fastHands.getDoubleHit();
            isEnd = true;
        }
    }

    @Override
    public boolean isEnd() {
        return isEnd;
    }
}
