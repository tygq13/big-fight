package bigfight.model.skill;

import bigfight.model.skill.struct.SkillIdentity;

import bigfight.model.skill.struct.SkillStruct;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SkillFactoryTest {
    @Test
    void test_create_skill_with_example_identity_born_as_strong() {
        // rely on existence of SkillIdentity.BORN_AS_STRONG
        SkillStruct mockStruct = mock(SkillStruct.class);
        SkillData skillData = mock(SkillData.class);
        when(skillData.getWithStar(any(SkillIdentity.class), anyInt())).thenReturn(mockStruct);
        SkillFactory testFactory = new SkillFactory(skillData);
        SkillModel result = testFactory.create(SkillIdentity.BORN_AS_STRONG);
        assertNotNull(result);
    }
}
