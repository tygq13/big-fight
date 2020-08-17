package bigfight.model.skill.skills;

import bigfight.model.skill.struct.SkillStruct;

public class Acupointer extends SkillModel {
    private final int DAMAGE_ZERO = 30;
    private final double LEVEL_MULTIPLY_ZERO = 0.3;
    private final double HIT_RATE_INCREMENT_ZERO = 0.15;
    private final int NO_SKILL_ROUND_NUM_ZERO = 2;

    public Acupointer(SkillStruct skill) {
        super(skill);
    }

    public int getDamage() {
        return DAMAGE_ZERO;
    }

    public double getLevelMultiply() {
        return LEVEL_MULTIPLY_ZERO;
    }

    public double getExtraHitRate() {
        return HIT_RATE_INCREMENT_ZERO;
    }

    public int getNoSKillRounds() {
        return NO_SKILL_ROUND_NUM_ZERO;
    }
}
