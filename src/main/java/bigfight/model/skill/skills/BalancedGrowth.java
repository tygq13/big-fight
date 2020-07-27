package bigfight.model.skill.skills;

import bigfight.model.skill.struct.SkillStruct;
import bigfight.model.warrior.component.BasicAttribute;

public class BalancedGrowth extends SkillModel {
    private final double BALANCED_GROWTH_ONE_MULTIPLY = 0.2;
    private final int BALANCED_GROWTH_ONE_ADDITION = 1;

    public BalancedGrowth(SkillStruct skill) {
        super(skill);
    }

    public double getMultiply() {
        return BALANCED_GROWTH_ONE_MULTIPLY;
    }

    public int getAddition() {
        return BALANCED_GROWTH_ONE_ADDITION;
    }


    public void upgrade(BasicAttribute strength, BasicAttribute agility, BasicAttribute speed) {
        int addition;
        addition = (int) (strength.getBase() * BALANCED_GROWTH_ONE_MULTIPLY + BALANCED_GROWTH_ONE_ADDITION);
        strength.addToAddition(addition);
        addition = (int) (agility.getBase() * BALANCED_GROWTH_ONE_MULTIPLY + BALANCED_GROWTH_ONE_ADDITION);
        agility.addToAddition(addition);
        addition = (int) (speed.getBase() * BALANCED_GROWTH_ONE_MULTIPLY + BALANCED_GROWTH_ONE_ADDITION);
        speed.addToAddition(addition);
    }
}
