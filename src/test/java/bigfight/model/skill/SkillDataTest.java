package bigfight.model.skill;

import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.skill.struct.SkillStruct;
import bigfight.model.skill.struct.SkillStructArray;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SkillDataTest {

    @Test
    void test_skill_table_accessible() {
        SkillIdentity identity = SkillIdentity.BORN_AS_STRONG;
        SkillStructArray result = SkillData.SKILL_TABLE.get(identity);
        assertNotNull(result);
    }

    @Test
    // also test that the SkillStructArray class function correctly.
    void test_skill_struct_array_accessible_with_example_born_as_strong() {
        SkillStruct result = SkillData.BORN_AS_STRONG_ARRAY.withStar(1);
        assertNotNull(result);
    }

    @Test
    // also test that the SkillStruct class function correctly
    void test_skill_struct_accessible_with_example_born_as_strong_one() {
        SkillStruct test = SkillData.BORN_AS_STRONG_ONE;
        assertNotNull(test.name);
        assertNotNull(test.description);
        assertNotNull(test.identity);
        assertNotNull(test.type);
    }

    @Test
    void all_skill_identity_exist_in_skill_map() {
        for(SkillIdentity identity : SkillIdentity.values()) {
            assertNotNull(SkillData.SKILL_TABLE.get(identity));
        }
    }
}
