package bigfight.model.skill.struct;

public enum SkillIdentity {
    BORN_AS_STRONG, AGILE_BODY, A_STEP_AHEAD,
    ROAR,
    APPARENT_DEATH,
    STRONG_PHYSIQUE,
    BALANCED_GROWTH,
    WEAPONS_HANDY,
    BODY_COMBAT_SKILLED,
    SIXTH_SENSE,
    BOLT_FROM_THE_BLUE,
    FAST_HANDS,
    HAKI_PROTECT,
    SEA_REFLECT,
    STONE_SKIN,
    RIPPLESLESS_STEPS,
    TORNADO,
    HEAVY_USUAL,
    OPT_FOR_LIGHTNESS,
    ONE_PUNCH,
    DISARM,
    SHADOW_MOVE,
    MINE_WATER,
    GLUE,
    ANGELS_WINGS,
    FOSHAN_KICK,
    WEAPON_RAINSTORM,
    TICKLE,
    BLOOD_THIRSTY,
    TENDON_SHAPING_CLASSIC,
    DIM_HIT,
    DASH,
    SHAKE,
    WINDY_KICK,
    FOCUS_ON_HEART,
    BLOOD_SACRIFICE,
    LUCKY_OR_NOT,
    QI_GONG,
    SHOCK_WAVE,
    ASSASSINS_TECHNIQUE,
    VALIANT_FORCE,
    SWORD_ART,
    ACUPOINTER,
    TAI_CHI,
    HEAL,
    STINKY_FEET,
    GHOST_ON;


    // use static constant to avoid copying of enum array
    private static SkillIdentity[] array = SkillIdentity.values();
    public static int getSize() {
        return array.length;
    }
    public static SkillIdentity[] getArray() {
        return array;
    }
    public static SkillIdentity at(int index) {
        return array[index];
    }
}
