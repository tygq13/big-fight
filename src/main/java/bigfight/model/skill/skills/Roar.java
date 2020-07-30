package bigfight.model.skill.skills;

import bigfight.model.skill.struct.SkillStruct;

public class Roar extends SkillModel {
    private final int ROAR_ZERO_DAMAGE = 15;
    private final int ROAR_ZERO_IGNORE = 1;

    public Roar(SkillStruct skill) {
        super(skill);
    }

    public int getDamage() {
        return ROAR_ZERO_DAMAGE;
    }

    public int getIgnore() {
        return ROAR_ZERO_IGNORE;
    }
}
