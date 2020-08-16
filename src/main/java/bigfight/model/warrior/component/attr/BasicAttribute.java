package bigfight.model.warrior.component.attr;

public class BasicAttribute implements Cloneable{
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

    public void assignBase(int newBase) {
        total = total - base + newBase;
        base = newBase;
    }

    public int value() {
        return total;
    }

    public int getBase() {
        return base;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println(e.getCause());
        }
        return null;
    }
}
