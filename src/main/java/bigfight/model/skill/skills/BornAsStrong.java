package bigfight.model.skill.skills;

import bigfight.model.skill.struct.SkillStruct;
import bigfight.model.warrior.component.BasicAttribute;

public class BornAsStrong extends SkillModel {
    private final double BORN_AS_STRONG_ONE_MULTIPLY = 0.5;
    private final int BORN_AS_STRONG_ONE_ADDITION = 3;

    public BornAsStrong(SkillStruct skill) {
        super(skill);
    }

    public double getMultiply() {
        return BORN_AS_STRONG_ONE_MULTIPLY;
    }

    public int getAddition() {
        return BORN_AS_STRONG_ONE_ADDITION;
    }

    public void upgrade(BasicAttribute strength) {
        int addition = (int) (strength.getBase() * BORN_AS_STRONG_ONE_MULTIPLY + BORN_AS_STRONG_ONE_ADDITION);
        strength.addToAddition(addition);
    }
}
