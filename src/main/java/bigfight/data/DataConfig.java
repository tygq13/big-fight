package bigfight.data;

public class DataConfig {
    private final int DEFAULT_INITIAL_ATTRIBUTE_TOTAL = 10;
    private int initialAttributeTotal;

    private final int DEFAULT_INITIAL_HEALTH = 65;
    private int initialHealth;

    // initialize data set to default value
    public DataConfig() {
        initialAttributeTotal = DEFAULT_INITIAL_ATTRIBUTE_TOTAL;
    }


    public int getInitialAttributeTotal() {
        return initialAttributeTotal;
    }

    public int getInitialHealth() {
        return initialHealth;
    }

}
