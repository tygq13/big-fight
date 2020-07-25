package bigfight.model.skill.struct;

public enum SkillIdentity {
    BORN_AS_STRONG, AGILE_BODY, A_STEP_AHEAD,
    ROAR,
    APPARENT_DEATH,
    STRONG_PHYSIQUE;

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
