package bigfight.model.skill.skills.permanent;

import bigfight.model.skill.struct.SkillStruct;
import bigfight.model.warrior.component.attr.Attribute;

public class RipplelessSteps extends PermanentSkill {
    private final double RIPPLELESS_STESPS_ZERO = 0.07;

    public RipplelessSteps(SkillStruct skill) {super(skill);}

    public double getEvasion() {
        return RIPPLELESS_STESPS_ZERO;
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
