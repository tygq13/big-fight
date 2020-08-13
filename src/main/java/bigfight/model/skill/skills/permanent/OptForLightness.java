package bigfight.model.skill.skills.permanent;

import bigfight.model.skill.struct.SkillStruct;
import bigfight.model.warrior.component.Attribute;

public class OptForLightness extends PermanentSkill{
    private final double SMALL_MEDIUM_ZERO = 0.15;
    private final double BIG_EVASION = 0.2;

    public OptForLightness(SkillStruct skill) {super(skill);}

    public double getSmallMeiumExtraDamage() {
        return SMALL_MEDIUM_ZERO;
    }

    public double getBigEvasion() {
        return BIG_EVASION;
    }

    @Override
    public void upgrade(Attribute attribute) {
        attribute.getAdvancedAttribute().mediumExtraPercentageDamage += getSmallMeiumExtraDamage();
        attribute.getAdvancedAttribute().smallExtraPercentageDamage += getSmallMeiumExtraDamage();
        attribute.getAdvancedAttribute().bigEvasionRate += getBigEvasion();
    }
}
