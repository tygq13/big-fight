package bigfight.combat.fighter;

import bigfight.combat.util.CombatRandom;
import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.skills.special.FastHands;
import bigfight.model.skill.skills.special.MineWater;
import bigfight.model.skill.skills.special.ShadowMoveUsable;
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

    public void select(FighterFlag fighterFlag, CombatRandom random, int baseSize) {
        if (contains(SkillIdentity.FAST_HANDS)) {
            FastHands fastHands = (FastHands) get(SkillIdentity.FAST_HANDS);
            double chance = (1.0 / baseSize) * fastHands.getExtraChance();
            if (random.selectAuxiliarySkillRandom() < chance) {
                fighterFlag.fastHandsFlag = true;
            }
        }
        if (contains(SkillIdentity.SHADOW_MOVE)) {
            ShadowMoveUsable shadowMove = (ShadowMoveUsable) get(SkillIdentity.SHADOW_MOVE);
            double chance = (1.0 / baseSize) * shadowMove.getInvocationChance();
            if (random.selectAuxiliarySkillRandom() < chance) {
                fighterFlag.shadowMoveFlag = true;
                fighterFlag.shadowMoveRound = shadowMove.getMaxRound();
            }
        }
        if (contains(SkillIdentity.MINE_WATER)) {
            MineWater mineWater = (MineWater) get(SkillIdentity.MINE_WATER);
            double chance = (1.0 / baseSize) * mineWater.getInvocationChance();
            if (random.selectAuxiliarySkillRandom() < chance) {
                fighterFlag.mineWaterFlag = true;
            }
        }
    }
}
