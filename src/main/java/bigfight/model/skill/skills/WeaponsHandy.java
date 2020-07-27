package bigfight.model.skill.skills;

import bigfight.model.skill.struct.SkillStruct;
import bigfight.model.warrior.component.WeaponAttribute;

public class WeaponsHandy extends SkillModel {
    private final double WEAPONS_HANDY_ONE = 0.2;

    public WeaponsHandy(SkillStruct skill) {
        super(skill);
    }

    public double getExtra() {
        return WEAPONS_HANDY_ONE;
    }

    public void upgrade(WeaponAttribute weaponAttribute) {
        weaponAttribute.bigExtraPercentageDamage += getExtra();
        weaponAttribute.mediumExtraPercentageDamage += getExtra();
        weaponAttribute.smallExtraPercentageDamage += getExtra();
    }
}

