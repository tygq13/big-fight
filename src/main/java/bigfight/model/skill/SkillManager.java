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

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(Map.Entry<SkillIdentity, SkillModel> skill : skillMap.entrySet()) {
            result.append(skill.getValue().getName()).append(", ");
        }
        if (result.length() > 0) {
            result.delete(result.length() - 2, result.length());
        }
        return result.toString();
    }
}
