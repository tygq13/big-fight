package bigfight.combat;

import bigfight.combat.fighter.Fighter;
import bigfight.combat.fighter.FighterStatus;
import bigfight.combat.util.CombatRandom;
import bigfight.model.skill.SkillData;
import bigfight.model.skill.SkillFactory;
import bigfight.model.skill.SkillModel;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.warrior.component.Empowerment;
import bigfight.model.weapon.WeaponData;
import bigfight.model.weapon.WeaponFactory;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CombatTestEachEmpowerment {
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

    @Test
    void roar_ignore_one_round() {
        // fighter with equal attributes
        FighterStatus fighter1 = getSimpleFixedFighter();
        FighterStatus fighter2 = getSimpleFixedFighter();
        SkillModel skill = defaultSkillFactory.create(SkillIdentity.ROAR);
        Empowerment empowerment = new Empowerment(skill);
        CombatRandom random = mock(CombatRandom.class);
        when(random.getAttackEscapeRandom()).thenReturn(NO_ESCAPE);

        // test
        final int EXPECTED = 1;
        int result = new Round(fighter1, fighter2, empowerment, random).fight();
        assertEquals(EXPECTED, result);
    }

}
