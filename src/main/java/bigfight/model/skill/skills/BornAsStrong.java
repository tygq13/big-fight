package bigfight.model.skill.skills;

import bigfight.model.skill.struct.SkillStruct;

public class BornAsStrong extends SkillModel {
    private final double BORN_AS_STRONG_ONE_MULTIPLY = 0.5;
    private final int BRON_AS_STRONG_ONE_ADDITION = 3;

    public BornAsStrong(SkillStruct skill) {
        super(skill);
    }

    public double getMultiply() {
        return BORN_AS_STRONG_ONE_MULTIPLY;
    }

    public int getAddition() {
        return BRON_AS_STRONG_ONE_ADDITION;
    }

    public int upgrade(int base) {
        return (int) (base * (getMultiply()) + getAddition());
    }
}
