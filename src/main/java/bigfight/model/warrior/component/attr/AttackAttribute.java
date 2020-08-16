package bigfight.model.warrior.component.attr;

public class AttackAttribute {
    private double hitRate;
    private double criticalChance;
    private int criticalDamage;
    private int extraDamage;
    private double extraPercentageDamage;
    private double penetrate;
    private double antiTenacity;

    public AttackAttribute(double hitRate, double criticalChance, int criticalDamage, int extraDamage, double extraPercentageDamage,
                           double penetrate, double antiTenacity) {
        this.hitRate = hitRate;
        this.criticalChance = criticalChance;
        this.criticalDamage = criticalDamage;
        this.extraDamage = extraDamage;
        this.extraPercentageDamage = extraPercentageDamage;
        this.penetrate = penetrate;
        this.antiTenacity = antiTenacity;
    }

    public double getHitRate() {
        return hitRate;
    }

    public double getCriticalChance() {
        return criticalChance;
    }

    public int getCriticalDamage() {
        return criticalDamage;
    }

    public int getExtraDamage() {
        return extraDamage;
    }

    public double getExtraPercentageDamage() {
        return extraPercentageDamage;
    }

    public double getPenetrate() {
        return penetrate;
    }

    public double getAntiTenacity() {
        return antiTenacity;
    }
}
