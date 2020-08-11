package bigfight.model.warrior.builder;

import bigfight.combat.fighter.ActiveSkillList;
import bigfight.combat.fighter.DisposableWeaponList;
import bigfight.combat.fighter.SpecialSkillList;
import bigfight.model.skill.SkillManager;
import bigfight.model.warrior.component.AdvancedAttribute;


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
    public int getStrength() {
        return warrior.getStrength();
    }

    @Override
    public int getAgility() {
        return warrior.getAgility();
    }

    @Override
    public int getSpeed() {
        return warrior.getSpeed();
    }

    @Override
    public int getHealth() {
        return warrior.getHealth();
    }

    @Override
    public int getLevel() {
        return warrior.getLevel();
    }

    @Override
    public AdvancedAttribute getWeaponAttributeCopy() {
        return warrior.getWeaponAttributeCopy();
    }

    @Override
    public DisposableWeaponList getDisposableWeapons() {
        return warrior.getWeaponManager().createDisposableList();
    }

    @Override
    public SkillManager getSkillManager() {
        return warrior.getSkillManager();
    }

    @Override
    public SpecialSkillList getSpecialSkills() {
        return warrior.getSkillManager().createSpecialList();
    }

    @Override
    public ActiveSkillList getActiveSkills() {
        return warrior.getSkillManager().createActiveList();
    }

}
