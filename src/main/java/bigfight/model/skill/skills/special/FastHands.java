package bigfight.model.skill.skills.special;

import bigfight.model.skill.struct.SkillStruct;

public class FastHands extends SpecialSkill {
    private final double FAST_HANDS_ZERO = 0.2;

    public FastHands(SkillStruct skill) {super(skill);}

    @Override
    public double getInvocationChance() {
            return FAST_HANDS_ZERO;
    }

    @Override
    public boolean isAuxiliary() {
        return true;
    }
}
