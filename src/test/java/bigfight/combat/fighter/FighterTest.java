package bigfight.combat.fighter;

import bigfight.model.skill.SkillManager;
import bigfight.model.skill.skills.FastHands;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.warrior.builder.Warrior;
import bigfight.model.warrior.component.*;
import bigfight.model.weapon.Weapon;
import bigfight.model.weapon.WeaponManager;

import java.util.ArrayList;
import bigfight.combat.util.CombatRandom;

import org.junit.jupiter.api.Test;

import static bigfight.model.skill.SkillFactoryUtil.DEFAULT_SKILL_FACTORY;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FighterTest {
    private double EPSILON = 0.01;
    private static final double NOT_UNARMED = 1;
    private static final int SELECT_WEAPON = 0;

    private Warrior twoWeaponWarrior() {
        ArrayList<Weapon> weaponList = new ArrayList<Weapon>();
        weaponList.add(mock(Weapon.class));
        weaponList.add(mock(Weapon.class));
        WeaponManager mockManager = mock(WeaponManager.class);
        when(mockManager.getWeaponList()).thenReturn(weaponList);

        Warrior mockWarrior = mock(Warrior.class);
        when(mockWarrior.getWeaponManager()).thenReturn(mockManager);
        when(mockWarrior.getSkillManager()).thenReturn(mock(SkillManager.class));
        return mockWarrior;
    }

    private Warrior noEmpowermentWarrior() {
        ArrayList<Weapon> weaponList = new ArrayList<Weapon>();
        WeaponManager mockManager = mock(WeaponManager.class);
        when(mockManager.getWeaponList()).thenReturn(weaponList);

        Warrior mockWarrior = mock(Warrior.class);
        when(mockWarrior.getWeaponManager()).thenReturn(mockManager);
        when(mockWarrior.getSkillManager()).thenReturn(mock(SkillManager.class));
        return mockWarrior;
    }

    @Test
    void selectEmpowerment_weapon_is_discarded_after_selected() {
        Warrior mockWarrior = twoWeaponWarrior();
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
        Warrior mockWarrior = twoWeaponWarrior();
        CombatRandom random = mock(CombatRandom.class);
        when(random.selectUnarmed()).thenReturn(NOT_UNARMED);
        when(random.selectWeaponOrSkill(anyInt())).thenReturn(SELECT_WEAPON);

        Fighter test = new Fighter(mockWarrior);
        Empowerment empowerment = test.selectEmpowerment(random);
        assertNotNull(empowerment.getWeapon());
    }

    @Test
    void selectEmpowerment_gives_unarmed_when_no_weapon_or_skill() {
        Warrior mockWarrior = noEmpowermentWarrior();
        CombatRandom random = mock(CombatRandom.class);
        Fighter test = new Fighter(mockWarrior);
        Empowerment empowerment = test.selectEmpowerment(random);
        assertNull(empowerment.getWeapon());
        assertNull(empowerment.getSkill());
    }

    @Test
    void selectEmpowerment_fast_hand_extra_chance() {
        // create warrior with two skills, one of them is fast hands
        Warrior mockWarrior = mock(Warrior.class);
        WeaponManager weaponManager = mock(WeaponManager.class);
        SkillManager skillManager = new SkillManager();
        FastHands fastHands = (FastHands) DEFAULT_SKILL_FACTORY.create(SkillIdentity.FAST_HANDS);
        skillManager.add(fastHands);
        skillManager.add(DEFAULT_SKILL_FACTORY.create(SkillIdentity.ROAR));
        when(mockWarrior.getSkillManager()).thenReturn(skillManager);
        when(mockWarrior.getWeaponManager()).thenReturn(weaponManager);

        // test
        final double SELECT = 0;
        final double NOT_SELECT = (1.0 / 2.0) * fastHands.getExtraChance() + EPSILON;
        CombatRandom random = mock(CombatRandom.class);
        when(random.selectAuxiliarySkillRandom()).thenReturn(NOT_SELECT).thenReturn(SELECT);
        Fighter testFighter = new Fighter(mockWarrior);
        FighterFlag test = new FighterFlag();
        // test not selected by extra chance
        testFighter.selectAuxiliarySkill(test, random);
        assertFalse(test.fastHandsFlag);
        // test selected by extra chance
        testFighter.selectAuxiliarySkill(test, random);
        assertTrue(test.fastHandsFlag);
    }
}
