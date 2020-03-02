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
}
