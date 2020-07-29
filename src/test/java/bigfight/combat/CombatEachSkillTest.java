// do functional test rather than unit test
package bigfight.combat;

import bigfight.combat.fighter.FighterStatus;
import bigfight.combat.util.CombatRandom;
import bigfight.model.skill.SkillFactory;
import bigfight.model.skill.skills.BoltFromTheBlue;
import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.warrior.component.Empowerment;
import bigfight.ui.EnUi;
import bigfight.ui.Uiable;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CombatEachSkillTest {
    private static SkillFactory defaultSkillFactory = CombatTestUtil.defaultSkillFactory;
    private static Uiable mockUi = mock(EnUi.class);

    private final double NO_ESCAPE = 1.0;

    @Test
    void roar_ignore_one_round() {
        // fighter with equal attributes
        FighterStatus fighter1 = CombatTestUtil.createSimpleFixedFighter();
        FighterStatus fighter2 = CombatTestUtil.createSimpleFixedFighter();
        SkillModel skill = defaultSkillFactory.create(SkillIdentity.ROAR);
        Empowerment empowerment = new Empowerment(skill);
        CombatRandom random = mock(CombatRandom.class);
        when(random.getEscapeRandom()).thenReturn(NO_ESCAPE);

        // test
        final int EXPECTED = 1;
        int result = new Round(fighter1, fighter2, empowerment, random, mockUi).fight();
        assertEquals(EXPECTED, result);
    }

    @Test
    void apparent_death_survive_deadly_attack() {
        FighterStatus fighter1 = CombatTestUtil.createSimpleFixedFighter();
        FighterStatus test = CombatTestUtil.createDyingFighterWithApparentDeath();
        CombatRandom random = mock(CombatRandom.class);
        when(random.getEscapeRandom()).thenReturn(NO_ESCAPE);
        when(random.getWeaponDamageRandom(anyInt(), anyInt())).thenReturn(fighter1.getUnarmedDamage().lower());

        new Round(fighter1, test, CombatTestUtil.createUnarmedEmpowerment(), random, mockUi).fight();
        final int EXPECTED_HEALTH_LEFT = 1;
        assertEquals(EXPECTED_HEALTH_LEFT, test.getHealth());
    }

    @Test
    void bolt_from_the_blue_level_multiply() {
        // fighter with equal attributes
        FighterStatus fighter1 = CombatTestUtil.createSimpleFixedFighter();
        FighterStatus fighter2 = CombatTestUtil.createSimpleFixedFighter();
        SkillModel skill = defaultSkillFactory.create(SkillIdentity.BOLT_FROM_THE_BLUE);
        BoltFromTheBlue boltFromTheBlue = (BoltFromTheBlue) skill;
        Empowerment empowerment = new Empowerment(skill);
        CombatRandom random = mock(CombatRandom.class);
        when(random.getEscapeRandom()).thenReturn(NO_ESCAPE);

        // test
        final int EXPECTED = fighter2.getHealth() - (int) (boltFromTheBlue.getDamage() + (fighter1.getLevel() * boltFromTheBlue.getLevelMultiply()));
        new Round(fighter1, fighter2, empowerment, random, mockUi).fight();
        assertEquals(EXPECTED, fighter2.getHealth());
    }

    @Test
    void fast_hand_hit_twice() {
        final int DAMAGE = 10;
        FighterStatus fighter1 = CombatTestUtil.createSimpleFixedFighter();
        FighterStatus fighter2 = CombatTestUtil.createSimpleFixedFighter();
        fighter1.getFighterFlag().fastHandsFlag = true;
        Empowerment empowerment = CombatTestUtil.createUnarmedEmpowerment();
        CombatRandom random = mock(CombatRandom.class);
        when(random.getEscapeRandom()).thenReturn(NO_ESCAPE);
        when(random.getWeaponDamageRandom(anyInt(), anyInt())).thenReturn(DAMAGE);
        // test
        final int EXPECTED = fighter2.getHealth() - DAMAGE * 2;
        new Round(fighter1, fighter2, empowerment, random, mockUi).fight();
        assertEquals(EXPECTED, fighter2.getHealth());
    }

}
