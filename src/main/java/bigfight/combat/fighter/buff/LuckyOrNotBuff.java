package bigfight.combat.fighter.buff;

import bigfight.combat.fighter.Fighter;
import bigfight.model.skill.skills.LuckyOrNot;
import bigfight.model.skill.skills.special.FastHands;

public class LuckyOrNotBuff implements Buff{
    private double skillHitRate;
    private boolean invoked;
    private boolean isEnd;

    public LuckyOrNotBuff(LuckyOrNot luckyOrNot) {
        skillHitRate = luckyOrNot.getExtraHitRate();
        invoked = false;
        isEnd = false;
    }

    @Override
    public void invoke(Fighter fighter) {
        if (!invoked) {
            fighter.getAdvancedAttribute().skillHitRate += skillHitRate;
            invoked = true;
        } else if (!isEnd) {
            fighter.getAdvancedAttribute().skillHitRate -= skillHitRate;
            isEnd = true;
        }
    }

    @Override
    public boolean isEnd() {
        return isEnd;
    }
}
