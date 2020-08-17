package bigfight.model.skill.skills.special;

import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.struct.SkillStruct;

public class HakiProtectUsable extends SkillModel {
    private int remainingInvocation;
    private double protectionPercentage;
    private double invocationChance;

    public HakiProtectUsable(SkillStruct skillStruct, int maxInvocation, double protectionPercentage, double invocationChance) {
        super(skillStruct);
        remainingInvocation = maxInvocation;
        this.protectionPercentage = protectionPercentage;
        this.invocationChance = invocationChance;
    }

    public double getProtectionPercentage() {
        return protectionPercentage;
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
