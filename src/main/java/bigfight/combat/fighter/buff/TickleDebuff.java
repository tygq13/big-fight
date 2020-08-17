package bigfight.combat.fighter.buff;

import bigfight.combat.fighter.Fighter;
import bigfight.model.skill.skills.Tickle;

public class TickleDebuff implements Buff{
    private int damage;
    private int rounds;

    public TickleDebuff(Tickle tickle, int damage) {
        this.damage = damage;
        rounds = tickle.getMaxRounds();
    }

    @Override
    public void invoke(Fighter fighter) {
        if (rounds > 0) {
            fighter.updateHealth(fighter.getHealth() - damage);
            rounds -= 1;
        }
    }

    @Override
    public boolean isEnd() {
        return rounds == 0;
    }
}
