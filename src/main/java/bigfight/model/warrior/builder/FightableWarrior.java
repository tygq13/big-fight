package bigfight.model.warrior.builder;

import bigfight.combat.fighter.ActiveSkillList;
import bigfight.combat.fighter.DisposableWeaponList;
import bigfight.combat.fighter.SpecialSkillList;
import bigfight.model.skill.SkillManager;
import bigfight.model.warrior.component.AdvancedAttribute;
import bigfight.model.weapon.struct.Damage;

public interface FightableWarrior {
    String getName();
    int getStrength();
    int getAgility();
    int getSpeed();
    int getHealth();
    int getLevel();
    AdvancedAttribute getWeaponAttributeCopy();
    DisposableWeaponList getDisposableWeapons();
    SpecialSkillList getSpecialSkills();
    ActiveSkillList getActiveSkills();
    Damage getUnarmedDamage();

}
