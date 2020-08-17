package bigfight.model.skill.skills;

import bigfight.model.skill.struct.SkillStruct;

public class StinkyFeet extends SkillModel {
    private final int DAMAGE_ZERO = 30;
    private final double STRENGTH_MULTIPLY_ZERO = 0.5;
    private final double SAME_SEX_MULTIPLY = 0.1;

    public StinkyFeet(SkillStruct skill) {
        super(skill);
    }

    public int getDamage() {
        return DAMAGE_ZERO;
    }

    public double getSameSexMultiply() {
        return SAME_SEX_MULTIPLY;
    }

    public double getStrengthMultiply() {
        return STRENGTH_MULTIPLY_ZERO;
    }
}
