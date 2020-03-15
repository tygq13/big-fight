package bigfight.model.skill.skills;

import bigfight.model.skill.struct.SkillStruct;

public class ApparentDeath extends SkillModel {
    private final int APPARENT_DEATH_ONE_HEALTH = 1;

    public ApparentDeath(SkillStruct skill) {
        super(skill);
    }

    public int getLastHealth() {
        return APPARENT_DEATH_ONE_HEALTH;
    }
}