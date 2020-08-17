package bigfight.model.skill.skills.permanent;

import bigfight.model.skill.struct.SkillStruct;
import bigfight.model.warrior.component.attr.Attribute;

public class QiGong extends PermanentSkill{
    private final double REDUCE_SKILL_DAMAGE_ZERO = 0.05;

    public QiGong(SkillStruct skill) {super(skill);}

    public double getReduceDamage() {
        return REDUCE_SKILL_DAMAGE_ZERO;
    }

    @Override
    public void upgrade(Attribute attribute) {
        attribute.getAdvancedAttribute().antiSkillExtraPercentageDamage += getReduceDamage();
    }
}
