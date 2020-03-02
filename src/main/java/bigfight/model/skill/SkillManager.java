package bigfight.model.skill;

import bigfight.model.skill.struct.SkillIdentity;

import java.util.HashMap;
import java.util.Map;

public class SkillManager {
    private Map<SkillIdentity, SkillModel> skillMap;

    public SkillManager() {
        skillMap = new HashMap<>();
    }

    public Map<SkillIdentity, SkillModel> getSkillMap() {
        return skillMap;
    }
}
