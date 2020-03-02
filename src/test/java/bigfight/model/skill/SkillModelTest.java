package bigfight.model.skill;

import bigfight.model.skill.struct.SkillIdentity;

import static org.junit.jupiter.api.Assertions.*;

class SkillModelTest {

    void model_initialization_and_access_with_example_born_as_strong() {
        SkillIdentity identity = SkillIdentity.BORN_AS_STRONG;
        SkillModel skillModel = new SkillModel(identity);
        assertNotNull(skillModel.getName());
        assertNotNull(skillModel.getIdentity());
        assertNotNull(skillModel.getDescription());
        assertNotNull(skillModel.getType());
    }
}
