package bigfight.model.skill.skills;

import bigfight.model.skill.struct.SkillStruct;

public class FastHands extends SkillModel {
    private final double FAST_HANDS_ZERO = 0.2;

    public FastHands(SkillStruct skill) {super(skill);}

    public double getExtraChance() {
            return FAST_HANDS_ZERO;
    }
}
