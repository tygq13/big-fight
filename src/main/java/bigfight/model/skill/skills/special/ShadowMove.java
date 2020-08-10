package bigfight.model.skill.skills.special;

import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.struct.SkillStruct;

public class ShadowMove extends SpecialSkill {
    final double SPEED_MULTIPLY = 0.5;
    final double DAMAGE_MULTIPLY = 0.3;
    final int MAX_ROUND = 3;
    final double INVOCATION_CHANCE_ZERO = 0.2;
    SkillStruct skill;

    public ShadowMove(SkillStruct skill) {
        super(skill);
        this.skill = skill;
    }

    public double getInvocationChance() {
        return INVOCATION_CHANCE_ZERO;
    }

    public double getSpeedMultiply() {
        return SPEED_MULTIPLY;
    }

    public double getDamageMultiply() {
        return DAMAGE_MULTIPLY;
    }

    public int getMaxRound() {
        return MAX_ROUND;
    }

    @Override
    public SkillModel getUsableInstance() {
        return new ShadowMoveUsable(skill, getSpeedMultiply(), getDamageMultiply(), getMaxRound(), getInvocationChance());
    }
}
