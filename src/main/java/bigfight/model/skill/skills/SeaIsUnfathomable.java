package bigfight.model.skill.skills;

import bigfight.model.skill.struct.SkillStruct;

public class SeaIsUnfathomable extends SkillModel {

    private double INVOCATION_CHANCE = 0.2; // not sure about the real data in original game
    private double REFLECT_PERCENTAGE_ONE = 1.0;
    private int MAX_INVOCATION = 1;

    int remaining;

    public SeaIsUnfathomable(SkillStruct skill) {
        super(skill);
        remaining = MAX_INVOCATION;
    }

    public double getReflectPercentage() {
        return REFLECT_PERCENTAGE_ONE;
    }

    public double getRemainingUsage() {
        return remaining;
    }

    public void invoke() {
        remaining -= 1;
    }

    public double getInvocationChance() {
        return INVOCATION_CHANCE;
    }
}
