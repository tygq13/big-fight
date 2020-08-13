package bigfight.model.skill.skills.permanent;

import bigfight.model.skill.struct.SkillStruct;
import bigfight.model.warrior.component.Attribute;

public class AStepAhead extends PermanentSkill {
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

    @Override
    public void upgrade(Attribute attribute) {
        int addition = (int) (attribute.getSpeedObj().getBase() * A_STEP_AHEAD_ZERO_MULTIPLY + A_STEP_AHEAD_ZERO_ADDITION);
        attribute.getSpeedObj().addToAddition(addition);
    }
}
