package bigfight.model.skill;

import bigfight.model.skill.skills.SkillModel;
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

    public void add(SkillModel model) {
        skillMap.put(model.getIdentity(), model);
    }

    public int getSize() {
        return skillMap.size();
    }
}
