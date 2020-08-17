package bigfight.model.skill.skills.special;

import bigfight.model.skill.struct.SkillStruct;

public class BloodSacrifice extends SpecialSkill{
    final double INVOCATION_CHANCE_ZERO = 0.2;
    final double LIFE_STEAL_ZERO = 0.1;

    public BloodSacrifice(SkillStruct skill) {
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
