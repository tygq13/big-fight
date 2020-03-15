// do functional test rather than unit test
package bigfight.combat;

import bigfight.combat.fighter.Fighter;
import bigfight.combat.fighter.FighterStatus;

import bigfight.combat.util.CombatAlgo;
import bigfight.combat.util.CombatRandom;
import bigfight.model.skill.SkillData;
import bigfight.model.skill.SkillFactory;
import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.warrior.component.Empowerment;
import bigfight.model.weapon.Weapon;
import bigfight.model.weapon.WeaponData;
import bigfight.model.weapon.WeaponFactory;
import bigfight.model.weapon.struct.WeaponIdentity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoundTest {
    private final double NO_ESCAPE = 1.0;
    private final double NO_IGNORE = 0.0;
    private final double NO_THROW = 1.0;

    private static WeaponData defaultWeaponData = new WeaponData();
    private static WeaponFactory defaultWeaponFactory = new WeaponFactory(defaultWeaponData);
    private static SkillData defaultSkillDate = new SkillData();
    private static SkillFactory defaultSkillFactory = new SkillFactory(defaultSkillDate);

    private FighterStatus getSimpleFixedFighter() {
        Fighter modelFighter = mock(Fighter.class);
        when(modelFighter.getSpeed()).thenReturn(5);
        when(modelFighter.getAgility()).thenReturn(5);
        when(modelFighter.getStrength()).thenReturn(5);
        when(modelFighter.getHealth()).thenReturn(100);
        when(modelFighter.getUnarmedDamage()).thenReturn(10);
        return new FighterStatus(modelFighter);
    }

    private FighterStatus getCustomFighter(int speed, int agility, int strength, int health, int unarmed) {
        Fighter modelFighter = mock(Fighter.class);
        when(modelFighter.getSpeed()).thenReturn(speed);
        when(modelFighter.getAgility()).thenReturn(agility);
        when(modelFighter.getStrength()).thenReturn(strength);
        when(modelFighter.getHealth()).thenReturn(health);
        when(modelFighter.getUnarmedDamage()).thenReturn(unarmed);
        return new FighterStatus(modelFighter);
    }

    private Empowerment getUnarmedEmpowerment() {
        Empowerment empowerment = mock(Empowerment.class);
        when(empowerment.getWeapon()).thenReturn(null);
        when(empowerment.getSkill()).thenReturn(null);
        return empowerment;
    }

    @Test
    void round_give_damage_in_simple_weapon_attack_with_example_trident() {
        FighterStatus fighter1 = getSimpleFixedFighter();
        FighterStatus fighter2 = getSimpleFixedFighter();
        Weapon weapon = defaultWeaponFactory.create(WeaponIdentity.TRIDENT);
        Empowerment empowerment = new Empowerment(weapon);
        CombatRandom random = mock(CombatRandom.class);
        when(random.getEscapeRandom()).thenReturn(NO_ESCAPE);
        when(random.getThrowWeaponRandom()).thenReturn(NO_THROW);

        // test
        int damage = weapon.getDamage().getKey();
        final int EXPECTED_HEALTH = fighter2.getHealth() - damage;
        new Round(fighter1, fighter2, empowerment, random).fight();
        assertEquals(EXPECTED_HEALTH, fighter2.getHealth());
    }

    @Test
    void round_give_damage_in_simple_skill_attack_with_example_roar() {
        FighterStatus fighter1 = getSimpleFixedFighter();
        FighterStatus fighter2 = getSimpleFixedFighter();
        SkillModel skill = defaultSkillFactory.create(SkillIdentity.ROAR);
        Empowerment empowerment = new Empowerment(skill);
        CombatRandom random = mock(CombatRandom.class);
        when(random.getEscapeRandom()).thenReturn(NO_ESCAPE);

        // test
        final int DAMAGE = 15; // the damage of one start ROAR is fixed at 15.
        final int EXPECTED_HEALTH = fighter2.getHealth() - DAMAGE;
        new Round(fighter1, fighter2, empowerment, random).fight();
        assertEquals(EXPECTED_HEALTH, fighter2.getHealth());
    }

    @Test
    void round_give_damage_in_simple_attack_with_unarmed() {
        FighterStatus fighter1 = getSimpleFixedFighter();
        FighterStatus fighter2 = getSimpleFixedFighter();
        Empowerment empowerment = getUnarmedEmpowerment();
        CombatRandom random = mock(CombatRandom.class);
        when(random.getEscapeRandom()).thenReturn(NO_ESCAPE);

        int EXPECTED_HEALTH = fighter2.getHealth() - fighter1.getUnarmedDamage();
        new Round(fighter1, fighter2, empowerment, random).fight();
        assertEquals(EXPECTED_HEALTH, fighter2.getHealth());
    }

    @Test
    void round_ignore_round_by_speed() {
        final int SPEED1 = 10;
        final int SPEED2 = 5;
        FighterStatus fighter1 = getCustomFighter(SPEED1, 5, 5, 100, 10);
        FighterStatus fighter2 = getCustomFighter(SPEED2, 5, 5, 100, 10);
        double ignore = CombatAlgo.ignoreBySpeed(SPEED1, SPEED2);
        CombatRandom random = mock(CombatRandom.class);
        when(random.getSpeedIgnoreRandom()).thenReturn(ignore - Math.ulp(1));

        int EXPECTED = 1;
        int result = new Round(fighter1, fighter2, getUnarmedEmpowerment(), random).fight();
        assertEquals(EXPECTED, result);
    }

    @Test
    void round_attack_miss_by_agility() {
        final int AGILITY1 = 10;
        final int AGILITY2 = 5;
        FighterStatus fighter1 = getCustomFighter(5, AGILITY1, 5, 100, 10);
        FighterStatus fighter2 = getCustomFighter(5, AGILITY2, 5, 100, 10);
        double escape = CombatAlgo.escapeByAgility(AGILITY2, AGILITY1);
        CombatRandom random = mock(CombatRandom.class);
        when(random.getEscapeRandom()).thenReturn(escape - Math.ulp(1));
        when(random.getThrowWeaponRandom()).thenReturn(NO_THROW);
        int EXPECTED_HEALTH = fighter2.getHealth(); // escaped, no damage done

        new Round(fighter1, fighter2, getUnarmedEmpowerment(), random).fight();
        assertEquals(EXPECTED_HEALTH, fighter2.getHealth());
    }

    @Test
    void round_attack_multiplied_by_strength() {
        final int STRENGTH1 = 100;
        final int STRENGTH2 = 50;
        FighterStatus fighter1 = getCustomFighter(5, 5, STRENGTH1, 100, 10);
        FighterStatus fighter2 = getCustomFighter(5, 5, STRENGTH2, 100, 10);
        double multiply = CombatAlgo.multiplyByStrength(STRENGTH1, STRENGTH2);
        CombatRandom random = mock(CombatRandom.class);
        when(random.getEscapeRandom()).thenReturn(NO_ESCAPE);
        when(random.getThrowWeaponRandom()).thenReturn(NO_THROW);
        int EXPECTED_HEALTH = fighter2.getHealth() - (int) (fighter1.getUnarmedDamage() * (1 + multiply));

        new Round(fighter1, fighter2, getUnarmedEmpowerment(), random).fight();
        assertEquals(EXPECTED_HEALTH, fighter2.getHealth());
    }

    @Test
    void round_counter_attack_both_unarmed_do_damage() {
        final double COUNTER_ATTACK = 0.1;
        FighterStatus fighter1 = getSimpleFixedFighter();
        FighterStatus fighter2 = getSimpleFixedFighter();
        Empowerment empowerment = getUnarmedEmpowerment();
        CombatRandom random = mock(CombatRandom.class);
        when(random.getThrowWeaponRandom()).thenReturn(NO_THROW);
        when(random.getEscapeRandom()).thenReturn(NO_ESCAPE);
        when(random.getCounterAttackRandom()).thenReturn(COUNTER_ATTACK);
        when(random.getCounterEscapeRandom()).thenReturn(NO_ESCAPE);

        int EXPECTED_HEALTH_1 = fighter1.getHealth() - fighter2.getUnarmedDamage();
        int EXPECTED_HEALTH_2 = fighter2.getHealth() - fighter1.getUnarmedDamage();
        new Round(fighter1, fighter2, empowerment, random).fight();
        assertEquals(EXPECTED_HEALTH_1, fighter1.getHealth());
        assertEquals(EXPECTED_HEALTH_2, fighter2.getHealth());
    }

    @Test
    void round_throw_weapon_cause_damage() {
        final double THROW_WEAPON = 0.05;
        FighterStatus fighter1 = getSimpleFixedFighter();
        FighterStatus fighter2 = getSimpleFixedFighter();
        Weapon weapon = defaultWeaponFactory.create(WeaponIdentity.TRIDENT);
        Empowerment empowerment = new Empowerment(weapon);
        CombatRandom random = mock(CombatRandom.class);
        when(random.getThrowWeaponRandom()).thenReturn(THROW_WEAPON);
        when(random.getEscapeRandom()).thenReturn(NO_ESCAPE);
        double multiply = CombatAlgo.multiplyByAgility(fighter1.getAgility(), fighter2.getAgility());
        int EXPECTED_HEALTH = fighter2.getHealth() - (int) (weapon.getDamage().getKey() * (1 + multiply));

        new Round(fighter1, fighter2, empowerment, random).fight();
        assertEquals(EXPECTED_HEALTH, fighter2.getHealth());
    }

    @Test
    void round_throw_weapon_loss_weapon() {
        final double THROW_WEAPON = 0.05;
        FighterStatus fighter1 = getSimpleFixedFighter();
        FighterStatus fighter2 = getSimpleFixedFighter();
        Weapon weapon = defaultWeaponFactory.create(WeaponIdentity.TRIDENT);
        Empowerment empowerment = new Empowerment(weapon);
        CombatRandom random = mock(CombatRandom.class);
        when(random.getThrowWeaponRandom()).thenReturn(THROW_WEAPON);

        new Round(fighter1, fighter2, empowerment, random).fight();
        assertNull(fighter1.getHoldingWeapon());
    }
}
