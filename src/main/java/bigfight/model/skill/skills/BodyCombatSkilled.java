package bigfight.model.skill.skills;

import bigfight.model.skill.struct.SkillStruct;
import bigfight.model.warrior.component.AdvancedAttribute;

public class BodyCombatSkilled extends SkillModel {
    private final double WEAPONS_HANDY_ZERO = 0.2;

    public BodyCombatSkilled(SkillStruct skill) {
        super(skill);
    }

    public double getExtra() {
        return WEAPONS_HANDY_ZERO;
    }

    public void upgrade(AdvancedAttribute advancedAttribute) {
        advancedAttribute.unarmedExtraPercentageDamage += getExtra();
    }
}

