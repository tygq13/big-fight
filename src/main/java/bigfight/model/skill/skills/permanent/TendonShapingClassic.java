package bigfight.model.skill.skills.permanent;

import bigfight.model.skill.struct.SkillStruct;
import bigfight.model.warrior.component.attr.Attribute;

public class TendonShapingClassic extends PermanentSkill {
    private final double ACTIVE_SKILL_EXTRA_PERCENTAGE_DAMAGE_ZERO = 0.2;

    public TendonShapingClassic(SkillStruct skill) {
        super(skill);
    }

    public double getExtraPercentageDamage() {
        return ACTIVE_SKILL_EXTRA_PERCENTAGE_DAMAGE_ZERO;
    }

    @Override
    public void upgrade(Attribute attribute) {
        attribute.getAdvancedAttribute().skillExtraPercentageDamage += getExtraPercentageDamage();
    }
}
