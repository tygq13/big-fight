package bigfight.model.skill.skills;

import bigfight.model.skill.struct.SkillStruct;

public class BoltFromTheBlue extends SkillModel {
    private final int BOLT_FROM_THE_BLUE_ONE_DAMAGE = 15;
    private final double BOLT_FROM_THE_BLUE_LEVEL_MULTIPLY = 1.5;

    public BoltFromTheBlue(SkillStruct skill) {
        super(skill);
    }

    public int getDamage() {
        return BOLT_FROM_THE_BLUE_ONE_DAMAGE;
    }

    public double getLevelMultiply() {
        return BOLT_FROM_THE_BLUE_LEVEL_MULTIPLY;
    }
}
