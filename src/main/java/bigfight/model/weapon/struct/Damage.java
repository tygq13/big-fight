package bigfight.model.weapon.struct;

public class Damage {
    private int lower;
    private int higher;

    public Damage(int lowerLimit, int upperLimit) {
        lower = lowerLimit;
        higher = upperLimit;
    }

    public int lower() {
        return lower;
    }

    public int higher() {
        return higher;
    }
}
