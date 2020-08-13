package bigfight.model.skill.skills;

import bigfight.model.skill.struct.SkillStruct;

public class FoshanKick extends SkillModel {
    private final int FOSHAN_DAMAGE = 30;
    private final double STRENGTH_MULTIPLE_ZERO = 0.5;

    public FoshanKick(SkillStruct skill) {
        super(skill);
    }

    public int getDamage() {
        return FOSHAN_DAMAGE;
    }

    public double getStrengthMultiply() {
        return STRENGTH_MULTIPLE_ZERO;
    }
}
