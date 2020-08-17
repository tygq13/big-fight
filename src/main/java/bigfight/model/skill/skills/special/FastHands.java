package bigfight.model.skill.skills.special;

import bigfight.combat.fighter.buff.Buff;
import bigfight.combat.fighter.buff.FastHandsBuff;
import bigfight.model.skill.struct.SkillStruct;

public class FastHands extends SpecialSkill {
    private final double FAST_HANDS_ZERO = 0.2;
    private final double DOUBLE_HIT = 1.0;

    public FastHands(SkillStruct skill) {super(skill);}

    public double getDoubleHit() {
        return DOUBLE_HIT;
    }

    @Override
    public double getInvocationChance() {
            return FAST_HANDS_ZERO;
    }

    public Buff createBuff() {
        return new FastHandsBuff(this);
    }

}
