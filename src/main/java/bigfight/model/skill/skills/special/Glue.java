package bigfight.model.skill.skills.special;

import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.struct.SkillStruct;

public class Glue extends SpecialSkill {
    final double GLUE_CHANCE_ZERO = 0.1;

    public Glue(SkillStruct skill) {
        super(skill);
    }

    public double getGlueChance() {
        return GLUE_CHANCE_ZERO;
    }
}
