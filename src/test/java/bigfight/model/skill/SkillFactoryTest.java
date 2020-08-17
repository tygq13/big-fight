package bigfight.model.skill;

import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.struct.SkillIdentity;

import bigfight.model.skill.struct.SkillStruct;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SkillFactoryTest {
    @Test
    void test_all_skill_creatable() {
        SkillData skillData = new SkillData();
        SkillFactory testFactory = new SkillFactory(skillData);
        for (SkillIdentity testSkill: SkillIdentity.getArray()) {
            SkillModel result = testFactory.create(testSkill);
            assertNotNull(result);
        }
    }
}
