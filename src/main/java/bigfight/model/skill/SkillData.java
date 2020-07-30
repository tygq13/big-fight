package bigfight.model.skill;

import bigfight.model.skill.struct.*;
import com.sun.tools.attach.AgentInitializationException;

import java.util.HashMap;
import java.util.Map;

// note: permanent skill type is untested.
public class SkillData {

    private final SkillStruct BORN_AS_STRONG_ZERO = new SkillStruct(
            SkillType.PERMANENT,
            "You are a warrior with enormous strength.\n" +
                    "Your strength increase: basic strength * 50% + 3\n" +
                    "After level 60, you have high chance of getting this skill at every 5 levels.",
            SkillIdentity.BORN_AS_STRONG,
            "born as strong"
    );

    private final SkillStructArray BORN_AS_STRONG_ARRAY = new SkillStructArray(
            BORN_AS_STRONG_ZERO
    );

    private final SkillStruct AGILE_BODY_ZERO = new SkillStruct(
            SkillType.PERMANENT,
            "Your agile body dodges every attacks.\n" +
                    "Your agility increase: basic agility * 50% + 3.\n" +
                    "After level 60, you have high chance of getting this skill at every 5 levels.",
            SkillIdentity.AGILE_BODY,
            "agile body"
    );

    private final SkillStructArray AGILE_BODY_ARRAY = new SkillStructArray(
            AGILE_BODY_ZERO
    );

    private final SkillStruct A_STEP_AHEAD_ZERO = new SkillStruct(
            SkillType.PERMANENT,
            "You are always a step ahead of cataclysm.\n" +
                    "Your speed increase: basic speed * 50% + 3.\n" +
                    "After level 60, you have high chance of getting this skill at every 5 levels.",
            SkillIdentity.A_STEP_AHEAD,
            "a step ahead"
    );

    private final SkillStructArray A_STEP_AHEAD_ARRAY = new SkillStructArray(
            A_STEP_AHEAD_ZERO
    );

    private final SkillStruct ROAR_ZERO = new SkillStruct(
            SkillType.ACTIVE,
            "Your fierce roar scares enemies away.\n" +
                    "Ignore opponent's next round if hit. Cause damage 15.",
            SkillIdentity.ROAR,
            "roar"
    );

    private final SkillStructArray ROAR_ARRAY = new SkillStructArray(
            ROAR_ZERO
    );

    private final SkillStruct APPARENT_DEATH_ZERO = new SkillStruct(
            SkillType.SPECIAL,
            "The utmost danger lies in the enemy's apparent death.\n" +
                    "Upon fatal damage, you will not die but leave with 1 health.\n" +
                    "After level 60, you have high chance of getting this skill at every 5 levels.",
            SkillIdentity.APPARENT_DEATH,
            "apparent death"
    );

    private final SkillStructArray APPARENT_DEATH_ARRAY = new SkillStructArray(
            APPARENT_DEATH_ZERO
    );

    private final SkillStruct STRONG_PHYSIQUE_ZERO = new SkillStruct(
            SkillType.PERMANENT,
            "Persistent training makes your body particularly strong. \n" +
                    "Your health increases by 15% + 8. \n" +
                    "After level 60, you have high chance of getting this skill at every 5 levels.\n",
            SkillIdentity.STRONG_PHYSIQUE,
            "strong physique"
    );

    private final SkillStructArray STRONG_PHYSIQUE_ARRAY = new SkillStructArray(
            STRONG_PHYSIQUE_ZERO
    );

    private final SkillStruct BALANCED_GROWTH_ZERO = new SkillStruct(
            SkillType.PERMANENT,
            "You do not leave out any weakness. \n" +
                    "All your properties increase by basic *21% + 2.\n",
            SkillIdentity.BALANCED_GROWTH,
            "balanced growth"
    );

    private final SkillStructArray BALANCED_GROWTH_ARRAY = new SkillStructArray(
            BALANCED_GROWTH_ZERO
    );

    private final SkillStruct WEAPONS_HANDY_ZERO = new SkillStruct(
            SkillType.PERMANENT,
            "All kinds of weapons become handy in your hands. \n" +
                    "Your weapon damage increases by 20%.\n",
            SkillIdentity.WEAPONS_HANDY,
            "weapons handy"
    );

    private final SkillStructArray WEAPONS_HANDY_ARRAY = new SkillStructArray(
            WEAPONS_HANDY_ZERO
    );

    private final SkillStruct BODY_COMBAT_SKILLED_ZERO = new SkillStruct(
            SkillType.PERMANENT,
            "You focus on body-combat all along.\n" +
                    "Your unarmed attack damage increases by 20%.\n",
            SkillIdentity.BODY_COMBAT_SKILLED,
            "body combat skilled"
    );

    private final SkillStructArray BODY_COMBAT_SKILLED_ARRAY = new SkillStructArray(
            BODY_COMBAT_SKILLED_ZERO
    );

    private final SkillStruct SIXTH_SENSE_ZERO = new SkillStruct(
            SkillType.PERMANENT,
            "You watch for opponent’s weakness when they attack and clinch the chance to counter-attack.\n" +
                    "You have 30% chance of counter-attack upon inflicted damage.\n",
            SkillIdentity.SIXTH_SENSE,
            "sixth sense"
    );

    private final SkillStructArray SIXTH_SENSE_ARRAY = new SkillStructArray(
            SIXTH_SENSE_ZERO
    );

    private final SkillStruct BOLT_FROM_THE_BLUE_ZERO = new SkillStruct(
            SkillType.ACTIVE,
            "This break-up letter is the bolt from the blue to him/her. \n" +
                    "Cause damage 15 + your level * 1.5. \n",
            SkillIdentity.BOLT_FROM_THE_BLUE,
            "bolt from the blue"
    );

    private final SkillStructArray BOLT_FROM_THE_BLUE_ARRAY = new SkillStructArray(
            BOLT_FROM_THE_BLUE_ZERO
    );

    private final SkillStruct FAST_HANDS_ZERO = new SkillStruct(
            SkillType.SPECIAL,
            "Your fast hands make the attack non-stop.\n" +
                    "Extra 20% chance of using this skill. Attack the opponent twice.\n",
            SkillIdentity.FAST_HANDS,
            "fast hands"
    );

    private final SkillStructArray FAST_HANDS_ARRAY = new SkillStructArray(
            FAST_HANDS_ZERO
    );

    private final SkillStruct HAKI_PROTECT_ZERO = new SkillStruct(
            SkillType.SPECIAL,
            "The overlord can knock off opponent simply by haki.\n" +
                    "15% chance of repelling 50% damage of unarmed and weapons attacks.\n" +
                    "You can use this skill twice.\n" +
                    "After level 60, you have high chance of getting this skill at every 5 levels.\n",
            SkillIdentity.HAKI_PROTECT,
            "haki protect"
    );

    private final SkillStructArray HAKI_PROTECT_ARRAY = new SkillStructArray(
            HAKI_PROTECT_ZERO
    );

    private final SkillStruct SEA_IS_UNFATHOMABLE_ZERO = new SkillStruct(
            SkillType.SPECIAL,
            "Your strong internal organs make you unafraid of any attack.\n" +
                    "Reflect 100% damage. \n" +
                    "You can only use this skill once.\n" +
                    "After level 60, you have high chance of getting this skill at every 5 levels.\n",
            SkillIdentity.SEA_IS_UNFATHOMABLE,
            "sea is unfathomable"
    );

    private final SkillStructArray SEA_IS_UNFATHOMABLE_ARRAY = new SkillStructArray(
            SEA_IS_UNFATHOMABLE_ZERO
    );

    private final SkillStruct STONE_SKIN_ZERO = new SkillStruct(
            SkillType.PERMANENT,
            "Your innate stone skin makes you very tough.\n" +
                    "Reduce 20% of unarmed and weapon damages.\n" +
                    "After level 60, you have high chance of getting this skill at every 5 levels.\n",
            SkillIdentity.STONE_SKIN,
            "stone skin"
    );

    private final SkillStructArray STONE_SKIN_ARRAY = new SkillStructArray(
            STONE_SKIN_ZERO
    );

    private final SkillStruct RIPPLELESS_STEPS_ZERO = new SkillStruct(
            SkillType.PERMANENT,
            "No one can guess your next move.\n" +
                    "Increases 7% chance of evasion.\n" +
                    "After level 60, you have high chance of getting this skill at every 5 levels.\n",
            SkillIdentity.RIPPLESLESS_STEPS,
            "ripple-less steps"
    );

    private final SkillStructArray RIPPLELESS_STEPS_ARRAY = new SkillStructArray(
            RIPPLELESS_STEPS_ZERO
    );

    private final SkillStruct TORNADO_ZERO = new SkillStruct(
            SkillType.ACTIVE,
            "No one can endure the ripping force in the centre of a tornado.\n" +
                    "Cause damage of 20 + strength*80%.\n",
            SkillIdentity.TORNADO,
            "tornado"
    );

    private final SkillStructArray TORNADO_ARRAY = new SkillStructArray(
            TORNADO_ZERO
    );

    private final SkillStruct HEAVY_USUAL_ZERO = new SkillStruct(
            SkillType.PERMANENT,
            "All kinds of heavy weapons become easy for you.\n" +
                    "Increase 10% damage of big weapons.\n",
            SkillIdentity.HEAVY_USUAL,
            "heavy-usual"
    );

    private final SkillStructArray HEAVY_USUAL_ARRAY = new SkillStructArray(
            HEAVY_USUAL_ZERO
    );

    private final SkillStruct HIT_FROM_GOD_ZERO = new SkillStruct(
            SkillType.ACTIVE,
            "The legendary skill that can seckill your opponent.\n" +
                    "Have 8% chance of reducing opponent’s health to 1.\n",
            SkillIdentity.HIT_FROM_GOD,
            "hit from god"
    );

    private final SkillStructArray HIT_FROM_GOD_ARRAY = new SkillStructArray(
            HIT_FROM_GOD_ZERO
    );

    private final SkillStruct DISARM_ZERO = new SkillStruct(
            SkillType.ACTIVE,
            "Others' weapon is the best weapon.\n" +
                    "Seize your opponent’s weapon. This skill is unescapable.\n",
            SkillIdentity.DISARM,
            "disarm"
    );

    private final SkillStructArray DISARM_ARRAY = new SkillStructArray(
            DISARM_ZERO
    );

    private final Map<SkillIdentity, SkillStructArray> SKILL_TABLE = new HashMap<>(
            Map.ofEntries(
                    Map.entry(SkillIdentity.BORN_AS_STRONG, BORN_AS_STRONG_ARRAY),
                    Map.entry(SkillIdentity.AGILE_BODY, AGILE_BODY_ARRAY),
                    Map.entry(SkillIdentity.A_STEP_AHEAD, A_STEP_AHEAD_ARRAY),
                    Map.entry(SkillIdentity.ROAR, ROAR_ARRAY),
                    Map.entry(SkillIdentity.APPARENT_DEATH, APPARENT_DEATH_ARRAY),
                    Map.entry(SkillIdentity.STRONG_PHYSIQUE, STRONG_PHYSIQUE_ARRAY),
                    Map.entry(SkillIdentity.BALANCED_GROWTH, BALANCED_GROWTH_ARRAY),
                    Map.entry(SkillIdentity.WEAPONS_HANDY, WEAPONS_HANDY_ARRAY),
                    Map.entry(SkillIdentity.BODY_COMBAT_SKILLED, BODY_COMBAT_SKILLED_ARRAY),
                    Map.entry(SkillIdentity.SIXTH_SENSE, SIXTH_SENSE_ARRAY),
                    Map.entry(SkillIdentity.BOLT_FROM_THE_BLUE, BOLT_FROM_THE_BLUE_ARRAY),
                    Map.entry(SkillIdentity.FAST_HANDS, FAST_HANDS_ARRAY),
                    Map.entry(SkillIdentity.HAKI_PROTECT, HAKI_PROTECT_ARRAY),
                    Map.entry(SkillIdentity.SEA_IS_UNFATHOMABLE, SEA_IS_UNFATHOMABLE_ARRAY),
                    Map.entry(SkillIdentity.STONE_SKIN, STONE_SKIN_ARRAY),
                    Map.entry(SkillIdentity.RIPPLESLESS_STEPS, RIPPLELESS_STEPS_ARRAY),
                    Map.entry(SkillIdentity.TORNADO, TORNADO_ARRAY),
                    Map.entry(SkillIdentity.HEAVY_USUAL, HEAVY_USUAL_ARRAY),
                    Map.entry(SkillIdentity.HIT_FROM_GOD, HIT_FROM_GOD_ARRAY),
                    Map.entry(SkillIdentity.DISARM, DISARM_ARRAY)
            )
    );

    public SkillStructArray get(SkillIdentity identity) {
        return SKILL_TABLE.get(identity);
    }

    public SkillStruct getWithStar(SkillIdentity identity, int star) {
        return SKILL_TABLE.get(identity).withStar(star);
    }
}
