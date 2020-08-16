package bigfight.combat.fighter.components;

import bigfight.combat.fighter.Fighter;
import bigfight.combat.fighter.FighterBuilderTestUtil;
import bigfight.combat.util.CombatRandom;
import bigfight.model.skill.skills.special.FastHands;
import bigfight.model.skill.skills.special.MineWater;
import bigfight.model.skill.skills.special.ShadowMove;
import bigfight.model.skill.skills.special.SpecialSkill;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.skill.struct.SkillType;
import org.junit.jupiter.api.Test;

import static bigfight.model.skill.SkillFactoryTestUtil.DEFAULT_SKILL_FACTORY;
import static org.mockito.Mockito.*;

public class SpecialSkillTest {

    @Test
    void selectHealingSKill_based_on_invocation_chance() {
        final double INVOCATION_CHANCE = 0.2;
        SpecialSkill specialSkill = spy(mock(SpecialSkill.class));
        when(specialSkill.getType()).thenReturn(SkillType.SPECIAL);
        when(specialSkill.getInvocationChance()).thenReturn(INVOCATION_CHANCE);
        Fighter testFighter = new FighterBuilderTestUtil()
                .withSkill(specialSkill)
                .build();
        // test
        testFighter.getCombatSelector().selectHealingSkill(mock(CombatRandom.class), testFighter.getHealthObj());
        verify(specialSkill).getInvocationChance();
    }

    @Test
    void selectHealingSKill_fast_hand_activated() {
        final double SELECT = -1.0;
        SpecialSkillList specialSkillList = new SpecialSkillList();
        FastHands fastHands = (FastHands) DEFAULT_SKILL_FACTORY.create(SkillIdentity.FAST_HANDS);
        FastHands spy = spy(fastHands);
        specialSkillList.add(spy);
        CombatRandom random = mock(CombatRandom.class);
        when(random.selectHealingSkillRandom()).thenReturn(SELECT);
        specialSkillList.postWeaponAuxiliary(random, 1);
        verify(spy).createBuff();
    }

    @Test
    void selectHealingSKill_shadow_move_activated() {
        final double SELECT = -1.0;
        SpecialSkillList specialSkillList = new SpecialSkillList();
        ShadowMove shadowMove = (ShadowMove) DEFAULT_SKILL_FACTORY.create(SkillIdentity.SHADOW_MOVE);
        ShadowMove spy = spy(shadowMove);
        specialSkillList.add(spy);
        CombatRandom random = mock(CombatRandom.class);
        when(random.selectHealingSkillRandom()).thenReturn(SELECT);
        specialSkillList.postWeaponAuxiliary(random, 1);
        verify(spy).createBuff();
    }

    @Test
    void selectHealingSKill_mine_water_activated() {
        final double SELECT = -1.0;
        SpecialSkillList specialSkillList = new SpecialSkillList();
        MineWater mineWater = (MineWater) DEFAULT_SKILL_FACTORY.create(SkillIdentity.MINE_WATER);
        MineWater spy = spy(mineWater);
        specialSkillList.add(spy);
        CombatRandom random = mock(CombatRandom.class);
        when(random.selectHealingSkillRandom()).thenReturn(SELECT);
        specialSkillList.preRoundAuxiliary(mock(Health.class), random, 1);
        verify(spy).updateHealth(any());
    }
}
