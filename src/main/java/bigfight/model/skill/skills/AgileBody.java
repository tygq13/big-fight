package bigfight.model.skill.skills;

import bigfight.model.skill.struct.SkillStruct;
import bigfight.model.warrior.component.Agility;

public class AgileBody extends SkillModel {
    private final double AGILE_BODY_ONE_MULTIPLY = 0.5;
    private final int AGILE_BODY_ONE_ADDITION = 3;

    public AgileBody(SkillStruct skill) {
        super(skill);
    }

    public double getMultiply() {
        return AGILE_BODY_ONE_MULTIPLY;
    }

    public int getAddition() {
        return AGILE_BODY_ONE_ADDITION;
    }

    public void upgrade(Agility agility) {
        // todo: got problem here
        int addition = (int) (agility.getBase() * AGILE_BODY_ONE_MULTIPLY + AGILE_BODY_ONE_ADDITION);
        agility.addToAddition(addition);
    }
}
