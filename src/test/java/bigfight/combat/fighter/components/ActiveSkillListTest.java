package bigfight.combat.fighter.components;

import bigfight.combat.util.CombatRandom;
import bigfight.model.skill.skills.ShockWave;
import bigfight.model.skill.struct.SkillIdentity;
import org.junit.jupiter.api.Test;

import static bigfight.model.skill.SkillFactoryTestUtil.DEFAULT_SKILL_FACTORY;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ActiveSkillListTest {

    @Test
    void shock_wave_removed_after_select() {
        final int SELECT = 0;
        ShockWave shockWave = (ShockWave) DEFAULT_SKILL_FACTORY.create(SkillIdentity.SHOCK_WAVE);
        ActiveSkillList activeSkillList = new ActiveSkillList();
        activeSkillList.add(shockWave);
        CombatRandom random = mock(CombatRandom.class);
        when(random.selectActiveSkill(anyInt())).thenReturn(SELECT);
        // test
        int EXPECTED_SIZE = 0;
        activeSkillList.select(random);
        assertEquals(EXPECTED_SIZE, activeSkillList.size());
    }
}
