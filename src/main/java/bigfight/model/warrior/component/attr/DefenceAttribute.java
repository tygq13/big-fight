package bigfight.model.warrior.component.attr;

public class DefenceAttribute {
    private double evasionRate;
    private double antiCriticalChance;
    private double antiCriticalDamage;
    private int antiExtraDamage;
    private double antiExtraPercentageDamage;
    private double antiPenetrate;
    private double Tenacity;

    public DefenceAttribute(double evasionRate, double antiCriticalChance, double antiCriticalDamage, int antiExtraDamage, double antiExtraPercentageDamage,
                           double antiPenetrate, double Tenacity) {
        this.evasionRate = evasionRate;
        this.antiCriticalChance = antiCriticalChance;
        this.antiCriticalDamage = antiCriticalDamage;
        this.antiExtraDamage = antiExtraDamage;
        this.antiExtraPercentageDamage = antiExtraPercentageDamage;
        this.antiPenetrate = antiPenetrate;
        this.Tenacity = Tenacity;
    }

    public int getAntiExtraDamage() {
        return antiExtraDamage;
    }

    public double getEvasionRate() {
        return evasionRate;
    }

    public double getAntiCriticalChance() {
        return antiCriticalChance;
    }

    public double getAntiCriticalDamage() {
        return antiCriticalDamage;
    }

    public double getAntiExtraPercentageDamage() {
        return antiExtraPercentageDamage;
    }

    public double getAntiPenetrate() {
        return antiPenetrate;
    }

    public double getTenacity() {
        return Tenacity;
    }
}
