package bigfight.combat.fighter.components;

import bigfight.combat.util.CombatRandom;
import bigfight.data.DataConfig;
import bigfight.model.warrior.component.Empowerment;
import bigfight.model.weapon.Weapon;

public class CombatSelector {
    private ActiveSkillList activeSkillList;
    private SpecialSkillList specialSkillList;
    private DisposableWeaponList weaponList;
    private FighterFlag fighterFlag;

    public CombatSelector(ActiveSkillList activeSkillList, SpecialSkillList specialSkillList, DisposableWeaponList disposableWeaponList,
                          FighterFlag fighterFlag) {
        this.activeSkillList = activeSkillList;
        this.specialSkillList = specialSkillList;
        this.weaponList = disposableWeaponList;
        this.fighterFlag = fighterFlag;
    }

    public Empowerment selectEmpowerment(CombatRandom random) {
        int totalSize = weaponList.size() + activeSkillList.size();
        if (totalSize == 0 || random.selectUnarmed() < DataConfig.UNARMED_CHANCE) {
            // unarmed attack
            specialSkillList.postWeaponAuxiliary(random, totalSize);
            return new Empowerment((Weapon) null);
        }
        int weaponOrSkill = random.selectWeaponOrSkill(totalSize);
        if (weaponOrSkill < weaponList.size() && weaponList.size() > 0) {
            // create weapon
            Empowerment weapon = weaponList.select(random, fighterFlag);
            specialSkillList.postWeaponAuxiliary(random, totalSize);
            return weapon;
        } else {
            // create skills
            return activeSkillList.select(random);
        }
    }

    public Empowerment selectWeapon(CombatRandom random) {
        return weaponList.select(random);
    }

    public void selectHealingSkill(CombatRandom random, Health health, int level) {
        if (fighterFlag.noSelectSkill == 0) {
            int totalSize = weaponList.size() + specialSkillList.size() + activeSkillList.size();
            specialSkillList.preRoundAuxiliary(health, random, totalSize, level);
        }
    }

    public double selectBloodThirsty(CombatRandom random) {
        if (fighterFlag.noSelectSkill == 0) {
            return specialSkillList.selectBloodThirsty(random);
        }
        return 0;
    }

    public double selectBloodSacrifice(CombatRandom random) {
        if (fighterFlag.noSelectSkill == 0) {
            return specialSkillList.selectBloodSacrifice(random);
        }
        return 0;
    }

    public double selectHakiProtect(CombatRandom random) {
        if (fighterFlag.noSelectSkill == 0) {
            return specialSkillList.selectHakiProtect(random);
        }
        return 0;
    }

    public void randomDisposeWeapon(CombatRandom random) {
        weaponList.randomDispose(random);
    }
}
