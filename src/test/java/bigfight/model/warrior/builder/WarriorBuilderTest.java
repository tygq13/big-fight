package bigfight.model.warrior.builder;

import bigfight.model.skill.SkillManager;
import bigfight.model.warrior.builder.Warrior;
import bigfight.model.warrior.builder.WarriorBuilder;
import bigfight.model.warrior.component.Agility;
import bigfight.model.warrior.component.Speed;
import bigfight.model.warrior.component.Strength;
import bigfight.model.weapon.WeaponManager;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WarriorBuilderTest {

    @Test
    void build_warrior_successful() {
        final String NAME = "TEST";
        final Strength STRENGTH = new Strength();
        final Agility AGILITY = new Agility();
        final Speed SPEED = new Speed();
        final int HEALTH = 10;
        final WeaponManager WEAPON_MANAGER = new WeaponManager();
        final SkillManager SKILL_MANAGER = new SkillManager();

        Warrior test = WarriorBuilder.stepBuilder()
                .name(NAME)
                .strength(STRENGTH)
                .agility(AGILITY)
                .speed(SPEED)
                .health(HEALTH)
                .weaponManager(WEAPON_MANAGER)
                .skillManager(SKILL_MANAGER)
                .build();
        assertNotNull(test);
        assertEquals(NAME, test.getName());
        assertEquals(STRENGTH.getBase(), test.getStrength());
        assertEquals(AGILITY.getBase(), test.getAgility());
        assertEquals(SPEED.getBase(), test.getSpeed());
        assertEquals(HEALTH, test.getHealth());
        assertNotNull(test.getWeaponManager());
        assertNotNull(test.getSkillManager());
    }
}
