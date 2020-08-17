package bigfight.model.skill.skills.permanent;

import bigfight.model.skill.struct.SkillStruct;
import bigfight.model.warrior.component.attr.Attribute;

public class ValiantForce extends PermanentSkill{
    private final double BIT_HIT_RATE = 0.01;

    public ValiantForce(SkillStruct skill) {super(skill);}

    public double getHitRate() {
        return BIT_HIT_RATE;
    }

    @Override
    public void upgrade(Attribute attribute) {
        attribute.getAdvancedAttribute().bigHitRate += getHitRate();
    }
}
