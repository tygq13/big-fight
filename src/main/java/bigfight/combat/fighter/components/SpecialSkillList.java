package bigfight.combat.fighter.components;

import bigfight.combat.fighter.buff.Buff;
import bigfight.combat.util.CombatRandom;
import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.skills.special.*;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.skill.struct.SkillType;

import java.util.ArrayList;
import java.util.Map;

public class SpecialSkillList {
    private ArrayList<SkillModel> skillList;

    public SpecialSkillList() {
        skillList = new ArrayList<>();
    }

    public SpecialSkillList(Map<SkillIdentity, SkillModel> skillMap) {
        skillList = new ArrayList<>();
        addSpecialFromMap(skillMap);
    }

    private void addSpecialFromMap(Map<SkillIdentity, SkillModel> skillMap) {
        for (Map.Entry<SkillIdentity, SkillModel> model: skillMap.entrySet()) {
            if (model.getValue().getType() == SkillType.SPECIAL) {
                SpecialSkill specialSkill = (SpecialSkill) model.getValue();
                skillList.add(specialSkill.getUsableInstance());
            }
        }
    }

    public void add(SkillModel model) {
        if (model.getType() == SkillType.SPECIAL) {
            skillList.add(model);
        }
    }

    public int size() {
        return skillList.size();
    }

    public SkillModel get(SkillIdentity identity) {
        for(SkillModel model: skillList) {
            if (model.getIdentity() == identity) {
                return model;
            }
        }
        // better to throw exception
        return null;
    }

    public boolean contains(SkillIdentity identity) {
        for(SkillModel model: skillList) {
            if (model.getIdentity() == identity) {
                return true;
            }
        }
        return false;
    }

    public Buff postWeaponAuxiliary(CombatRandom random, int baseSize) {
        if (baseSize == 0 || skillList.size() == 0) {
            return null;
        }
        int luckyDraw = random.selectSpecialSkill(baseSize);
        SkillModel skill = skillList.get(luckyDraw);
        switch (skill.getIdentity()) {
            case FAST_HANDS: {
                FastHands fastHands = (FastHands) skill;
                return fastHands.createBuff();
            }
            case SHADOW_MOVE: {
                ShadowMove shadowMove = (ShadowMove) skill;
                return shadowMove.createBuff();
            }
        }
        return null;
    }

    // todo: bad to add unrelated parameter "level", refactor this.
    public void preRoundAuxiliary(Health health, CombatRandom random, int baseSize, int level) {
        if (baseSize == 0 || skillList.size() == 0) {
            return;
        }
        int luckyDraw = random.selectSpecialSkill(baseSize);
        SkillModel skill = skillList.get(luckyDraw);
        switch (skill.getIdentity()) {
            case MINE_WATER: {
                MineWater mineWater = (MineWater) skill;
                if (random.selectHealingSkillRandom() < mineWater.getInvocationChance()) {
                    mineWater.updateHealth(health);
                }
                break;
            }
            case HEAL: {
                HealUsable healUsable = (HealUsable) skill;
                int regen = healUsable.getBaseHeal() + (int)(level * healUsable.getLevelMultiply());
                health.update(health.value() + regen);
                healUsable.invoke();
                if (healUsable.getRemainingUsage() == 0) {
                    skillList.remove(healUsable);
                }
                break;
            }
        }
    }

    public double selectBloodThirsty(CombatRandom random) {
        if (contains(SkillIdentity.BLOOD_THIRSTY)) {
            BloodThirsty bloodThirsty = (BloodThirsty) get(SkillIdentity.BLOOD_THIRSTY);
            if (random.getSingleSpecialRandom() < bloodThirsty.getInvocationChance()) {
                return bloodThirsty.getLifeStealPercentage();
            }
        }
        return 0;
    }

    public double selectBloodSacrifice(CombatRandom random) {
        if (contains(SkillIdentity.BLOOD_SACRIFICE)) {
            BloodSacrifice bloodSacrifice = (BloodSacrifice) get(SkillIdentity.BLOOD_SACRIFICE);
            if (random.getSingleSpecialRandom() < bloodSacrifice.getInvocationChance()) {
                return bloodSacrifice.getLifeStealPercentage();
            }
        }
        return 0;
    }

    public double selectHakiProtect(CombatRandom random) {
        if (contains(SkillIdentity.HAKI_PROTECT)) {
            HakiProtectUsable hakiProtect = (HakiProtectUsable) get(SkillIdentity.HAKI_PROTECT);
            if (hakiProtect.getRemainingUsage() > 0 && random.getSingleSpecialRandom() < hakiProtect.getInvocationChance()) {
                hakiProtect.invoke();
                return hakiProtect.getProtectionPercentage();
            }
        }
        return 0;
    }
}
