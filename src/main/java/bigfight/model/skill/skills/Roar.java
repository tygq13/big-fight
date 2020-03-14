package bigfight.model.skill.skills;

import bigfight.model.skill.struct.SkillStruct;

public class Roar extends SkillModel {
    private final int ROAR_ONE_DAMAGE = 15;
    private final int ROAR_ONE_IGNORE = 1;

    public Roar(SkillStruct skill) {
        super(skill);
    }

    public int getDamage() {
        return ROAR_ONE_DAMAGE;
    }

    public int getIgnore() {
        return ROAR_ONE_IGNORE;
    }
}
