package bigfight.combat.fighter;

import bigfight.combat.Combat;
import bigfight.combat.fighter.buff.Buff;
import bigfight.combat.util.CombatRandom;
import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.skills.special.FastHands;
import bigfight.model.skill.skills.special.MineWater;
import bigfight.model.skill.skills.special.ShadowMove;
import bigfight.model.skill.skills.special.SpecialSkill;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.skill.struct.SkillType;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Map;

import static bigfight.model.skill.SkillFactoryTestUtil.DEFAULT_SKILL_FACTORY;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class SpecialSkillTest {

    @Test
    void selectAuxiliarySkill_based_on_invocation_chance() {
        final double INVOCATION_CHANCE = 0.2;
        SpecialSkill specialSkill = spy(mock(SpecialSkill.class));
        when(specialSkill.getType()).thenReturn(SkillType.SPECIAL);
        when(specialSkill.isAuxiliary()).thenReturn(true);
        when(specialSkill.getInvocationChance()).thenReturn(INVOCATION_CHANCE);
        Fighter testFighter = new FighterBuilderTestUtil()
                .withSkill(specialSkill)
                .build();
        // test
        testFighter.selectAuxiliarySkill(mock(CombatRandom.class));
        verify(specialSkill).getInvocationChance();
    }

    @Test
    void selectAuxiliarySkill_fast_hand_activated() {
        final double SELECT = -1.0;
        SpecialSkillList specialSkillList = new SpecialSkillList();
        FastHands fastHands = (FastHands) DEFAULT_SKILL_FACTORY.create(SkillIdentity.FAST_HANDS);
        FastHands spy = spy(fastHands);
        specialSkillList.add(spy);
        CombatRandom random = mock(CombatRandom.class);
        when(random.selectAuxiliarySkill()).thenReturn(SELECT);
        specialSkillList.select(new FighterFlag(), random, 1);
        verify(spy).createBuff();
    }

    @Test
    void selectAuxiliarySkill_shadow_move_activated() {
        final double SELECT = -1.0;
        SpecialSkillList specialSkillList = new SpecialSkillList();
        ShadowMove shadowMove = (ShadowMove) DEFAULT_SKILL_FACTORY.create(SkillIdentity.SHADOW_MOVE);
        ShadowMove spy = spy(shadowMove);
        specialSkillList.add(spy);
        CombatRandom random = mock(CombatRandom.class);
        when(random.selectAuxiliarySkill()).thenReturn(SELECT);
        specialSkillList.select(new FighterFlag(), random, 1);
        verify(spy).createBuff();
    }

    @Test
    void selectAuxiliarySkill_mine_water_activated() {
        MineWater mineWater = (MineWater) DEFAULT_SKILL_FACTORY.create(SkillIdentity.MINE_WATER);
        Fighter testFighter = new FighterBuilderTestUtil()
                .withSkill(mineWater)
                .build();

        // test
        assertFalse(testFighter.getFighterFlag().mineWaterFlag);
        testFighter.selectAuxiliarySkill(mock(CombatRandom.class));
        assertTrue(testFighter.getFighterFlag().mineWaterFlag);
    }
}
