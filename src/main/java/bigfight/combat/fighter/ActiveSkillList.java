package bigfight.combat.fighter;

import bigfight.combat.util.CombatRandom;
import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.skill.struct.SkillType;
import bigfight.model.warrior.component.Empowerment;

import java.util.ArrayList;
import java.util.Map;

public class ActiveSkillList {
    private ArrayList<SkillModel> skillList;

    public ActiveSkillList() {
        skillList = new ArrayList<>();
    }

    public ActiveSkillList(Map<SkillIdentity, SkillModel> skillMap) {
        skillList = new ArrayList<>();
        addActiveFromMap(skillMap);
    }

    private void addActiveFromMap(Map<SkillIdentity, SkillModel> skillMap) {
        for (Map.Entry<SkillIdentity, SkillModel> model: skillMap.entrySet()) {
            if (model.getValue().getType() == SkillType.ACTIVE) {
                skillList.add(model.getValue());
            }
        }
    }

    public void add(SkillModel model) {
        skillList.add(model);
    }

    public int size() {
        return skillList.size();
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

    public Empowerment select(CombatRandom random) {
        int luckyDraw = random.selectWhichEmpowerment(skillList.size());
        return new Empowerment(skillList.get(luckyDraw));
    }
}