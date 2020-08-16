package bigfight.model.skill.skills;

import bigfight.model.skill.struct.SkillStruct;

public class WindyKick extends SkillModel {
    private final int SPEED_ADDITION_ZERO = 10;
    private final double SPEED_DAMAGE_MULTIPLY_ZERO = 0.5;

    public WindyKick(SkillStruct skill) {
        super(skill);
    }

    public int getSpeedAddition() {
        return SPEED_ADDITION_ZERO;
    }

    public double getSpeedMultiply() {
        return SPEED_DAMAGE_MULTIPLY_ZERO;
    }
}
