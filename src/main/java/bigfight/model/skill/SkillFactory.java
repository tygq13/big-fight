package bigfight.model.skill;

import bigfight.model.skill.SkillModel;
import bigfight.model.skill.struct.SkillIdentity;

public class SkillFactory {
    private SkillData skillData;

    public SkillFactory(SkillData data) {
        skillData = data;
    }

    public SkillModel create(SkillIdentity identity) {
        return new SkillModel(skillData.getWithStar(identity, 1));
    }
}
