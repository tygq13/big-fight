package bigfight.model.skill.skills;

import bigfight.model.skill.struct.SkillStruct;

public class AStepAhead extends SkillModel {
    private final double A_STEP_AHEAD_ONE_MULTIPLY = 0.5;
    private final int A_STEP_AHEAD_ONE_ADDITION = 3;

    public AStepAhead(SkillStruct skill) {
        super(skill);
    }

    public double getMultiply() {
        return A_STEP_AHEAD_ONE_MULTIPLY;
    }

    public int getAddition() {
        return A_STEP_AHEAD_ONE_ADDITION;
    }

    public int upgrade(int base) {
        return base * (int) (getMultiply()) + getAddition();
    }
}
