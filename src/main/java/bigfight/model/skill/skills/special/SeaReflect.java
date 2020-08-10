package bigfight.model.skill.skills.special;

import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.struct.SkillStruct;

public class SeaReflect extends SpecialSkill {

    private double INVOCATION_CHANCE = 0.2; // not sure about the real data in original game
    private double REFLECT_PERCENTAGE_ZERO = 1.0;
    private int MAX_INVOCATION = 1;
    SkillStruct skill;

    public SeaReflect(SkillStruct skill) {
        super(skill);
        this.skill = skill;
    }

    public double getReflectPercentage() {
        return REFLECT_PERCENTAGE_ZERO;
    }

    public double getInvocationChance() {
        return INVOCATION_CHANCE;
    }

    @Override
    public SkillModel getUsableInstance() {
        return new SeaReflectUsable(skill, MAX_INVOCATION, getReflectPercentage(), getInvocationChance());
    }
}
