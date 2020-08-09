package bigfight.model.skill.skills.permanent;

import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.struct.SkillStruct;
import bigfight.model.warrior.component.AdvancedAttribute;
import bigfight.model.warrior.component.Attribute;

public class RipplelessSteps extends PermanentSkill {
    private final double RIPPLELESS_STESPS_ZERO = 0.07;

    public RipplelessSteps(SkillStruct skill) {super(skill);}

    public double getEvasion() {
        return RIPPLELESS_STESPS_ZERO;
    }

    public void upgrade(AdvancedAttribute advancedAttribute) {
        advancedAttribute.bigEvasionRate += getEvasion();
        advancedAttribute.mediumEvasionRate += getEvasion();
        advancedAttribute.smallEvasionRate += getEvasion();
        advancedAttribute.throwEvasionRate += getEvasion();
        advancedAttribute.unarmedEvasionRate += getEvasion();
        advancedAttribute.skillEvasionRate += getEvasion();
    }

    @Override
    public void upgrade(Attribute attribute) {
        attribute.getAdvancedAttribute().bigEvasionRate += getEvasion();
        attribute.getAdvancedAttribute().mediumEvasionRate += getEvasion();
        attribute.getAdvancedAttribute().smallEvasionRate += getEvasion();
        attribute.getAdvancedAttribute().throwEvasionRate += getEvasion();
        attribute.getAdvancedAttribute().unarmedEvasionRate += getEvasion();
        attribute.getAdvancedAttribute().skillEvasionRate += getEvasion();
    }
}
