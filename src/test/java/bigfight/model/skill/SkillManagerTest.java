package bigfight.model.skill;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SkillManagerTest {

    @Test
    void skills_map_initialize() {
        SkillManager skillManager = new SkillManager();
        assertNotNull(skillManager.getSkillMap());
    }

    @Test
    // rely on the correctness of add() method and existence of SkillIdentity.BORN_AS_STRONG
    void get_size_of_skill_set() {
        SkillManager skillManager = new SkillManager();
        SkillModel dummyModel = mock(SkillModel.class);
        skillManager.add(dummyModel);
        assertEquals(1, skillManager.getSize());
    }
}
