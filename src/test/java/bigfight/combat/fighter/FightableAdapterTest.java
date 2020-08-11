package bigfight.combat.fighter;

import bigfight.model.skill.SkillManager;
import bigfight.model.warrior.builder.Warrior;
import bigfight.model.warrior.component.AdvancedAttribute;
import bigfight.model.warrior.component.BasicAttribute;
import bigfight.model.weapon.WeaponManager;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

public class FightableAdapterTest {

    @Test
    void adapter_does_not_change_original_value() {
        Warrior mockWarrior = mock(Warrior.class);
        BasicAttribute strength = new BasicAttribute(5);
        BasicAttribute agility = new BasicAttribute(5);
        BasicAttribute speed = new BasicAttribute(5);
        AdvancedAttribute advancedAttribute = new AdvancedAttribute();
        when(mockWarrior.getSpeedObj()).thenReturn(speed);
        when(mockWarrior.getAgilityObj()).thenReturn(agility);
        when(mockWarrior.getStrengthObj()).thenReturn(strength);
        when(mockWarrior.getAdvancedAttribute()).thenReturn(advancedAttribute);
        when(mockWarrior.getWeaponManager()).thenReturn(new WeaponManager());
        when(mockWarrior.getSkillManager()).thenReturn(new SkillManager());
        // expect
        final int expectedStrength = strength.value();
        final int expectedAgility = agility.value();
        final int expectedSpeed = speed.value();
        final double expectedDamage = advancedAttribute.weaponCriticalDamage;
        // test
        FightableAdapter test = new FightableAdapter(mockWarrior);
        test.getStrength().addToAddition(10);
        test.getAgility().addToAddition(10);
        test.getSpeed().addToAddition(10);
        test.getWeaponAttribute().weaponCriticalDamage += 10;
        assertEquals(expectedStrength, strength.value());
        assertEquals(expectedAgility, agility.value());;
        assertEquals(expectedSpeed, speed.value());
        assertEquals(expectedDamage, advancedAttribute.weaponCriticalDamage);
    }

}
