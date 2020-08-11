package bigfight.combat.fighter;

import bigfight.combat.CombatTestUtil;
import bigfight.model.skill.skills.special.FastHands;
import bigfight.model.skill.skills.special.MineWater;
import bigfight.model.skill.skills.special.ShadowMove;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.warrior.component.*;
import bigfight.model.weapon.Weapon;

import bigfight.combat.util.CombatRandom;

import org.junit.jupiter.api.Test;

import static bigfight.model.skill.SkillFactoryTestUtil.DEFAULT_SKILL_FACTORY;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FighterTest {
    private double EPSILON = 0.01;
    private static final double NOT_UNARMED = 1;
    private static final int SELECT_WEAPON = 0;

    private FightableWarrior twoWeaponWarrior() {
        DisposableWeaponList weaponList = new DisposableWeaponList();
        weaponList.add(mock(Weapon.class));
        weaponList.add(mock(Weapon.class));

        FightableWarrior mockWarrior = mock(FightableWarrior.class);
        when(mockWarrior.getDisposableWeapons()).thenReturn(weaponList);
        when(mockWarrior.getSpecialSkills()).thenReturn(mock(SpecialSkillList.class));
        when(mockWarrior.getActiveSkills()).thenReturn(mock(ActiveSkillList.class));
        return mockWarrior;
    }

    private FightableWarrior noEmpowermentWarrior() {
        FightableWarrior mockWarrior = mock(FightableWarrior.class);
        when(mockWarrior.getDisposableWeapons()).thenReturn(mock(DisposableWeaponList.class));
        when(mockWarrior.getSpecialSkills()).thenReturn(mock(SpecialSkillList.class));
        when(mockWarrior.getActiveSkills()).thenReturn(mock(ActiveSkillList.class));
        return mockWarrior;
    }

    @Test
    void selectEmpowerment_weapon_is_discarded_after_selected() {
        FightableWarrior mockWarrior = twoWeaponWarrior();
        CombatRandom random = mock(CombatRandom.class);
        when(random.selectUnarmed()).thenReturn(NOT_UNARMED);
        when(random.selectWeaponOrSkill(anyInt())).thenReturn(SELECT_WEAPON);

        Fighter test = new Fighter(mockWarrior);
        test.selectEmpowerment(random);
        final int EXPECTED = 1;
        assertEquals(EXPECTED, test.getWeaponSize());
    }

    @Test
    void selectEmpowerment_successfully_return_not_null() {
        FightableWarrior mockWarrior = twoWeaponWarrior();
        CombatRandom random = mock(CombatRandom.class);
        when(random.selectUnarmed()).thenReturn(NOT_UNARMED);
        when(random.selectWeaponOrSkill(anyInt())).thenReturn(SELECT_WEAPON);

        Fighter test = new Fighter(mockWarrior);
        Empowerment empowerment = test.selectEmpowerment(random);
        assertNotNull(empowerment.getWeapon());
    }

    @Test
    void selectEmpowerment_gives_unarmed_when_no_weapon_or_skill() {
        FightableWarrior mockWarrior = noEmpowermentWarrior();
        CombatRandom random = mock(CombatRandom.class);
        Fighter test = new Fighter(mockWarrior);
        Empowerment empowerment = test.selectEmpowerment(random);
        assertNull(empowerment.getWeapon());
        assertNull(empowerment.getSkill());
    }

    @Test
    void selectAuxiliarySkill_fast_hand_invocation_chance() {
        // create warrior with two skills, one of them is fast hands
        FightableWarrior mockWarrior = mock(FightableWarrior.class);
        SpecialSkillList specialSkillList = new SpecialSkillList();
        FastHands fastHands = (FastHands) DEFAULT_SKILL_FACTORY.create(SkillIdentity.FAST_HANDS);
        specialSkillList.add(fastHands);
        ActiveSkillList activeSkillList = new ActiveSkillList();
        activeSkillList.add(DEFAULT_SKILL_FACTORY.create(SkillIdentity.ROAR));
        when(mockWarrior.getSpecialSkills()).thenReturn(specialSkillList);
        when(mockWarrior.getActiveSkills()).thenReturn(activeSkillList);
        when(mockWarrior.getDisposableWeapons()).thenReturn(mock(DisposableWeaponList.class));

        // test
        final double SELECT = 0;
        final double NOT_SELECT = (1.0 / 2.0) * fastHands.getExtraChance() + EPSILON;
        CombatRandom random = mock(CombatRandom.class);
        when(random.selectAuxiliarySkillRandom()).thenReturn(NOT_SELECT).thenReturn(SELECT);
        Fighter testFighter = new Fighter(mockWarrior);
        // test not selected by invocation chance
        testFighter.selectAuxiliarySkill(random);
        assertFalse(testFighter.getFighterFlag().fastHandsFlag);
        // test selected by invocation chance
        testFighter.selectAuxiliarySkill(random);
        assertTrue(testFighter.getFighterFlag().fastHandsFlag);
    }

    @Test
    void selectAuxiliarySkill_shadow_move_invocation_chance() {
        // create warrior with two skills, one of them is fast hands
        FightableWarrior mockWarrior = mock(FightableWarrior.class);
        ShadowMove shadowMove = (ShadowMove) DEFAULT_SKILL_FACTORY.create(SkillIdentity.SHADOW_MOVE);
        SpecialSkillList specialSkillList = new SpecialSkillList();
        specialSkillList.add(shadowMove.getUsableInstance());
        ActiveSkillList activeSkillList = new ActiveSkillList();
        activeSkillList.add(DEFAULT_SKILL_FACTORY.create(SkillIdentity.ROAR));
        when(mockWarrior.getSpecialSkills()).thenReturn(specialSkillList);
        when(mockWarrior.getActiveSkills()).thenReturn(activeSkillList);
        when(mockWarrior.getDisposableWeapons()).thenReturn(mock(DisposableWeaponList.class));

        // test
        final double SELECT = 0;
        final double NOT_SELECT = (1.0 / 2.0) * shadowMove.getInvocationChance() + EPSILON;
        CombatRandom random = mock(CombatRandom.class);
        when(random.selectAuxiliarySkillRandom()).thenReturn(NOT_SELECT).thenReturn(SELECT);
        Fighter testFighter = new Fighter(mockWarrior);
        // test not selected by invocation chance
        testFighter.selectAuxiliarySkill(random);
        assertFalse(testFighter.getFighterFlag().shadowMoveFlag);
        // test selected by invocation chance
        testFighter.selectAuxiliarySkill(random);
        assertTrue(testFighter.getFighterFlag().shadowMoveFlag);
        assertEquals(shadowMove.getMaxRound(), testFighter.getFighterFlag().shadowMoveRound);
    }

    @Test
    void selectAuxiliarySkill_mine_water_invocation_chance() {
        // create warrior with two skills, one of them is fast hands
        FightableWarrior mockWarrior = mock(FightableWarrior.class);
        SpecialSkillList specialSkillList = new SpecialSkillList();
        MineWater mineWater = (MineWater) DEFAULT_SKILL_FACTORY.create(SkillIdentity.MINE_WATER);
        specialSkillList.add(mineWater);
        ActiveSkillList activeSkillList = new ActiveSkillList();
        activeSkillList.add(DEFAULT_SKILL_FACTORY.create(SkillIdentity.ROAR));
        when(mockWarrior.getSpecialSkills()).thenReturn(specialSkillList);
        when(mockWarrior.getActiveSkills()).thenReturn(activeSkillList);
        when(mockWarrior.getDisposableWeapons()).thenReturn(mock(DisposableWeaponList.class));

        // test
        final double SELECT = 0;
        final double NOT_SELECT = (1.0 / 2.0) * mineWater.getInvocationChance() + EPSILON;
        CombatRandom random = mock(CombatRandom.class);
        when(random.selectAuxiliarySkillRandom()).thenReturn(NOT_SELECT).thenReturn(SELECT);
        Fighter testFighter = new Fighter(mockWarrior);
        // test not selected by invocation chance
        testFighter.selectAuxiliarySkill(random);
        assertFalse(testFighter.getFighterFlag().mineWaterFlag);
        // test selected by invocation chance
        testFighter.selectAuxiliarySkill(random);
        assertTrue(testFighter.getFighterFlag().mineWaterFlag);
    }

    @Test
    void update_health_not_exceed_maximum() {
        Fighter fighter = CombatTestUtil.createSimpleFixedFighter();
        final int EXPECTED = fighter.getHealth();
        fighter.updateHealth(fighter.getHealth() + 1);
        assertEquals(EXPECTED, fighter.getHealth());
    }
}
