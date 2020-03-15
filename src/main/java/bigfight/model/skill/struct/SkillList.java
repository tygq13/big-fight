package bigfight.model.skill.struct;

import bigfight.model.skill.skills.SkillModel;

import java.util.ArrayList;
import java.util.Map;

public class SkillList {
    private ArrayList<SkillModel> skillList;

    public SkillList() {
        skillList = new ArrayList<>();
    }

    public void add(SkillModel model) {
        skillList.add(model);
    }

    public void addNonPermanentFromMap(Map<SkillIdentity, SkillModel> skillMap) {
        for (Map.Entry<SkillIdentity, SkillModel> model: skillMap.entrySet()) {
            if (model.getValue().getType() != SkillType.PERMANENT) {
                skillList.add(model.getValue());
            }
        }
    }

    public int size() {
        return skillList.size();
    }

    public SkillModel get(int index) {
        return skillList.get(index);
    }

    public SkillModel get(SkillIdentity identity) {
        for(SkillModel model: skillList) {
            if (model.getIdentity() == identity) {
                return model;
            }
        }
        // better to throw exception
        return null;
    }

    public boolean contains(SkillIdentity identity) {
        for(SkillModel model: skillList) {
            if (model.getIdentity() == identity) {
                return true;
            }
        }
        return false;
    }

    public void remove(SkillIdentity identity) {
        for(SkillModel model: skillList) {
            if (model.getIdentity() == identity) {
                skillList.remove(model);
            }
        }
    }
}
