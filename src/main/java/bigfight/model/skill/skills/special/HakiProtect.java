package bigfight.model.skill.skills.special;

import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.struct.SkillStruct;

public class HakiProtect extends SpecialSkill {
    private final int MAX_INVOCATION = 2;
    private final double INVOKE_CHANCE_ZERO = 0.15;
    private final double PROTECTION_PERCENTAGE_ZERO = 0.5;
    private SkillStruct skill;

    private int remaining;

    public HakiProtect(SkillStruct skill) {
        super(skill);
        this.skill = skill;
        remaining = MAX_INVOCATION;
    }

    public int getRemainingUsage() {
        return remaining;
    }

    public double getInvocationChance() {
        return INVOKE_CHANCE_ZERO;
    }

    public double getProtectionPercentage() {
        return PROTECTION_PERCENTAGE_ZERO;
    }

    public void invoke() {
        remaining -= 1;
    }

    @Override
    public SkillModel getUsableInstance() {
        return new HakiProtectUsable(skill, MAX_INVOCATION, getProtectionPercentage(), getInvocationChance());
    }
}
