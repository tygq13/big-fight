package bigfight.model.skill.skills;

import bigfight.model.skill.struct.SkillStruct;
import bigfight.model.warrior.component.BasicAttribute;

public class AgileBody extends SkillModel {
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


    public void upgrade(BasicAttribute agility) {
        int addition = (int) (agility.getBase() * AGILE_BODY_ZERO_MULTIPLY + AGILE_BODY_ZERO_ADDITION);
        agility.addToAddition(addition);
    }
}
