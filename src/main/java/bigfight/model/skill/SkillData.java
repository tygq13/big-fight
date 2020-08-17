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
            "roar",
            "sums up his courage and seems unaffected by the attack. ",
            "roars in anger, as if the king of beasts to scare the enemy away. "
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
                    "Only 20% chance of normal chance in using this skill. Attack the opponent twice.\n" +
                    "Immediately make the next attack.",
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

    private final SkillStruct SEA_REFLECT_ZERO = new SkillStruct(
            SkillType.SPECIAL,
            "Your strong internal organs make you unafraid of any attack.\n" +
                    "Reflect 100% damage. \n" +
                    "You can only use this skill once.\n" +
                    "After level 60, you have high chance of getting this skill at every 5 levels.\n",
            SkillIdentity.SEA_REFLECT,
            "sea reflect"
    );

    private final SkillStructArray SEA_REFLECT_ARRAY = new SkillStructArray(
            SEA_REFLECT_ZERO
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
                    "Your evasion chance increases by 7%.\n" +
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
                    "Your big weapon damage increases by 10%.\n",
            SkillIdentity.HEAVY_USUAL,
            "heavy-usual"
    );

    private final SkillStructArray HEAVY_USUAL_ARRAY = new SkillStructArray(
            HEAVY_USUAL_ZERO
    );

    private final SkillStruct OPT_FOR_LIGHTNESS_ZERO = new SkillStruct(
            SkillType.PERMANENT,
            "You of featherweight have your own secret skill.\n" +
                    "Your small and medium weapon damage increases by 15%. Your big weapon evasion rate increases by 20%.\n" +
                    "Skill is only acquired after level 30.\n",
            SkillIdentity.OPT_FOR_LIGHTNESS,
            "opt for lightness"
    );

    private final SkillStructArray OPT_FOR_LIGHTNESS_ARRAY = new SkillStructArray(
            OPT_FOR_LIGHTNESS_ZERO
    );

    private final SkillStruct ONE_PUNCH_ZERO = new SkillStruct(
            SkillType.ACTIVE,
            "The legendary skill that can seckill your opponent.\n" +
                    "only 20% of normal chance in using this skill.\n" +
                    "Reduce opponent’s health to 1 if hit.\n",
            SkillIdentity.ONE_PUNCH,
            "one punch"
    );

    private final SkillStructArray ONE_PUNCH_ARRAY = new SkillStructArray(
            ONE_PUNCH_ZERO
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

    private final SkillStruct SHADOW_MOVE_ZERO = new SkillStruct(
            SkillType.SPECIAL,
            "Fast as lighting. Swift as thunderbolt.\n" +
                    "only 20% of normal chance in showing shadow move.\n" +
                    "Under shadow move, your speed increases by 50%, all damages increase by 30%. Last for 3 rounds.\n" +
                    "Immediately make the next attack.",
            SkillIdentity.SHADOW_MOVE,
            "shadow move"
    );

    private final SkillStructArray SHADOW_MOVE_ARRAY = new SkillStructArray(
            SHADOW_MOVE_ZERO
    );

    private final SkillStruct MINE_WATER_ZERO = new SkillStruct(
            SkillType.SPECIAL,
            "Big-fight’s special mine water. Everyone recommends it!\n" +
                    "Regenerate 25% health (at least 25 health).\n" +
                    "only 10% of normal chance in using this skill" +
                    "Immediately make the next attack.\n",
            SkillIdentity.MINE_WATER,
            "mine water"
    );

    private final SkillStructArray MINE_WATER_ARRAY = new SkillStructArray(
            MINE_WATER_ZERO
    );

    private final SkillStruct GLUE_ZERO = new SkillStruct(
            SkillType.ACTIVE,
            "Very sticky glue.\n" +
                    "10% chance of gluing the opponent.\n" +
                    "The opponent can only use remote attack. \n",
            SkillIdentity.GLUE,
            "glue"
    );

    private final SkillStructArray GLUE_ARRAY = new SkillStructArray(
            GLUE_ZERO
    );

    private final SkillStruct ANGELS_WINGS_ZERO = new SkillStruct(
            SkillType.ACTIVE,
            "True warriors fight in the sky.\n" +
                    "Cause damage of 15 + agility*100%. Unable to be counter-attacked.\n",
            SkillIdentity.ANGELS_WINGS,
            "angel's wings"
    );

    private final SkillStructArray ANGELS_WINGS_ARRAY = new SkillStructArray(
            ANGELS_WINGS_ZERO
    );

    private final SkillStruct FOSHAN_KICK_ZERO = new SkillStruct(
            SkillType.ACTIVE,
            "This is a popular shadowless kick technique in Foshan. It was created by the martial arts master, Fei-hong.\n" +
                    "Cause damage of 30 + strength*0.5.\n",
            SkillIdentity.FOSHAN_KICK,
            "Foshan kick"
    );

    private final SkillStructArray FOSHAN_KICK_ARRAY = new SkillStructArray(
            FOSHAN_KICK_ZERO
    );

    private final SkillStruct WEAPON_RAINSTORM_ZERO = new SkillStruct(
            SkillType.ACTIVE,
            "No one can guard against the rainstorm-like attack.\n" +
                    "Throw at most 3 weapons to your opponent at once.\n",
            SkillIdentity.WEAPON_RAINSTORM,
            "weapon rainstorm"
    );

    private final SkillStructArray WEAPON_RAINSTORM_ARRAY = new SkillStructArray(
            WEAPON_RAINSTORM_ZERO
    );

    private final SkillStruct TICKLE_ZERO = new SkillStruct(
            SkillType.ACTIVE,
            "This is your weakness!\n" +
                    "Cause damage of 5 + agility*0.2 at each round. Last for 6 rounds. \n" +
                    "You can only use once.\n",
            SkillIdentity.TICKLE,
            "tickle"
    );

    private final SkillStructArray TICKLE_ARRAY = new SkillStructArray(
            TICKLE_ZERO
    );

    private final SkillStruct BLOOD_THIRSTY_ZERO = new SkillStruct(
            SkillType.SPECIAL,
            "A secret technique of the Middle Ages.\n" +
                    "25% chance of increasing 1/3 of life steal.\n" +
                    "Skill is only acquired after level 30. After level 60, you have high chance of getting this skill at every 5 levels.\n",
            SkillIdentity.BLOOD_THIRSTY,
            "blood thirsty"
    );

    private final SkillStructArray BLOOD_THIRSTY_ARRAY = new SkillStructArray(
            BLOOD_THIRSTY_ZERO
    );

    private final SkillStruct TENDON_SHAPING_CLASSIC_ZERO = new SkillStruct(
            SkillType.PERMANENT,
            "A classic from Shaolin.\n" +
                    "Increases the damage of active skill by 20%.\n" +
                    "This Skill is only acquired after level 40.\n",
            SkillIdentity.TENDON_SHAPING_CLASSIC,
            "tendon shaping classic"
    );

    private final SkillStructArray TENDON_SHAPING_CLASSIC_ARRAY = new SkillStructArray(
            TENDON_SHAPING_CLASSIC_ZERO
    );

    private final SkillStruct DIM_HIT_ZERO = new SkillStruct(
            SkillType.PERMANENT,
            "It all comes from a dim mood. Hero Yang Guo created this skill in his miss for his wife.\n" +
                    "Your chance of skill critical attack increases by 15%.\n" +
                    "This Skill is only acquired after level 40.\n",
            SkillIdentity.DIM_HIT,
            "dim hit"
    );

    private final SkillStructArray DIM_HIT_ARRAY = new SkillStructArray(
            DIM_HIT_ZERO
    );

    private final SkillStruct DASH_ZERO = new SkillStruct(
            SkillType.ACTIVE,
            "The most valiant attack by a warrior!\n" +
                    "Ignore opponent’s next round if hit.\n" +
                    "Cause damage of 15 + speed*50%.\n",
            SkillIdentity.DASH,
            "dash"
    );

    private final SkillStructArray DASH_ARRAY = new SkillStructArray(
            DASH_ZERO
    );

    private final SkillStruct SHAKE_ZERO = new SkillStruct(
            SkillType.ACTIVE,
            "Causes 25 damage and randomly dispose one of the opponent’s weapon. Unevadable.\n" +
                    "This Skill is only acquired after level 20.\n",
            SkillIdentity.SHAKE,
            "shake"
    );

    private final SkillStructArray SHAKE_ARRAY = new SkillStructArray(
            SHAKE_ZERO
    );

    private final SkillStruct WINDY_KICK_ZERO = new SkillStruct(
            SkillType.ACTIVE,
            "Ordinary qinggong. \n" +
                    "Increase speed at this round by 10.\n" +
                    "Cause damage of speed*50%.\n",
            SkillIdentity.WINDY_KICK,
            "windy kick"
    );

    private final SkillStructArray WINDY_KICK_ARRAY = new SkillStructArray(
            WINDY_KICK_ZERO
    );

    private final SkillStruct FOCUS_ON_HEART_ZERO = new SkillStruct(
            SkillType.PERMANENT,
            "A method that helps you concentrate. \n" +
                    "Increase hit rate of all active skill by 3%.\n" +
                    "High chance of enhancing the skill.\n",
            SkillIdentity.FOCUS_ON_HEART,
            "focus on heart"
    );

    private final SkillStructArray FOCUS_ON_HEART_ARRAY = new SkillStructArray(
            FOCUS_ON_HEART_ZERO
    );

    private final SkillStruct BLOOD_SACRIFICE_ZERO = new SkillStruct(
            SkillType.SPECIAL,
            "An evil technique from the demon king.\n" +
                    "20% chance of increasing 10% of life steal.\n" +
                    "This skill is only acquired after level 30.\n",
            SkillIdentity.BLOOD_SACRIFICE,
            "blood sacrifice"
    );

    private final SkillStructArray BLOOD_SACRIFICE_ARRAY = new SkillStructArray(
            BLOOD_SACRIFICE_ZERO
    );

    private final SkillStruct LUCKY_OR_NOT_ZERO = new SkillStruct(
            SkillType.ACTIVE,
            "Your luck or my luck?\n" +
                    "70% chance increases opponent’s health by 25%. \n" +
                    "This skill is only acquired after level 30.\n",
            SkillIdentity.LUCKY_OR_NOT,
            "luck or not"
    );

    private final SkillStructArray LUCK_OR_NOT_ARRAY = new SkillStructArray(
            LUCKY_OR_NOT_ZERO
    );

    private final SkillStruct QI_GOING_ZERO = new SkillStruct(
            SkillType.PERMANENT,
            "Tighten your muscle to endure the attack.\n" +
                    "Your afflicted skill attack damage reduces by 5%.\n" +
                    "This skill is only acquired after level 40.\n" +
                    "After level 60, you have high chance of getting this skill at every 5 levels.\n",
            SkillIdentity.QI_GONG,
            "qi gong"
    );

    private final SkillStructArray QI_GONG_ARRAY = new SkillStructArray(
            QI_GOING_ZERO
    );

    private final SkillStruct SHOCK_WAVE_ZERO = new SkillStruct(
            SkillType.ACTIVE,
                "Use all your qi to attack your opponent.\n" +
                        "Cause at least 40 damage.\n" +
                        "You accumulate qi at every round, increasing the damage by 1, hit rate by 2%.\n" +
                        "You can only uses this skill once in a fight.\n",
            SkillIdentity.SHOCK_WAVE,
            "shock wave"
    );

    private final SkillStructArray SHOCK_WAVE_ARRAY = new SkillStructArray(
            SHOCK_WAVE_ZERO
    );

    private final SkillStruct ASSASSINS_TECHNIQUE_ZERO = new SkillStruct(
            SkillType.PERMANENT,
            "Concealed weapons are necessary for a good assassin.\n" +
                    "Your hit rate for throw and small weapons increases by 1%.\n" +
                    "This skill is only acquired after level 50.\n",
            SkillIdentity.ASSASSINS_TECHNIQUE,
            "assassin's technique"
    );

    private final SkillStructArray ASSASSINS_TECHNIQUE_ARRAY = new SkillStructArray(
            ASSASSINS_TECHNIQUE_ZERO
    );

    private final SkillStruct VALIANT_FORCE_ZERO = new SkillStruct(
            SkillType.PERMANENT,
            "Big weapons are necessary for a valiant soldier.\n" +
                    "Your hit rate for big weapons increases by 1%.\n" +
                    "This skill is only acquired after level 60.\n",
            SkillIdentity.VALIANT_FORCE,
            "valiant force"
    );

    private final SkillStructArray VALIANT_FORCE_ARRAY = new SkillStructArray(
            VALIANT_FORCE_ZERO
    );

    private final SkillStruct SWORD_ART_ZERO = new SkillStruct(
            SkillType.PERMANENT,
            "Using medium weapons is an art to a good swordsman.\n" +
                    "Your hit rate for medium weapons increases by 1%.\n" +
                    "This skill is only acquired after level 70.\n",
            SkillIdentity.SWORD_ART,
            "sword art"
    );

    private final SkillStructArray SWORD_ART_ARRAY = new SkillStructArray(
            SWORD_ART_ZERO
    );

    private final SkillStruct ACUPOINTER_ZERO = new SkillStruct(
            SkillType.ACTIVE,
            "The crucial acupoint controls a man’s ability to use skill.\n" +
                    "Causes damage of 30 + lv*0.3 and makes the opponent unable to use skills for 2 rounds. The hit rate increases 15% when using this skill.\n" +
                    "This skill is only acquired after level 50.\n",
            SkillIdentity.ACUPOINTER,
            "acupointer"
    );

    private final SkillStructArray ACUPOINTER_ARRAY = new SkillStructArray(
            ACUPOINTER_ZERO
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
                    Map.entry(SkillIdentity.SEA_REFLECT, SEA_REFLECT_ARRAY),
                    Map.entry(SkillIdentity.STONE_SKIN, STONE_SKIN_ARRAY),
                    Map.entry(SkillIdentity.RIPPLESLESS_STEPS, RIPPLELESS_STEPS_ARRAY),
                    Map.entry(SkillIdentity.TORNADO, TORNADO_ARRAY),
                    Map.entry(SkillIdentity.HEAVY_USUAL, HEAVY_USUAL_ARRAY),
                    Map.entry(SkillIdentity.OPT_FOR_LIGHTNESS, OPT_FOR_LIGHTNESS_ARRAY),
                    Map.entry(SkillIdentity.ONE_PUNCH, ONE_PUNCH_ARRAY),
                    Map.entry(SkillIdentity.DISARM, DISARM_ARRAY),
                    Map.entry(SkillIdentity.SHADOW_MOVE, SHADOW_MOVE_ARRAY),
                    Map.entry(SkillIdentity.MINE_WATER, MINE_WATER_ARRAY),
                    Map.entry(SkillIdentity.GLUE, GLUE_ARRAY),
                    Map.entry(SkillIdentity.ANGELS_WINGS, ANGELS_WINGS_ARRAY),
                    Map.entry(SkillIdentity.FOSHAN_KICK, FOSHAN_KICK_ARRAY),
                    Map.entry(SkillIdentity.WEAPON_RAINSTORM, WEAPON_RAINSTORM_ARRAY),
                    Map.entry(SkillIdentity.TICKLE, TICKLE_ARRAY),
                    Map.entry(SkillIdentity.BLOOD_THIRSTY, BLOOD_THIRSTY_ARRAY),
                    Map.entry(SkillIdentity.TENDON_SHAPING_CLASSIC, TENDON_SHAPING_CLASSIC_ARRAY),
                    Map.entry(SkillIdentity.DIM_HIT, DIM_HIT_ARRAY),
                    Map.entry(SkillIdentity.DASH, DASH_ARRAY),
                    Map.entry(SkillIdentity.SHAKE, SHAKE_ARRAY),
                    Map.entry(SkillIdentity.WINDY_KICK, WINDY_KICK_ARRAY),
                    Map.entry(SkillIdentity.FOCUS_ON_HEART, FOCUS_ON_HEART_ARRAY),
                    Map.entry(SkillIdentity.BLOOD_SACRIFICE, BLOOD_SACRIFICE_ARRAY),
                    Map.entry(SkillIdentity.LUCKY_OR_NOT, LUCK_OR_NOT_ARRAY),
                    Map.entry(SkillIdentity.QI_GONG, QI_GONG_ARRAY),
                    Map.entry(SkillIdentity.SHOCK_WAVE, SHOCK_WAVE_ARRAY),
                    Map.entry(SkillIdentity.ASSASSINS_TECHNIQUE, ASSASSINS_TECHNIQUE_ARRAY),
                    Map.entry(SkillIdentity.VALIANT_FORCE, VALIANT_FORCE_ARRAY),
                    Map.entry(SkillIdentity.SWORD_ART, SWORD_ART_ARRAY),
                    Map.entry(SkillIdentity.ACUPOINTER, ACUPOINTER_ARRAY)
            )
    );

    public SkillStructArray get(SkillIdentity identity) {
        return SKILL_TABLE.get(identity);
    }

    public SkillStruct getWithStar(SkillIdentity identity, int star) {
        return SKILL_TABLE.get(identity).withStar(star);
    }
}
