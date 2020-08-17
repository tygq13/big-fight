package bigfight.model.skill.skills;

import bigfight.model.skill.struct.SkillStruct;

public class Dash extends SkillModel {
    private final int DASH_ZERO_DAMAGE = 15;
    private final int DASH_ZERO_IGNORE = 1;
    private final double DASH_ZERO_SPEED_MULTIPLY = .05;

    public Dash(SkillStruct skill) {
        super(skill);
    }

    public int getDamage() {
        return DASH_ZERO_DAMAGE;
    }

    public int getIgnore() {
        return DASH_ZERO_IGNORE;
    }

    public double getSpeedMultiply() {
        return DASH_ZERO_SPEED_MULTIPLY;
    }
}
