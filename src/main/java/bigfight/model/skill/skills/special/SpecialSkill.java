package bigfight.model.skill.skills.special;

import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.struct.SkillStruct;

public class SpecialSkill extends SkillModel {
    public SpecialSkill(SkillStruct skillStruct) {
        super(skillStruct);
    }

    public SkillModel getUsableInstance() {
        return this;
    }

    public boolean isAuxiliary() {
        return false;
    }

    public double getInvocationChance() {
        return 1;
    }
}
