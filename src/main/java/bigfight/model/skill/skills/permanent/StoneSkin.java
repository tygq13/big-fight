package bigfight.model.skill.skills.permanent;

import bigfight.model.skill.struct.SkillStruct;
import bigfight.model.warrior.component.AdvancedAttribute;
import bigfight.model.warrior.component.Attribute;

public class StoneSkin extends PermanentSkill {
    private final double STONE_SKIN_ZERO = 0.2;

    public StoneSkin(SkillStruct skill) {
        super(skill);
    }

    public double getExtra() {
        return STONE_SKIN_ZERO;
    }

    @Override
    public void upgrade(Attribute attribute) {
        attribute.getAdvancedAttribute().antiBigExtraPercentageDamage += 0.2;
        attribute.getAdvancedAttribute().antiMediumExtraPercentageDamage += 0.2;
        attribute.getAdvancedAttribute().antiSmallExtraPercentageDamage += 0.2;
        attribute.getAdvancedAttribute().antiThrowExtraPercentageDamage += 0.2;
        attribute.getAdvancedAttribute().antiUnarmedExtraPercentageDamage += 0.2;
    }
}
