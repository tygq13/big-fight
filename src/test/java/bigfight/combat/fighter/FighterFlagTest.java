package bigfight.combat.fighter;

import bigfight.combat.CombatTestUtil;
import bigfight.combat.fighter.buff.Buffs;
import bigfight.combat.fighter.components.*;
import bigfight.combat.util.CombatRandom;
import bigfight.model.warrior.component.Empowerment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FighterFlagTest {

    @Test
    void noSelectSkill_effective_in_selecting_active_skill() {
        final int SELECT = 0;
        ActiveSkillList activeSkillList = new ActiveSkillList();
        activeSkillList.add(CombatTestUtil.createAnySkill());
        CombatSelector combatSelector = new CombatSelector(activeSkillList, mock(SpecialSkillList.class), mock(DisposableWeaponList.class));
        CombatRandom random = mock(CombatRandom.class);
        FighterFlag fighterFlag = new FighterFlag();
        fighterFlag.noSelectSkill = 1;
        when(random.selectActiveSkill(anyInt())).thenReturn(SELECT);
        // test
        Empowerment empowerment = combatSelector.selectEmpowerment(random, fighterFlag);
        assertNull(empowerment.getSkill());
    }
}
