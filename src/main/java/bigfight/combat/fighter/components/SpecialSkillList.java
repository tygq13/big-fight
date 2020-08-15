package bigfight.combat.fighter.components;

import bigfight.combat.fighter.Fighter;
import bigfight.combat.fighter.buff.Buff;
import bigfight.combat.util.CombatRandom;
import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.skills.special.FastHands;
import bigfight.model.skill.skills.special.MineWater;
import bigfight.model.skill.skills.special.ShadowMove;
import bigfight.model.skill.skills.special.SpecialSkill;
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
        int luckyDraw = random.selectSpecialSkill(baseSize);
        SpecialSkill specialSkill = null;
        if (luckyDraw < skillList.size()) {
            specialSkill = (SpecialSkill) skillList.get(luckyDraw);
        }
        if (specialSkill != null && specialSkill.isAuxiliary() && random.selectAuxiliarySkill() < specialSkill.getInvocationChance()) {
            if (specialSkill.getIdentity() == SkillIdentity.FAST_HANDS) {
                FastHands fastHands = (FastHands) specialSkill;
                return fastHands.createBuff();
            }
            if (specialSkill.getIdentity() == SkillIdentity.SHADOW_MOVE) {
                ShadowMove shadowMove = (ShadowMove) specialSkill;
                return shadowMove.createBuff();
            }
        }
        return null;
    }

    public void preRoundAuxiliary(Health health, CombatRandom random, int baseSize) {
        int luckyDraw = random.selectSpecialSkill(baseSize);
        SpecialSkill specialSkill = null;
        if (luckyDraw < skillList.size()) {
            specialSkill = (SpecialSkill) skillList.get(luckyDraw);
        }
        if (specialSkill != null && specialSkill.isAuxiliary() && random.selectAuxiliarySkill() < specialSkill.getInvocationChance()) {
            if (specialSkill.getIdentity() == SkillIdentity.MINE_WATER) {
                MineWater mineWater = (MineWater) specialSkill;
                mineWater.updateHealth(health);
            }
        }
    }
}
