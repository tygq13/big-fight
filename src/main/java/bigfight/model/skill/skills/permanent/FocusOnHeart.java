package bigfight.model.skill.skills.permanent;

import bigfight.model.skill.struct.SkillStruct;
import bigfight.model.warrior.component.attr.Attribute;

public class FocusOnHeart extends PermanentSkill{
    private final double HIT_RATE_ZERO = 0.03;

    public FocusOnHeart(SkillStruct skill) {super(skill);}

    public double getHitRate() {
        return HIT_RATE_ZERO;
    }

    @Override
    public void upgrade(Attribute attribute) {
        attribute.getAdvancedAttribute().skillHitRate += getHitRate();
    }
}
