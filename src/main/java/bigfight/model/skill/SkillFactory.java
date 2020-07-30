package bigfight.model.skill;

import bigfight.model.skill.skills.*;
import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.struct.SkillIdentity;

public class SkillFactory {
    private SkillData skillData;

    public SkillFactory(SkillData data) {
        skillData = data;
    }

    public SkillModel create(SkillIdentity identity) {
        switch (identity) {
            case BORN_AS_STRONG:
                return new BornAsStrong(skillData.getWithStar(identity, 1));
            case AGILE_BODY:
                return new AgileBody(skillData.getWithStar(identity, 1));
            case A_STEP_AHEAD:
                return new AStepAhead(skillData.getWithStar(identity, 1));
            case ROAR:
                return new Roar(skillData.getWithStar(identity, 1));
            case APPARENT_DEATH:
                return new ApparentDeath(skillData.getWithStar(identity, 1));
            case STRONG_PHYSIQUE:
                return new StrongPhysique(skillData.getWithStar(identity, 1));
            case BALANCED_GROWTH:
                return new BalancedGrowth(skillData.getWithStar(identity, 1));
            case WEAPONS_HANDY:
                return new WeaponsHandy(skillData.getWithStar(identity, 1));
            case BODY_COMBAT_SKILLED:
                return new BodyCombatSkilled(skillData.getWithStar(identity, 1));
            case SIXTH_SENSE:
                return new SixSense(skillData.getWithStar(identity, 1));
            case BOLT_FROM_THE_BLUE:
                return new BoltFromTheBlue(skillData.getWithStar(identity, 1));
            case FAST_HANDS:
                return new FastHands(skillData.getWithStar(identity, 1));
            case HAKI_PROTECT:
                return new HakiProtect(skillData.getWithStar(identity, 1));
            case SEA_IS_UNFATHOMABLE:
                return new SeaIsUnfathomable(skillData.getWithStar(identity, 1));
            default:
                return null;
        }
    }
}
