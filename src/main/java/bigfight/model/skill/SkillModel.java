package bigfight.model.skill;

import bigfight.model.skill.struct.*;

public class SkillModel {
    private SkillStruct skill;

    public SkillModel(SkillIdentity identity) {
        this(identity, 1);
    }

    public SkillModel(SkillIdentity identity, int star) {
    }

    public String getName() {
        return "hello";
    }

    public SkillIdentity getIdentity() {
        return SkillIdentity.BORN_AS_STRONG;
    }

    public String getDescription() {
        return "hello";
    }

    public SkillType getType() {
        return SkillType.PERMANENT;
    }
}
