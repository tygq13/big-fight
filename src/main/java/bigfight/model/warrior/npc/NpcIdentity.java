package bigfight.model.warrior.npc;

public enum NpcIdentity {
    NOOB(0);

    private int value;

    NpcIdentity(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    private static NpcIdentity[] array = NpcIdentity.values();
    public static NpcIdentity[] getArray() {
        return array;
    }
    public static int getSize() {
        return array.length;
    }
}
