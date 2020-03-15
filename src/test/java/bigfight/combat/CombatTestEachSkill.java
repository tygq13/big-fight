// do functional test rather than unit test
package bigfight.combat;

import bigfight.combat.fighter.Fighter;
import bigfight.combat.fighter.FighterStatus;
import bigfight.combat.util.CombatRandom;
import bigfight.model.skill.SkillData;
import bigfight.model.skill.SkillFactory;
import bigfight.model.skill.SkillManager;
import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.warrior.Warrior;
import bigfight.model.warrior.component.Empowerment;
import bigfight.model.weapon.WeaponData;
import bigfight.model.weapon.WeaponFactory;
import bigfight.model.weapon.WeaponManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CombatTestEachSkill {
    private static WeaponData defaultWeaponData = new WeaponData();
    private static WeaponFactory defaultWeaponFactory = new WeaponFactory(defaultWeaponData);
    private static SkillData defaultSkillDate = new SkillData();
    private static SkillFactory defaultSkillFactory = new SkillFactory(defaultSkillDate);

    private final double NO_ESCAPE = 1.0;

    private FighterStatus getSimpleFixedFighter() {
        Fighter modelFighter = mock(Fighter.class);
        when(modelFighter.getSpeed()).thenReturn(5);
        when(modelFighter.getAgility()).thenReturn(5);
        when(modelFighter.getStrength()).thenReturn(5);
        when(modelFighter.getHealth()).thenReturn(100);
        when(modelFighter.getUnarmedDamage()).thenReturn(10);
        return new FighterStatus(modelFighter);
    }

    private Empowerment getUnarmedEmpowerment() {
        Empowerment empowerment = mock(Empowerment.class);
        when(empowerment.getWeapon()).thenReturn(null);
        when(empowerment.getSkill()).thenReturn(null);
        return empowerment;
    }

    private FighterStatus getDyingFighterWithApparentDeath() {
        SkillModel skill = defaultSkillFactory.create(SkillIdentity.APPARENT_DEATH);
        SkillManager skillManager = new SkillManager();
        skillManager.add(skill);
        Warrior warrior = mock(Warrior.class);
        when(warrior.getSkillManager()).thenReturn(skillManager);
        when(warrior.getWeaponManager()).thenReturn(new WeaponManager());
        when(warrior.getHealth()).thenReturn(2);
        Fighter fighter = new Fighter(warrior);
        return new FighterStatus(fighter);
    }

    @Test
    void roar_ignore_one_round() {
        // fighter with equal attributes
        FighterStatus fighter1 = getSimpleFixedFighter();
        FighterStatus fighter2 = getSimpleFixedFighter();
        SkillModel skill = defaultSkillFactory.create(SkillIdentity.ROAR);
        Empowerment empowerment = new Empowerment(skill);
        CombatRandom random = mock(CombatRandom.class);
        when(random.getEscapeRandom()).thenReturn(NO_ESCAPE);

        // test
        final int EXPECTED = 1;
        int result = new Round(fighter1, fighter2, empowerment, random).fight();
        assertEquals(EXPECTED, result);
    }

    @Test
    void apparent_death_survive_deadly_attack() {
        FighterStatus fighter1 = getSimpleFixedFighter();
        FighterStatus test = getDyingFighterWithApparentDeath();
        CombatRandom random = mock(CombatRandom.class);
        when(random.getEscapeRandom()).thenReturn(NO_ESCAPE);

        new Round(fighter1, test, getUnarmedEmpowerment(), random).fight();
        final int EXPECTED_HEALTH_LEFT = 1;
        assertEquals(EXPECTED_HEALTH_LEFT, test.getHealth());
    }

}
