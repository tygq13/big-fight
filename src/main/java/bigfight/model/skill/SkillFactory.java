package bigfight.model.skill;

import bigfight.model.skill.skills.*;
import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.skills.permanent.*;
import bigfight.model.skill.skills.special.*;
import bigfight.model.skill.struct.SkillIdentity;

public class SkillFactory {
    private SkillData skillData;

    public SkillFactory(SkillData data) {
        skillData = data;
    }

    public SkillModel create(SkillIdentity identity) {
        switch (identity) {
            case BORN_AS_STRONG:
                return new BornAsStrong(skillData.getWithStar(identity, 0));
            case AGILE_BODY:
                return new AgileBody(skillData.getWithStar(identity, 0));
            case A_STEP_AHEAD:
                return new AStepAhead(skillData.getWithStar(identity, 0));
            case ROAR:
                return new Roar(skillData.getWithStar(identity, 0));
            case APPARENT_DEATH:
                return new ApparentDeath(skillData.getWithStar(identity, 0));
            case STRONG_PHYSIQUE:
                return new StrongPhysique(skillData.getWithStar(identity, 0));
            case BALANCED_GROWTH:
                return new BalancedGrowth(skillData.getWithStar(identity, 0));
            case WEAPONS_HANDY:
                return new WeaponsHandy(skillData.getWithStar(identity, 0));
            case BODY_COMBAT_SKILLED:
                return new BodyCombatSkilled(skillData.getWithStar(identity, 0));
            case SIXTH_SENSE:
                return new SixSense(skillData.getWithStar(identity, 0));
            case BOLT_FROM_THE_BLUE:
                return new BoltFromTheBlue(skillData.getWithStar(identity, 0));
            case FAST_HANDS:
                return new FastHands(skillData.getWithStar(identity, 0));
            case HAKI_PROTECT:
                return new HakiProtect(skillData.getWithStar(identity, 0));
            case SEA_REFLECT:
                return new SeaReflect(skillData.getWithStar(identity, 0));
            case STONE_SKIN:
                return new StoneSkin(skillData.getWithStar(identity, 0));
            case RIPPLESLESS_STEPS:
                return new RipplelessSteps(skillData.getWithStar(identity, 0));
            case TORNADO:
                return new Tornado(skillData.getWithStar(identity, 0));
            case HEAVY_USUAL:
                return new HeavyUsual(skillData.getWithStar(identity, 0));
            case OPT_FOR_LIGHTNESS:
                return new OptForLightness(skillData.getWithStar(identity, 0));
            case ONE_PUNCH:
                return new OnePunch(skillData.getWithStar(identity, 0));
            case DISARM:
                return new Disarm(skillData.getWithStar(identity, 0));
            case SHADOW_MOVE:
                return new ShadowMove(skillData.getWithStar(identity, 0));
            case MINE_WATER:
                return new MineWater(skillData.getWithStar(identity, 0));
            case GLUE:
                return new Glue(skillData.getWithStar(identity, 0));
            case ANGELS_WINGS:
                return new AngelsWings(skillData.getWithStar(identity, 0));
            case FOSHAN_KICK:
                return new FoshanKick(skillData.getWithStar(identity, 0));
            case TICKLE:
                return new Tickle(skillData.getWithStar(identity, 0));
            case WEAPON_RAINSTORM:
                return new WeaponRainstorm(skillData.getWithStar(identity, 0));
            case BLOOD_THIRSTY:
                return new BloodThirsty(skillData.getWithStar(identity, 0));
            case TENDON_SHAPING_CLASSIC:
                return new TendonShapingClassic(skillData.getWithStar(identity, 0));
            case DIM_HIT:
                return new DimHit(skillData.getWithStar(identity, 0));
            case DASH:
                return new Dash(skillData.getWithStar(identity, 0));
            case SHAKE:
                return new Shake(skillData.getWithStar(identity, 0));
            case WINDY_KICK:
                return new WindyKick(skillData.getWithStar(identity, 0));
            case FOCUS_ON_HEART:
                return new FocusOnHeart(skillData.getWithStar(identity, 0));
            case BLOOD_SACRIFICE:
                return new BloodSacrifice(skillData.getWithStar(identity, 0));
            case LUCKY_OR_NOT:
                return new LuckyOrNot(skillData.getWithStar(identity, 0));
            case QI_GONG:
                return new QiGong(skillData.getWithStar(identity, 0));
            case SHOCK_WAVE:
                return new ShockWave(skillData.getWithStar(identity, 0));
            case ASSASSINS_TECHNIQUE:
                return new AssassinsTechnique(skillData.getWithStar(identity, 0));
            case VALIANT_FORCE:
                return new ValiantForce(skillData.getWithStar(identity, 0));
            case SWORD_ART:
                return new SwordArt(skillData.getWithStar(identity, 0));
            case ACUPOINTER:
                return new Acupointer(skillData.getWithStar(identity, 0));
            case TAI_CHI:
                return new TaiChi(skillData.getWithStar(identity, 0));
            default:
                return null;
        }
    }
}
