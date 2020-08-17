package bigfight.combat.fighter.components;

import bigfight.combat.fighter.buff.Buffs;
import bigfight.combat.util.CombatRandom;
import bigfight.data.DataConfig;
import bigfight.model.skill.skills.special.BloodSacrifice;
import bigfight.model.skill.skills.special.BloodThirsty;
import bigfight.model.skill.skills.special.HakiProtectUsable;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.warrior.component.Empowerment;
import bigfight.model.weapon.Weapon;

public class CombatSelector {
    private ActiveSkillList activeSkillList;
    private SpecialSkillList specialSkillList;
    private DisposableWeaponList weaponList;

    public CombatSelector(ActiveSkillList activeSkillList, SpecialSkillList specialSkillList, DisposableWeaponList disposableWeaponList) {
        this.activeSkillList = activeSkillList;
        this.specialSkillList = specialSkillList;
        this.weaponList = disposableWeaponList;
    }

    public Empowerment selectEmpowerment(CombatRandom random, FighterFlag fighterFlag) {
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

    public void selectHealingSkill(CombatRandom random, Health health) {
        int totalSize = weaponList.size() + specialSkillList.size() + activeSkillList.size();
        specialSkillList.preRoundAuxiliary(health, random, totalSize);
    }

    public double selectBloodThirsty(CombatRandom random) {
        if (specialSkillList.contains(SkillIdentity.BLOOD_THIRSTY)) {
            BloodThirsty bloodThirsty = (BloodThirsty) specialSkillList.get(SkillIdentity.BLOOD_THIRSTY);
            if (random.getBloodThirstyRandom() < bloodThirsty.getInvocationChance()) {
                return bloodThirsty.getLifeStealPercentage();
            }
        }
        return 0;
    }

    public double selectBloodSacrifice(CombatRandom random) {
        if (specialSkillList.contains(SkillIdentity.BLOOD_SACRIFICE)) {
            BloodSacrifice bloodSacrifice = (BloodSacrifice) specialSkillList.get(SkillIdentity.BLOOD_SACRIFICE);
            if (random.getBloodThirstyRandom() < bloodSacrifice.getInvocationChance()) {
                return bloodSacrifice.getLifeStealPercentage();
            }
        }
        return 0;
    }

    public double selectHakiProtect(CombatRandom random) {
        if (specialSkillList.contains(SkillIdentity.HAKI_PROTECT)) {
            HakiProtectUsable hakiProtect = (HakiProtectUsable) specialSkillList.get(SkillIdentity.HAKI_PROTECT);
            if (hakiProtect.getRemainingUsage() > 0 && random.getHakiProtectRandom() < hakiProtect.getInvocationChance()) {
                hakiProtect.invoke();
                return hakiProtect.getProtectionPercentage();
            }
        }
        return 0;
    }

    public void randomDisposeWeapon(CombatRandom random) {
        weaponList.randomDispose(random);
    }
}
