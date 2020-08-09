package bigfight.model.skill.skills.permanent;

import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.struct.SkillStruct;
import bigfight.model.warrior.component.Attribute;

public class PermanentSkill extends SkillModel {

    public PermanentSkill(SkillStruct skill) {
        super(skill);
    }

    public void upgrade(Attribute attribute){};
}
