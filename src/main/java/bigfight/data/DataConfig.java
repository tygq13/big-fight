package bigfight.data;

import bigfight.model.weapon.struct.Damage;

public class DataConfig {
    private final int DEFAULT_INITIAL_ATTRIBUTE_TOTAL = 10;
    private int initialAttributeTotal;

    private final int DEFAULT_INITIAL_HEALTH = 65;
    private int initialHealth;

    public static final Damage DEFAULT_UNARMED_DAMAGE = new Damage(10, 20);
    public static final double COUNTER_ATTACK_CHANCE = 0.2;
    public static final double THROW_WEAPON_CHANCE = 0.1;

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
