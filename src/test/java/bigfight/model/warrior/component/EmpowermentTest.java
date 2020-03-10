package bigfight.model.warrior.component;

import bigfight.model.skill.SkillManager;
import bigfight.model.skill.SkillModel;
import bigfight.model.weapon.Weapon;
import bigfight.model.weapon.WeaponManager;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class EmpowermentTest {
    @Test
    // depend on the correctness of WeaponManager constructor and add(), getSize()
    void empowerment_correctly_add_weapon() {
        WeaponManager weaponManager = new WeaponManager();
        SkillManager dummySkillSet = mock(SkillManager.class);
        Weapon dummyWeapon = mock(Weapon.class);
        Empowerment test = new Empowerment(dummyWeapon);
        test.addTo(weaponManager, dummySkillSet);
        assertEquals(1, weaponManager.getSize());
    }

    @Test
    void empowerment_correctly_add_skill() {
        SkillManager skillManager = new SkillManager();
        WeaponManager dummyWeaponList = mock(WeaponManager.class);
        SkillModel dummySkill = mock(SkillModel.class);
        Empowerment test = new Empowerment(dummySkill);
        test.addTo(dummyWeaponList, skillManager);
        assertEquals(1, skillManager.getSize());
    }
}
