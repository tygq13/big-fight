package bigfight.model.skill.skills;

import bigfight.model.skill.struct.SkillStruct;

public class Tornado extends SkillModel {
    private final int TORNADO_DAMAGE = 20;
    private final double STRENGTH_MULTIPLE_ZERO = 0.8;

    public Tornado(SkillStruct skill) {
        super(skill);
    }

    public int getDamage() {
        return TORNADO_DAMAGE;
    }

    public double getStrengthMultiply() {
        return STRENGTH_MULTIPLE_ZERO;
    }
}
