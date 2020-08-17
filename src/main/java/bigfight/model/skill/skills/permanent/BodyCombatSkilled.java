package bigfight.model.skill.skills.permanent;

import bigfight.model.skill.struct.SkillStruct;
import bigfight.model.warrior.component.attr.Attribute;

public class BodyCombatSkilled extends PermanentSkill {
    private final double WEAPONS_HANDY_ZERO = 0.2;

    public BodyCombatSkilled(SkillStruct skill) {
        super(skill);
    }

    public double getExtra() {
        return WEAPONS_HANDY_ZERO;
    }

    @Override
    public void upgrade(Attribute attribute) {
        attribute.getAdvancedAttribute().unarmedExtraPercentageDamage += getExtra();
    }
}

