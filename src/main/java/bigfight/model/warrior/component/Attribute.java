package bigfight.model.warrior.component;

public class Attribute {
    private BasicAttribute speed;
    private BasicAttribute agility;
    private BasicAttribute strength;
    private BasicAttribute health;
    private AdvancedAttribute advancedAttribute;

    public Attribute() {
        strength = new BasicAttribute();
        agility = new BasicAttribute();
        speed = new BasicAttribute();
        health = new BasicAttribute();
        advancedAttribute = new AdvancedAttribute();
    }

    public Attribute(BasicAttribute strength, BasicAttribute agility, BasicAttribute speed, BasicAttribute health) {
        this.strength = strength;
        this.agility = agility;
        this.speed = speed;
        this.health = health;
        advancedAttribute = new AdvancedAttribute();
    }

    public int getSpeed() {
        return speed.value();
    }

    public BasicAttribute getSpeedObj() {
        return speed;
    }

    public int getStrength() {
        return strength.value();
    }

    public BasicAttribute getStrengthObj() {
        return strength;
    }

    public int getAgility() {
        return agility.value();
    }

    public BasicAttribute getAgilityObj() {
        return agility;
    }

    public int getHealth() {
        return health.value();
    }

    public BasicAttribute getHealthObj() {
        return health;
    }

    public AdvancedAttribute getAdvancedAttribute() {
        return advancedAttribute;
    }
}
