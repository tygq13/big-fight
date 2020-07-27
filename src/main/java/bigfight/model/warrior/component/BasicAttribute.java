package bigfight.model.warrior.component;

public class BasicAttribute {
    private int base;
    private int total;
    public BasicAttribute() {
        base = 0;
        total = 0;
    }

    public BasicAttribute(int base) {
        this.base = base;
        total = this.base;
    }

    public void addToAddition(int increment) {
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
