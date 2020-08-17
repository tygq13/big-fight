package bigfight.model.skill.skills;

import bigfight.model.skill.struct.SkillStruct;

public class Tornado extends SkillModel {
    private final int TORNADO_ZERO_DAMAGE = 20;
    private final double TORNADO_ZERO_STRENGTH_MULTIPLE = 0.8;

    public Tornado(SkillStruct skill) {
        super(skill);
    }

    public int getDamage() {
        return TORNADO_ZERO_DAMAGE;
    }

    public double getStrengthMultiply() {
        return TORNADO_ZERO_STRENGTH_MULTIPLE;
    }
}
