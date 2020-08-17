package bigfight.model.skill.skills.special;

import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.struct.SkillStruct;

public class SeaReflectUsable extends SkillModel {
    private int remainingInvocation;
    private double reflectPercentage;
    private double invocationChance;

    public SeaReflectUsable(SkillStruct skillStruct, int maxInvocation, double reflectPercentage, double invocationChance) {
        super(skillStruct);
        remainingInvocation = maxInvocation;
        this.reflectPercentage = reflectPercentage;
        this.invocationChance = invocationChance;
    }

    public double getReflectPercentage() {
        return reflectPercentage;
    }

    public double getRemainingUsage() {
        return remainingInvocation;
    }

    public void invoke() {
        remainingInvocation -= 1;
    }

    public double getInvocationChance() {
        return invocationChance;
    }
}
