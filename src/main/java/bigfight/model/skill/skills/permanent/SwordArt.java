package bigfight.model.skill.skills.permanent;

import bigfight.model.skill.struct.SkillStruct;
import bigfight.model.warrior.component.attr.Attribute;

public class SwordArt extends PermanentSkill{
    private final double MEDIUM_HIT_RATE = 0.01;

    public SwordArt(SkillStruct skill) {super(skill);}

    public double getHitRate() {
        return MEDIUM_HIT_RATE;
    }

    @Override
    public void upgrade(Attribute attribute) {
        attribute.getAdvancedAttribute().mediumHitRate += getHitRate();
    }
}
