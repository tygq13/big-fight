package bigfight.model.skill.skills.special;

import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.struct.SkillStruct;

public class FastHands extends SpecialSkill {
    private final double FAST_HANDS_ZERO = 0.2;

    public FastHands(SkillStruct skill) {super(skill);}

    public double getExtraChance() {
            return FAST_HANDS_ZERO;
    }
}
