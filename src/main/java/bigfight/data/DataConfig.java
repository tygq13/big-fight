package bigfight.data;

import bigfight.model.weapon.struct.Damage;

public class DataConfig {
    private final int DEFAULT_INITIAL_ATTRIBUTE_TOTAL = 10;
    private int initialAttributeTotal;

    private final int DEFAULT_INITIAL_HEALTH = 65;
    private int initialHealth;

    public static int LEVEL_UP_ATTRIBUTE_ADDITION_NORMAL = 1;
    public static int LEVEL_UP_HEALTH_ADDITION = 2;

    public static final Damage DEFAULT_UNARMED_DAMAGE = new Damage(10, 20);
    public static final double THROW_WEAPON_CHANCE = 0.1;
    public static final double UNARMED_CHANCE = 0.1;
    public static final double CRITICAL_DAMAGE_BASE = 1.5;

    // initialize data set to default value
    public DataConfig() {
        initialAttributeTotal = DEFAULT_INITIAL_ATTRIBUTE_TOTAL;
        initialHealth = DEFAULT_INITIAL_HEALTH;
    }


    public int getInitialAttributeTotal() {
        return initialAttributeTotal;
    }

    public int getInitialHealth() {
        return initialHealth;
    }

}
