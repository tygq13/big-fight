package bigfight.model.skill.skills;

import bigfight.model.skill.struct.SkillStruct;

public class ShockWave extends SkillModel{
    private final int BASE_DAMAGE_ZERO = 40;
    private final int INCREMENT_DAMAGE_ZERO = 1;
    private final double INCREMENT_HIT_RATE = 0.02;
    SkillStruct skillStruct;

    public ShockWave(SkillStruct skillStruct) {
        super(skillStruct);
        this.skillStruct = skillStruct;
    }

    public int getDamage() {
        return BASE_DAMAGE_ZERO;
    }

    public int getDamageIncrement() {
        return INCREMENT_DAMAGE_ZERO;
    }

    public double getHitRateIncrement() {
        return INCREMENT_HIT_RATE;
    }
}
