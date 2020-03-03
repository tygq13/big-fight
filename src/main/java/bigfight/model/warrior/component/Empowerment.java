// a wrapper class around weapon and skill
package bigfight.model.warrior.component;

import bigfight.model.skill.SkillManager;
import bigfight.model.skill.SkillModel;
import bigfight.model.weapon.Weapon;
import bigfight.model.weapon.WeaponManager;
import bigfight.model.weapon.WeaponModel;

public class Empowerment {
    private SkillModel skill;
    private Weapon weapon;

    public Empowerment() {}

    Empowerment(SkillModel model) {
        skill = model;
    }

    Empowerment(Weapon model) {
        weapon = model;
    }

    public void addTo(WeaponManager weaponManager, SkillManager skillManager) {
        if (skill == null) {
            // add to weapon manager
            weaponManager.add(weapon);
        } else {
            // add to skill manager
            skillManager.add(skill);
        }
    }
}
