package bigfight.model.weapon.struct;

public enum WeaponIdentity {
    TRIDENT, GAS_HAMMER, DEMON_SCYTHE, METEOR_BALL, JUDGE_PENCIL;

    // use static constant to avoid copying of enum array
    private static WeaponIdentity[] array = WeaponIdentity.values();

    public static WeaponIdentity[] getArray() {
        return array;
    }

    public static int getSize() {
        return array.length;
    }

    public static WeaponIdentity at(int index) {
        return array[index];
    }
}
