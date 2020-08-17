package bigfight.model.skill.skills.permanent;

import bigfight.model.skill.struct.SkillStruct;
import bigfight.model.warrior.component.attr.Attribute;

public class AgileBody extends PermanentSkill {
    private final double AGILE_BODY_ZERO_MULTIPLY = 0.5;
    private final int AGILE_BODY_ZERO_ADDITION = 3;

    public AgileBody(SkillStruct skill) {
        super(skill);
    }

    public double getMultiply() {
        return AGILE_BODY_ZERO_MULTIPLY;
    }

    public int getAddition() {
        return AGILE_BODY_ZERO_ADDITION;
    }

    @Override
    public void upgrade(Attribute attribute) {
        int addition = (int) (attribute.getAgilityObj().getBase() * AGILE_BODY_ZERO_MULTIPLY + AGILE_BODY_ZERO_ADDITION);
        attribute.getAgilityObj().addToAddition(addition);
    }
}
