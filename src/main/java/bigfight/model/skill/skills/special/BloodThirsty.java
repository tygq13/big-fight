package bigfight.model.skill.skills.special;

import bigfight.model.skill.struct.SkillStruct;

public class BloodThirsty extends SpecialSkill{
    final double INVOCATION_CHANCE_ZERO = 0.25;
    final double LIFE_STEAL_ZERO = 1.0 / 3.0;

    public BloodThirsty(SkillStruct skill) {
        super(skill);
    }

    @Override
    public double getInvocationChance() {
        return INVOCATION_CHANCE_ZERO;
    }

    public double getLifeStealPercentage() {
        return LIFE_STEAL_ZERO;
    }
}
