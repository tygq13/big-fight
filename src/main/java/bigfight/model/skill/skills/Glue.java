package bigfight.model.skill.skills;

import bigfight.model.skill.struct.SkillStruct;

public class Glue extends SkillModel{
    final double GLUE_CHANCE_ZERO = 0.1;

    public Glue(SkillStruct skill) {
        super(skill);
    }

    public double getGlueChance() {
        return GLUE_CHANCE_ZERO;
    }
}
