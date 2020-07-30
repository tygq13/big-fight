package bigfight.model.skill.skills;

import bigfight.model.skill.struct.SkillStruct;
import bigfight.model.warrior.component.AdvancedAttribute;

public class HeavyUsual extends SkillModel {
    private final double HEAVY_USUAL_ZERO = 0.1;

    public HeavyUsual(SkillStruct skill) {super(skill);}

    public double getExtraDamage() {
        return HEAVY_USUAL_ZERO;
    }

    public void upgrade(AdvancedAttribute advancedAttribute) {
        advancedAttribute.bigExtraDamage += getExtraDamage();
    }
}
