package bigfight.combat;

import bigfight.combat.fighter.Fighter;
import bigfight.combat.fighter.FighterStatus;

import bigfight.model.skill.SkillData;
import bigfight.model.skill.SkillFactory;
import bigfight.model.skill.SkillModel;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.warrior.component.Empowerment;
import bigfight.model.weapon.Weapon;
import bigfight.model.weapon.WeaponData;
import bigfight.model.weapon.WeaponFactory;
import bigfight.model.weapon.struct.WeaponIdentity;

import java.util.Random;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RoundTest {
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
        final double NO_ESCAPE = 1.0;
        FighterStatus fighter1 = getSimpleFixedFighter();
        FighterStatus fighter2 = getSimpleFixedFighter();
        Weapon weapon = defaultWeaponFactory.create(WeaponIdentity.TRIDENT);
        Empowerment empowerment = new Empowerment(weapon);
        Random random = mock(Random.class);
        when(random.nextDouble()).thenReturn(NO_ESCAPE);

        // test
        int damage = weapon.getDamage().getKey();
        final int EXPECTED_HEALTH = fighter2.getHealth() - damage;
        new Round(fighter1, fighter2, empowerment, random).fight();
        assertEquals(EXPECTED_HEALTH, fighter2.getHealth());
    }

    @Test
    void round_give_damage_in_simple_skill_attack_with_example_roar() {
        final double NO_ESCAPE = 1.0;
        FighterStatus fighter1 = getSimpleFixedFighter();
        FighterStatus fighter2 = getSimpleFixedFighter();
        SkillModel skill = defaultSkillFactory.create(SkillIdentity.ROAR);
        Empowerment empowerment = new Empowerment(skill);
        Random random = mock(Random.class);
        when(random.nextDouble()).thenReturn(NO_ESCAPE);

        // test
        final int DAMAGE = 15; // the damage of one start ROAR is fixed at 15.
        final int EXPECTED_HEALTH = fighter2.getHealth() - DAMAGE;
        new Round(fighter1, fighter2, empowerment, random).fight();
        assertEquals(EXPECTED_HEALTH, fighter2.getHealth());
    }

    @Test
    void round_give_damage_in_simple_attack_with_unarmed() {
        final double NO_ESCAPE = 1.0;
        FighterStatus fighter1 = getSimpleFixedFighter();
        FighterStatus fighter2 = getSimpleFixedFighter();
        Empowerment empowerment = getUnarmedEmpowerment();
        Random random = mock(Random.class);
        when(random.nextDouble()).thenReturn(NO_ESCAPE);

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
        Random random = mock(Random.class);
        when(random.nextDouble()).thenReturn(ignore - Math.ulp(1));
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
        double escape = CombatAlgo.escapeByAgility(AGILITY1, AGILITY2);
        Random random = mock(Random.class);
        when(random.nextDouble()).thenReturn(0.0).thenReturn(escape - Math.ulp(1));
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
        Random random = new Random();
        int EXPECTED_HEALTH = fighter2.getHealth() - (int) (fighter1.getUnarmedDamage() * (1 + multiply)); // escaped, no damage done

        new Round(fighter1, fighter2, getUnarmedEmpowerment(), random).fight();
        assertEquals(EXPECTED_HEALTH, fighter2.getHealth());
    }
}
