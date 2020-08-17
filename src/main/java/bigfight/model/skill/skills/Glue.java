package bigfight.model.skill.skills;

import bigfight.model.skill.skills.special.SpecialSkill;
import bigfight.model.skill.struct.SkillStruct;

public class Glue extends SkillModel {
    final double GLUE_ZERO_CHANCE = 0.1;

    public Glue(SkillStruct skill) {
        super(skill);
    }

    public double getInvocationChance() {
        return GLUE_ZERO_CHANCE;
    }
}
