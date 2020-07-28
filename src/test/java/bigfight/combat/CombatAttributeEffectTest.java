package bigfight.combat;

import bigfight.combat.attack.BigTypeAttack;
import bigfight.combat.attack.UnarmedAttack;
import bigfight.combat.fighter.FighterStatus;
import bigfight.combat.util.CombatRandom;
import bigfight.model.warrior.component.Empowerment;
import bigfight.model.warrior.component.WeaponAttribute;
import bigfight.model.weapon.Weapon;
import bigfight.ui.EnUi;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CombatAttributeEffectTest {
    private final double NO_ESCAPE = 1.0;
    private final double ESCAPE = 0.0;
    private final double NO_THROW = 1.0;
    private final double THROW = 0.0;
    private final double COUNTER_ATTACK = -1.0;
    private final double NO_COUNTER_ESCAPE = 1.0;

    @Test
    // skill medium, small and throw since they copy from big type.
    void test_extra_percentage_damage_effective_in_attack_example_big() {
        double EXTRA_PERCENTAGE = 0.2;
        int WEAPON_DAMAGE = 10;
        WeaponAttribute weaponAttribute = new WeaponAttribute();
        weaponAttribute.bigExtraPercentageDamage = EXTRA_PERCENTAGE;
        FighterStatus fighter1 = CombatTestUtil.createSimpleFixedFighter(weaponAttribute);
        FighterStatus fighter2 = CombatTestUtil.createSimpleFixedFighter();
        Weapon weapon = CombatTestUtil.createBigWeapon();
        CombatRandom random = mock(CombatRandom.class);
        when(random.getEscapeRandom()).thenReturn(NO_ESCAPE);
        when(random.getThrowWeaponRandom()).thenReturn(NO_THROW);
        when(random.getWeaponDamageRandom(anyInt(), anyInt())).thenReturn(WEAPON_DAMAGE);
        // test
        int expectedHealth = fighter2.getHealth() - (int) (WEAPON_DAMAGE * (1 + EXTRA_PERCENTAGE));
        new BigTypeAttack(fighter1, fighter2, weapon, random, mock(EnUi.class)).attack();
        assertEquals(expectedHealth, fighter2.getHealth());
    }

    @Test
    void test_extra_percentage_damage_effective_in_counter_attack() {
        double EXTRA_PERCENTAGE = 0.2;
        int WEAPON_DAMAGE = 10;
        WeaponAttribute weaponAttribute = new WeaponAttribute();
        weaponAttribute.bigExtraPercentageDamage = EXTRA_PERCENTAGE;
        FighterStatus fighter1 = CombatTestUtil.createSimpleFixedFighter();
        FighterStatus fighter2 = CombatTestUtil.createSimpleFixedFighter(weaponAttribute);
        Weapon weapon = CombatTestUtil.createBigWeapon();
        fighter2.changeWeapon(new Empowerment(weapon));
        CombatRandom random = mock(CombatRandom.class);
        when(random.getEscapeRandom()).thenReturn(NO_ESCAPE);
        when(random.getCounterAttackRandom()).thenReturn(COUNTER_ATTACK);
        when(random.getCounterEscapeRandom()).thenReturn(NO_COUNTER_ESCAPE);
        when(random.getWeaponDamageRandom(anyInt(), anyInt())).thenReturn(WEAPON_DAMAGE);
        // test
        int expectedHealth = fighter1.getHealth() - (int) (WEAPON_DAMAGE * (1 + EXTRA_PERCENTAGE));
        new UnarmedAttack(fighter1, fighter2, random, mock(EnUi.class)).attack();
        assertEquals(expectedHealth, fighter1.getHealth());
    }
}
