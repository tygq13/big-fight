package bigfight.model.skill.struct;

import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.skills.special.SpecialSkill;

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

    public void addActiveFromMap(Map<SkillIdentity, SkillModel> skillMap) {
        for (Map.Entry<SkillIdentity, SkillModel> model: skillMap.entrySet()) {
            if (model.getValue().getType() == SkillType.ACTIVE) {
                skillList.add(model.getValue());
            }
        }
    }

    public void addSpecialFromMap(Map<SkillIdentity, SkillModel> skillMap) {
        for (Map.Entry<SkillIdentity, SkillModel> model: skillMap.entrySet()) {
            if (model.getValue().getType() == SkillType.SPECIAL) {
                SpecialSkill specialSkill = (SpecialSkill) model.getValue();
                skillList.add(specialSkill.getUsableInstance());
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
        SkillModel toRemove = null;
        for(SkillModel model: skillList) {
            if (model.getIdentity() == identity) {
                toRemove = model;
            }
        }
        if (toRemove != null) {
            skillList.remove(toRemove);
        }
    }
}
