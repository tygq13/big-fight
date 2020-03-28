package bigfight.model.warrior.component;

public class Speed {
    private int base;
    private int total;
    public Speed() {
        base = 0;
        total = 0;
    }

    public Speed(int base) {
        this.base = base;
        total = this.base;
    }

    public void add(int increment) {
        total += increment;
    }

    public void addToBase(int increment) {
        base += increment;
        total += increment;
    }

    public int value() {
        return total;
    }

    public int getBase() {
        return base;
    }
}
