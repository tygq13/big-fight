package bigfight.model.skill.struct;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SkillStructArrayTest {
    @Mock SkillStruct skillStruct;
    @InjectMocks SkillStructArray skillStructArray;

    @Test
    void test_weapon_struct_array_get_with_star() {
        SkillStruct result = skillStructArray.withStar(0);
        assertEquals(skillStruct, result);
    }
}
