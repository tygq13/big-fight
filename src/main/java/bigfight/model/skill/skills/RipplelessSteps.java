package bigfight.model.skill.skills;

import bigfight.model.skill.struct.SkillStruct;
import bigfight.model.warrior.component.AdvancedAttribute;

public class RipplelessSteps extends SkillModel {
    private final double RIPPLELESS_STESPS_ONE = 0.07;

    public RipplelessSteps(SkillStruct skill) {super(skill);}

    public double getEvasion() {
        return RIPPLELESS_STESPS_ONE;
    }

    public void upgrade(AdvancedAttribute advancedAttribute) {
        advancedAttribute.bigEvasionRate += getEvasion();
        advancedAttribute.mediumEvasionRate += getEvasion();
        advancedAttribute.smallEvasionRate += getEvasion();
        advancedAttribute.throwEvasionRate += getEvasion();
        advancedAttribute.unarmedEvasionRate += getEvasion();
        advancedAttribute.skillEvasionRate += getEvasion();
    }
}
