package bigfight.combat.fighter;

import bigfight.combat.CombatTestUtil;
import bigfight.combat.fighter.components.*;
import bigfight.combat.util.CombatRandom;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.warrior.component.Empowerment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static bigfight.model.skill.SkillFactoryTestUtil.DEFAULT_SKILL_FACTORY;
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
        FighterFlag fighterFlag = new FighterFlag();
        fighterFlag.noSelectSkill = 1;
        CombatSelector combatSelector = new CombatSelector(activeSkillList, mock(SpecialSkillList.class), mock(DisposableWeaponList.class),
                fighterFlag);
        CombatRandom random = mock(CombatRandom.class);
        when(random.selectActiveSkill(anyInt())).thenReturn(SELECT);
        // test
        Empowerment empowerment = combatSelector.selectEmpowerment(random);
        assertNull(empowerment.getSkill());
    }

    // todo:smelly test, try to refactor this

    @Test
    // does not test sea_reflect, blood_sacrifica, blood_thirsty, heal_skill since they are copy and paste
    void noSelectSkill_effective_in_selecting_special_skill_example_haki_protect() {
        final double SELECT = 0;
        SpecialSkillList specialSkillList = new SpecialSkillList();
        specialSkillList.add(DEFAULT_SKILL_FACTORY.create(SkillIdentity.HAKI_PROTECT));
        FighterFlag fighterFlag = new FighterFlag();
        fighterFlag.noSelectSkill = 1;
        CombatSelector combatSelector = new CombatSelector(mock(ActiveSkillList.class), specialSkillList, mock(DisposableWeaponList.class),
                fighterFlag);
        CombatRandom random = mock(CombatRandom.class);
        when(random.getSingleSpecialRandom()).thenReturn(SELECT);
        // test
        double EXPECTED = 0;
        double result = combatSelector.selectHakiProtect(random);
        assertEquals(EXPECTED, result);
    }
}
