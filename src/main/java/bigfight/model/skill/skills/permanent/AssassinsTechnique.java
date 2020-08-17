package bigfight.model.skill.skills.permanent;

import bigfight.model.skill.struct.SkillStruct;
import bigfight.model.warrior.component.attr.Attribute;

public class AssassinsTechnique extends PermanentSkill{
    private final double SMALL_THROW_HIT_RATE = 0.01;

    public AssassinsTechnique(SkillStruct skill) {super(skill);}

    public double getHitRate() {
        return SMALL_THROW_HIT_RATE;
    }

    @Override
    public void upgrade(Attribute attribute) {
        attribute.getAdvancedAttribute().smallHitRate += getHitRate();
        attribute.getAdvancedAttribute().throwHitRate += getHitRate();
    }
}
