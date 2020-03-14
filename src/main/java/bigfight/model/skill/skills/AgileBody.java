package bigfight.model.skill.skills;

import bigfight.model.skill.struct.SkillStruct;

public class AgileBody extends SkillModel {
    private final double AGILE_BODY_ONE_MULTIPLY = 0.5;
    private final int AGILE_BODY_ONE_ADDITION = 3;

    public AgileBody(SkillStruct skill) {
        super(skill);
    }

    public double getMultiply() {
        return AGILE_BODY_ONE_MULTIPLY;
    }

    public int getAddition() {
        return AGILE_BODY_ONE_ADDITION;
    }

    public int upgrade(int base) {
        return base * (int) (getMultiply()) + getAddition();
    }
}
