package bigfight.combat.fighter;

import bigfight.combat.CombatTestUtil;
import bigfight.model.skill.SkillManager;
import bigfight.model.skill.skills.FastHands;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.skill.struct.SkillStruct;
import bigfight.model.warrior.builder.Warrior;
import bigfight.model.warrior.component.*;
import bigfight.model.weapon.Weapon;
import bigfight.model.weapon.WeaponManager;

import java.util.ArrayList;
import bigfight.combat.util.CombatRandom;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FighterTest {
    private double EPSILON = 0.01;

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
        final int SELECT_WEAPON = 0;
        Warrior mockWarrior = twoWeaponWarrior();
        CombatRandom random = mock(CombatRandom.class);
        when(random.selectWeaponOrSkill(anyInt())).thenReturn(SELECT_WEAPON);

        Fighter test = new Fighter(mockWarrior);
        test.SelectEmpowerment(random);
        final int EXPECTED = 1;
        assertEquals(EXPECTED, test.getWeaponSize());
    }

    @Test
    void selectEmpowerment_successfully_return_not_null() {
        final int SELECT_WEAPON = 0;
        Warrior mockWarrior = twoWeaponWarrior();
        CombatRandom random = mock(CombatRandom.class);
        when(random.selectWeaponOrSkill(anyInt())).thenReturn(SELECT_WEAPON);

        Fighter test = new Fighter(mockWarrior);
        Empowerment empowerment = test.SelectEmpowerment(random);
        assertNotNull(empowerment.getWeapon());
    }

    @Test
    void selectEmpowerment_gives_unarmed_when_no_weapon_or_skill() {
        Warrior mockWarrior = noEmpowermentWarrior();
        CombatRandom random = mock(CombatRandom.class);
        Fighter test = new Fighter(mockWarrior);
        Empowerment empowerment = test.SelectEmpowerment(random);
        assertNull(empowerment.getWeapon());
        assertNull(empowerment.getSkill());
    }

    @Test
    void selectEmpowerment_fast_hand_extra_chance() {
        // create warrior with two skills, one of them is fast hands
        Warrior mockWarrior = mock(Warrior.class);
        ArrayList<Weapon> weaponList = new ArrayList<Weapon>();
        WeaponManager weaponManager = mock(WeaponManager.class);
        when(weaponManager.getWeaponList()).thenReturn(weaponList);
        SkillManager skillManager = new SkillManager();
        FastHands fastHands = (FastHands) CombatTestUtil.defaultSkillFactory.create(SkillIdentity.FAST_HANDS);
        skillManager.add(fastHands);
        skillManager.add(CombatTestUtil.defaultSkillFactory.create(SkillIdentity.ROAR));
        when(mockWarrior.getSkillManager()).thenReturn(skillManager);
        when(mockWarrior.getWeaponManager()).thenReturn(weaponManager);

        // test
        final double SELECT_EXTRA = 0;
        final double NOT_SELECT_EXTRA = (1.0 / 2.0) * fastHands.getExtraChance() + EPSILON;
        CombatRandom random = mock(CombatRandom.class);
        when(random.selectExtraChanceEmpowerment()).thenReturn(NOT_SELECT_EXTRA).thenReturn(SELECT_EXTRA);
        when(random.selectWeaponOrSkill(anyInt())).thenReturn(0); // not select any empowerment
        Fighter test = new Fighter(mockWarrior);
        // test not selected by extra chance
        Empowerment empowerment = test.SelectEmpowerment(random);
        assertNull(empowerment.getSkill());
        assertNull(empowerment.getWeapon());
        // test selected by extra chance
        empowerment = test.SelectEmpowerment(random);
        assertNotNull(empowerment.getSkill());
        final SkillIdentity EXPECTED_IDENTITY = SkillIdentity.FAST_HANDS;
        assertEquals(EXPECTED_IDENTITY, empowerment.getSkill().getIdentity());
    }
}
