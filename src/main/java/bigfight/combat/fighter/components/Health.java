package bigfight.combat.fighter.components;

public class Health {
    private int maxHealth;
    private int health;

    public Health(int health) {
        this.health = health;
        maxHealth = health;
    }

    public int value() {
        return health;
    }

    public void update(int value) {
        // feature: go below zero
        health = Math.min(value, maxHealth);
    }

    public int getMaxHealth() {
        return maxHealth;
    }
}
