package bigfight.model.skill.skills.permanent;

import bigfight.model.skill.struct.SkillStruct;
import bigfight.model.warrior.component.attr.Attribute;

public class BornAsStrong extends PermanentSkill {
    private final double BORN_AS_STRONG_ZERO_MULTIPLY = 0.5;
    private final int BORN_AS_STRONG_ZERO_ADDITION = 3;

    public BornAsStrong(SkillStruct skill) {
        super(skill);
    }

    public double getMultiply() {
        return BORN_AS_STRONG_ZERO_MULTIPLY;
    }

    public int getAddition() {
        return BORN_AS_STRONG_ZERO_ADDITION;
    }

    @Override
    public void upgrade(Attribute attribute) {
        int addition = (int) (attribute.getStrengthObj().getBase() * BORN_AS_STRONG_ZERO_MULTIPLY + BORN_AS_STRONG_ZERO_ADDITION);
        attribute.getStrengthObj().addToAddition(addition);
    }
}
