package bigfight.model.skill.skills.permanent;

import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.struct.SkillStruct;
import bigfight.model.warrior.component.AdvancedAttribute;
import bigfight.model.warrior.component.Attribute;

public class WeaponsHandy extends PermanentSkill {
    private final double WEAPONS_HANDY_ZERO = 0.2;

    public WeaponsHandy(SkillStruct skill) {
        super(skill);
    }

    public double getExtra() {
        return WEAPONS_HANDY_ZERO;
    }

    public void upgrade(AdvancedAttribute advancedAttribute) {
        advancedAttribute.bigExtraPercentageDamage += getExtra();
        advancedAttribute.mediumExtraPercentageDamage += getExtra();
        advancedAttribute.smallExtraPercentageDamage += getExtra();
        advancedAttribute.throwExtraPercentageDamage += getExtra();
    }

    @Override
    public void upgrade(Attribute attribute) {
        attribute.getAdvancedAttribute().bigExtraPercentageDamage += getExtra();
        attribute.getAdvancedAttribute().mediumExtraPercentageDamage += getExtra();
        attribute.getAdvancedAttribute().smallExtraPercentageDamage += getExtra();
        attribute.getAdvancedAttribute().throwExtraPercentageDamage += getExtra();
    }
}

