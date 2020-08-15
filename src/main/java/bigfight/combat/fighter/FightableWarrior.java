package bigfight.combat.fighter;

import bigfight.combat.fighter.components.ActiveSkillList;
import bigfight.combat.fighter.components.DisposableWeaponList;
import bigfight.combat.fighter.components.SpecialSkillList;
import bigfight.model.warrior.component.AdvancedAttribute;
import bigfight.model.warrior.component.BasicAttribute;
import bigfight.model.weapon.struct.Damage;

public interface FightableWarrior {
    String getName();
    int getHealthValue();
    int getLevel();
    BasicAttribute getAgility();
    BasicAttribute getStrength();
    BasicAttribute getSpeed();
    AdvancedAttribute getWeaponAttribute();
    DisposableWeaponList getDisposableWeapons();
    SpecialSkillList getSpecialSkills();
    ActiveSkillList getActiveSkills();
    Damage getUnarmedDamage();

}
