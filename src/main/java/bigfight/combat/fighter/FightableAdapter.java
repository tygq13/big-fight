package bigfight.combat.fighter;

import bigfight.data.DataConfig;
import bigfight.model.warrior.builder.Warrior;
import bigfight.model.warrior.component.AdvancedAttribute;
import bigfight.model.warrior.component.Attribute;
import bigfight.model.warrior.component.BasicAttribute;
import bigfight.model.weapon.struct.Damage;


public class FightableAdapter implements FightableWarrior {
    private Warrior warrior;
    public FightableAdapter(Warrior warrior) {
        this.warrior = warrior;
    }


    @Override
    public String getName() {
        return warrior.getName();
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
    public BasicAttribute getAgility() {
        return (BasicAttribute) warrior.getAgilityObj().clone();
    }

    @Override
    public BasicAttribute getStrength() {
        return (BasicAttribute) warrior.getStrengthObj().clone();
    }

    @Override
    public BasicAttribute getSpeed() {
        return (BasicAttribute) warrior.getSpeedObj().clone();
    }

    @Override
    public AdvancedAttribute getWeaponAttribute() {
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
