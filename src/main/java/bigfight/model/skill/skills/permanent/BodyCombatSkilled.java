package bigfight.model.skill.skills.permanent;

import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.struct.SkillStruct;
import bigfight.model.warrior.component.AdvancedAttribute;
import bigfight.model.warrior.component.Attribute;

public class BodyCombatSkilled extends PermanentSkill {
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

    @Override
    public void upgrade(Attribute attribute) {
        attribute.getAdvancedAttribute().unarmedExtraPercentageDamage += getExtra();
    }
}

