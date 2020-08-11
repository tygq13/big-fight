package bigfight.model.warrior.builder;

import bigfight.combat.fighter.ActiveSkillList;
import bigfight.combat.fighter.DisposableWeaponList;
import bigfight.combat.fighter.SpecialSkillList;
import bigfight.model.warrior.component.AdvancedAttribute;
import bigfight.model.warrior.component.BasicAttribute;
import bigfight.model.weapon.struct.Damage;

public interface FightableWarrior {
    String getName();
    // clone issue
    BasicAttribute getStrength();
    BasicAttribute getAgility();
    BasicAttribute getSpeed();
    int getHealthValue();
    int getLevel();
    AdvancedAttribute getWeaponAttributeCopy();
    DisposableWeaponList getDisposableWeapons();
    SpecialSkillList getSpecialSkills();
    ActiveSkillList getActiveSkills();
    Damage getUnarmedDamage();

}
