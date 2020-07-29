package bigfight.model.skill.skills;

import bigfight.model.skill.struct.SkillStruct;

public class FastHands extends SkillModel {
    private final double EXTRA_CHANCE_ONE = 0.2;

    public FastHands(SkillStruct skill) {super(skill);}

    public double getExtraChance() {
        return EXTRA_CHANCE_ONE;
    }
}
