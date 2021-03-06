package bigfight.model.skill.skills;

import bigfight.model.skill.struct.*;

public class SkillModel{
    private SkillStruct skill;

    public SkillModel(SkillStruct skill) {
        this.skill = skill;
    }

    public String getName() {
        return skill.name;
    }

    public SkillIdentity getIdentity() {
        return skill.identity;
    }

    public String getDescription() {
        return skill.description;
    }

    public String getAttackDescription() {return skill.attackString;}

    public String getDodgeDescription() {return skill.dodgeString;}

    public SkillType getType() {
        return skill.type;
    }
}
