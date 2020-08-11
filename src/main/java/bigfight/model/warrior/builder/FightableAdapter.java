package bigfight.model.warrior.builder;

import bigfight.combat.fighter.ActiveSkillList;
import bigfight.combat.fighter.DisposableWeaponList;
import bigfight.combat.fighter.SpecialSkillList;
import bigfight.data.DataConfig;
import bigfight.model.warrior.component.AdvancedAttribute;
import bigfight.model.warrior.component.BasicAttribute;
import bigfight.model.weapon.struct.Damage;


public class FightableAdapter implements FightableWarrior{
    private Warrior warrior;
    public FightableAdapter(Warrior warrior) {
        this.warrior = warrior;
    }


    @Override
    public String getName() {
        return warrior.getName();
    }

    @Override
    public BasicAttribute getStrength() {
        return warrior.getStrengthObj();
    }

    @Override
    public BasicAttribute getAgility() {
        return warrior.getAgilityObj();
    }

    @Override
    public BasicAttribute getSpeed() {
        return warrior.getSpeedObj();
    }

    @Override
    public int getHealthValue() {
        return warrior.getHealthObj().value();
    }

    @Override
    public int getLevel() {
        return warrior.getLevel();
    }

    @Override
    public AdvancedAttribute getWeaponAttributeCopy() {
        return (AdvancedAttribute) warrior.getAdvancedAttribute().clone();
    }

    @Override
    public DisposableWeaponList getDisposableWeapons() {
        return warrior.getWeaponManager().createDisposableList();
    }

    @Override
    public SpecialSkillList getSpecialSkills() {
        return warrior.getSkillManager().createSpecialList();
    }

    @Override
    public ActiveSkillList getActiveSkills() {
        return warrior.getSkillManager().createActiveList();
    }

    @Override
    public Damage getUnarmedDamage() {
        return DataConfig.DEFAULT_UNARMED_DAMAGE;
    }

}
