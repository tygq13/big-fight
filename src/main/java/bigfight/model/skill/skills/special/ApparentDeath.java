package bigfight.model.skill.skills.special;

import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.struct.SkillStruct;

public class ApparentDeath extends SpecialSkill {
    private final int APPARENT_DEATH_ZERO_HEALTH = 1;

    public ApparentDeath(SkillStruct skill) {
        super(skill);
    }

    public int getLastHealth() {
        return APPARENT_DEATH_ZERO_HEALTH;
    }
}
