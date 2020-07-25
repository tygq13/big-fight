package bigfight.model.warrior.component;

public class Agility {
    private int base;
    private int total;
    public Agility() {
        base = 0;
        total = 0;
    }

    public Agility(int base) {
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
