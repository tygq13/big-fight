package bigfight.model.skill.skills.special;

import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.struct.SkillStruct;

public class Heal extends SpecialSkill{
    final int BASE_HEAL_ZERO = 30;
    final double LEVEL_MULTIPLY_ZERO = 0.4;
    final int MAX_INVOCATION = 1;
    SkillStruct skill;

    public Heal(SkillStruct skill) {
        super(skill);
        this.skill = skill;
    }

    private int getBaseHeal() {
        return BASE_HEAL_ZERO;
    }

    private double getLevelMultiply() {
        return LEVEL_MULTIPLY_ZERO;
    }

    private int getMaxInvocation() {
        return MAX_INVOCATION;
    }

    @Override
    public SkillModel getUsableInstance() {
        return new HealUsable(skill, getMaxInvocation(), getBaseHeal(), getLevelMultiply());
    }
}
