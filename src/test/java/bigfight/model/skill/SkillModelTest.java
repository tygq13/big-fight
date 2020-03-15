package bigfight.model.skill;

import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.skill.struct.SkillStruct;
import bigfight.model.skill.struct.SkillType;

import static org.junit.jupiter.api.Assertions.*;

class SkillModelTest {

    void model_initialization_and_access_with_example_born_as_strong() {
        // rely on the correctness of this struct and the existence of SkillType.ACTIVE and SkillIdentity.BORN_AS_STRONG
        SkillStruct testStruct = new SkillStruct(
                SkillType.ACTIVE,
                "test",
                SkillIdentity.BORN_AS_STRONG,
                "test"
        );
        SkillModel result = new SkillModel(testStruct);
        assertNotNull(result.getName());
        assertNotNull(result.getDescription());
        assertNotNull(result.getIdentity());
        assertNotNull(result.getType());
    }
}
