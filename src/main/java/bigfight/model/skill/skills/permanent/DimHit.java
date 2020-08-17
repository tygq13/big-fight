package bigfight.model.skill.skills.permanent;

import bigfight.model.skill.struct.SkillStruct;
import bigfight.model.warrior.component.attr.Attribute;

public class DimHit extends PermanentSkill{
    private final double CRITICAL_CHANCE_ZERO = 0.15;

    public DimHit(SkillStruct skill) {super(skill);}

    public double getCriticalChance() {
        return CRITICAL_CHANCE_ZERO;
    }

    @Override
    public void upgrade(Attribute attribute) {
        attribute.getAdvancedAttribute().skillCriticalChance += getCriticalChance();
    }
}
