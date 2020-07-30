package bigfight.model.skill.skills;

import bigfight.model.skill.struct.SkillStruct;
import bigfight.model.warrior.component.AdvancedAttribute;
import bigfight.model.warrior.component.BasicAttribute;

public class StoneSkin extends SkillModel {
    private final double STONE_SKIN_ONE = 0.2;

    public StoneSkin(SkillStruct skill) {
        super(skill);
    }

    public double getExtra() {
        return STONE_SKIN_ONE;
    }

    // in fact better to change according to skill flag. but can skip since it is simple
    public void upgrade(AdvancedAttribute advancedAttribute) {
        advancedAttribute.antiBigExtraPercentageDamage += 0.2;
        advancedAttribute.antiMediumExtraPercentageDamage += 0.2;
        advancedAttribute.antiSmallExtraPercentageDamage += 0.2;
        advancedAttribute.antiThrowExtraPercentageDamage += 0.2;
        advancedAttribute.antiUnarmedExtraPercentageDamage += 0.2;
    }
}
