package bigfight.model.skill.skills;

import bigfight.model.skill.struct.SkillStruct;

public class HakiProtect extends SkillModel {
    private final int MAX_USAGE = 2;
    private final double INVOKE_CHANCE_ONE = 0.15;
    private final double PROTECTION_PERCENTAGE_ONE = 0.5;

    private int remaining;

    public HakiProtect(SkillStruct skill) {
        super(skill);
        remaining = MAX_USAGE;
    }

    public int getRemainingUsage() {
        return remaining;
    }

    public double getInvokeChance() {
        return INVOKE_CHANCE_ONE;
    }

    public double getProtectionPercentage() {
        return PROTECTION_PERCENTAGE_ONE;
    }

    public void invoke() {
        remaining -= 1;
    }
}
