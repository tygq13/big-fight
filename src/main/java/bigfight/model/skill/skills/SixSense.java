package bigfight.model.skill.skills;

import bigfight.model.skill.struct.SkillStruct;
import bigfight.model.warrior.component.AdvancedAttribute;

public class SixSense extends SkillModel {
    private final double SIX_SENSE_ZERO = 0.3;

    public SixSense(SkillStruct skill) {
        super(skill);
    }

    public double getChance() {
        return SIX_SENSE_ZERO;
    }

    public void upgrade(AdvancedAttribute advancedAttribute) {
        advancedAttribute.counterAttackChance = getChance();
    }
}
