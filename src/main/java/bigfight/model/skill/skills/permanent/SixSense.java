package bigfight.model.skill.skills.permanent;

import bigfight.model.skill.struct.SkillStruct;
import bigfight.model.warrior.component.attr.Attribute;

public class SixSense extends PermanentSkill {
    private final double SIX_SENSE_ZERO = 0.3;

    public SixSense(SkillStruct skill) {
        super(skill);
    }

    public double getChance() {
        return SIX_SENSE_ZERO;
    }

    @Override
    public void upgrade(Attribute attribute) {
        attribute.getAdvancedAttribute().counterAttackChance = getChance();
    }
}
