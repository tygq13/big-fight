package bigfight.model.skill.skills;

import bigfight.model.skill.struct.SkillStruct;

public class Shake extends SkillModel {
    private final int SHAKE_ZERO_DAMAGE = 25;
    private final int SHAKE_ZERO_DISPOSE_NUM = 1;

    public Shake(SkillStruct skill) {
        super(skill);
    }

    public int getDamage() {
        return SHAKE_ZERO_DAMAGE;
    }

    public int getDisposeNum() {
        return SHAKE_ZERO_DISPOSE_NUM;
    }
}
