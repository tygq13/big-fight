package bigfight.model.skill.skills;

import bigfight.model.skill.struct.SkillStruct;
import bigfight.model.warrior.component.BasicAttribute;

public class AStepAhead extends SkillModel {
    private final double A_STEP_AHEAD_ZERO_MULTIPLY = 0.5;
    private final int A_STEP_AHEAD_ZERO_ADDITION = 3;

    public AStepAhead(SkillStruct skill) {
        super(skill);
    }

    public double getMultiply() {
        return A_STEP_AHEAD_ZERO_MULTIPLY;
    }

    public int getAddition() {
        return A_STEP_AHEAD_ZERO_ADDITION;
    }


    public void upgrade(BasicAttribute speed) {
        int addition = (int) (speed.getBase() * A_STEP_AHEAD_ZERO_MULTIPLY + A_STEP_AHEAD_ZERO_ADDITION);
        speed.addToAddition(addition);
    }
}
