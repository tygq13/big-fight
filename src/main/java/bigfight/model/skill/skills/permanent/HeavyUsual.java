package bigfight.model.skill.skills.permanent;

import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.struct.SkillStruct;
import bigfight.model.warrior.component.AdvancedAttribute;
import bigfight.model.warrior.component.Attribute;

public class HeavyUsual extends PermanentSkill{
    private final double HEAVY_USUAL_ZERO = 0.1;

    public HeavyUsual(SkillStruct skill) {super(skill);}

    public double getExtraDamage() {
        return HEAVY_USUAL_ZERO;
    }

    public void upgrade(AdvancedAttribute advancedAttribute) {
        advancedAttribute.bigExtraDamage += getExtraDamage();
    }

    @Override
    public void upgrade(Attribute attribute) {
        attribute.getAdvancedAttribute().bigExtraDamage += getExtraDamage();
    }
}
