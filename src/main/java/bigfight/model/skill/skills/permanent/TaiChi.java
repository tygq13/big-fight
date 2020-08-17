package bigfight.model.skill.skills.permanent;

import bigfight.model.skill.struct.SkillStruct;
import bigfight.model.warrior.component.attr.Attribute;

public class TaiChi extends PermanentSkill{
    private final double TAI_CHI_ZERO = 0.05;

    public TaiChi(SkillStruct skill) {super(skill);}

    public double getHitRate() {
        return TAI_CHI_ZERO;
    }

    @Override
    public void upgrade(Attribute attribute) {
        attribute.getAdvancedAttribute().unarmedHitRate += getHitRate();
    }
}
