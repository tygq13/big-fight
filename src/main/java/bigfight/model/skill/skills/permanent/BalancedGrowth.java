package bigfight.model.skill.skills.permanent;

import bigfight.model.skill.struct.SkillStruct;
import bigfight.model.warrior.component.attr.Attribute;

public class BalancedGrowth extends PermanentSkill {
    private final double BALANCED_GROWTH_ZERO_MULTIPLY = 0.2;
    private final int BALANCED_GROWTH_ZERO_ADDITION = 1;

    public BalancedGrowth(SkillStruct skill) {
        super(skill);
    }

    public double getMultiply() {
        return BALANCED_GROWTH_ZERO_MULTIPLY;
    }

    public int getAddition() {
        return BALANCED_GROWTH_ZERO_ADDITION;
    }

    @Override
    public void upgrade(Attribute attribute) {
        int addition;
        addition = (int) (attribute.getStrengthObj().getBase() * BALANCED_GROWTH_ZERO_MULTIPLY + BALANCED_GROWTH_ZERO_ADDITION);
        attribute.getStrengthObj().addToAddition(addition);
        addition = (int) (attribute.getAgilityObj().getBase() * BALANCED_GROWTH_ZERO_MULTIPLY + BALANCED_GROWTH_ZERO_ADDITION);
        attribute.getAgilityObj().addToAddition(addition);
        addition = (int) (attribute.getSpeedObj().getBase() * BALANCED_GROWTH_ZERO_MULTIPLY + BALANCED_GROWTH_ZERO_ADDITION);
        attribute.getSpeedObj().addToAddition(addition);
    }
}
