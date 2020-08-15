package bigfight.model.skill.skills;

import bigfight.combat.fighter.buff.Buff;
import bigfight.combat.fighter.buff.TickleDebuff;
import bigfight.model.skill.struct.SkillStruct;

public class Tickle extends SkillModel {
    private final int TICKLE_DAMAGE_ONE = 5;
    private final double TICKLE_AGILITY_MULTIPLY_ONE = 0.2;
    private final int TICKLE_ROUND = 6;

    public Tickle(SkillStruct skillStruct) {
        super(skillStruct);
    }

    public int getDamage() {
        return TICKLE_DAMAGE_ONE;
    }

    public double getAgilityMultiply() {
        return TICKLE_AGILITY_MULTIPLY_ONE;
    }

    public int getMaxRounds() {
        return TICKLE_ROUND;
    }

    public Buff createBuff(int damage) {
        return new TickleDebuff(this, damage);
    }
}
