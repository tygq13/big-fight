package bigfight.combat.fighter;

import bigfight.combat.util.CombatRandom;
import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.skills.special.FastHands;
import bigfight.model.skill.skills.special.MineWater;
import bigfight.model.skill.skills.special.ShadowMoveUsable;
import bigfight.model.skill.skills.special.SpecialSkill;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.skill.struct.SkillList;
import bigfight.model.skill.struct.SkillType;

import java.util.Map;

public class SpecialSkillList extends SkillList {

    public SpecialSkillList() {
        super();
    }

    public void addSpecialFromMap(Map<SkillIdentity, SkillModel> skillMap) {
        for (Map.Entry<SkillIdentity, SkillModel> model: skillMap.entrySet()) {
            if (model.getValue().getType() == SkillType.SPECIAL) {
                SpecialSkill specialSkill = (SpecialSkill) model.getValue();
                add(specialSkill.getUsableInstance());
            }
        }
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
