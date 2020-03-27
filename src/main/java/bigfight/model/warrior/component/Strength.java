package bigfight.model.warrior.component;

public class Strength {
    private int base;
    private int total;
    public Strength() {
        base = 0;
        total = 0;
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
