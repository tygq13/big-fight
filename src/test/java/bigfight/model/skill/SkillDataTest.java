package bigfight.model.skill;

import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.skill.struct.SkillStruct;
import bigfight.model.skill.struct.SkillStructArray;

import org.junit.jupiter.api.Test;

import javax.swing.text.StyledEditorKit;

import static org.junit.jupiter.api.Assertions.*;

class SkillDataTest {

    @Test
    void all_skill_identity_exist_in_skill_map() {
        SkillData skillData = new SkillData();
        for(SkillIdentity identity : SkillIdentity.values()) {
            assertNotNull(skillData.get(identity));
        }
    }
}
