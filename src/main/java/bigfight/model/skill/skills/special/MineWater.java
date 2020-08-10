package bigfight.model.skill.skills.special;

import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.struct.SkillStruct;

public class MineWater extends SpecialSkill {
    final double REGENERATE_PERCENTAGE_ZERO = 0.25;
    final double INVOCATION_CHANCE = 0.1;

    public MineWater(SkillStruct skill) {
        super(skill);
    }

    public double getRegeneratePercentage() {
        return REGENERATE_PERCENTAGE_ZERO;
    }

    public double getInvocationChance() {
        return INVOCATION_CHANCE;
    }
}
