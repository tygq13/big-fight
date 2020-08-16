package bigfight.model.skill.skills.permanent;

import bigfight.model.skill.struct.SkillStruct;
import bigfight.model.warrior.component.attr.Attribute;

public class WeaponsHandy extends PermanentSkill {
    private final double WEAPONS_HANDY_ZERO = 0.2;

    public WeaponsHandy(SkillStruct skill) {
        super(skill);
    }

    public double getExtra() {
        return WEAPONS_HANDY_ZERO;
    }

    @Override
    public void upgrade(Attribute attribute) {
        attribute.getAdvancedAttribute().bigExtraPercentageDamage += getExtra();
        attribute.getAdvancedAttribute().mediumExtraPercentageDamage += getExtra();
        attribute.getAdvancedAttribute().smallExtraPercentageDamage += getExtra();
        attribute.getAdvancedAttribute().throwExtraPercentageDamage += getExtra();
    }
}

