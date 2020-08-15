package bigfight.model.skill.skills;

import bigfight.model.skill.struct.SkillStruct;

public class OnePunch extends SkillModel {
    private double INVOCATION_CHANCE_ONE = 0.08;

    public OnePunch(SkillStruct skill) {
        super(skill);
    }

    public double getInvocationChance() {
        return INVOCATION_CHANCE_ONE;
    }
}
