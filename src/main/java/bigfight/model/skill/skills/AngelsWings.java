package bigfight.model.skill.skills;

import bigfight.model.skill.struct.SkillStruct;

public class AngelsWings extends SkillModel {
    private final int ANGELS_WINGS_ZERO_DAMAGE = 15;
    private final double AGILITY_MULTIPLE_ZERO = 1.0;

    public AngelsWings(SkillStruct skill) {
        super(skill);
    }

    public int getDamage() {
        return ANGELS_WINGS_ZERO_DAMAGE;
    }

    public double getAgilityMultiply() {
        return AGILITY_MULTIPLE_ZERO;
    }
}
