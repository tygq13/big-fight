package bigfight.model.skill.skills;

import bigfight.combat.fighter.buff.Buff;
import bigfight.combat.fighter.buff.GhostOnDebuff;
import bigfight.model.skill.struct.SkillStruct;

public class GhostOn extends SkillModel{
    private final double HIT_RATE_ZERO = 0.1;
    private final int BASE_DAMAGE = 30;
    private final double LEVEL_MULTIPLY = 0.4;

    private final int ROUND_NUM_ZERO = 2;
    private final double REDUCTION_ZERO = 0.2;


    public GhostOn(SkillStruct skillStruct) {
        super(skillStruct);
    }

    public int getDamage() {
        return BASE_DAMAGE;
    }

    public double getExtraHitRate() {
        return HIT_RATE_ZERO;
    }

    public double getLevelMultiply() {
        return LEVEL_MULTIPLY;
    }

    public int getRounds() {
        return ROUND_NUM_ZERO;
    }

    public double getReduction() {
        return REDUCTION_ZERO;
    }

    public Buff createDebuff() {
        return new GhostOnDebuff(this);
    }
}
