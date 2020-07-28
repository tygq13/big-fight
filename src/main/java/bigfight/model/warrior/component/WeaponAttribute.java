package bigfight.model.warrior.component;

public class WeaponAttribute implements Cloneable{
    public double bigHitRate = 0;
    public double mediumHitRate = 0;
    public double smallHitRate = 0;
    public double throwHitRate = 0;

    public double bigEvasionRate = 0;
    public double mediumEvasionRate = 0;
    public double smallEvasionRate = 0;
    public double throwEvasionRate = 0;

    public double bigCriticalChance = 0;
    public double mediumCriticalChance = 0;
    public double smallCriticalChance = 0;
    public double throwCriticalChance = 0;

    public double bigAntiCriticalChance = 0;
    public double mediumAntiCriticalChance = 0;
    public double smallAntiCriticalChance = 0;
    public double throwAntiCriticalChance = 0;

    public double weaponCriticalDamage = 0;
    public double weaponAntiCriticalDamage = 0;

    public double bigExtraDamage = 0;
    public double mediumExtraDamage = 0;
    public double smallExtraDamage = 0;
    public double throwExtraDamage = 0;

    public double bigExtraPercentageDamage = 0;
    public double mediumExtraPercentageDamage = 0;
    public double smallExtraPercentageDamage = 0;
    public double throwExtraPercentageDamage = 0;

    public double bigPenetrate = 0;
    public double mediumPenetrate = 0;
    public double smallPenetrate = 0;
    public double throwPenetrate = 0;

    public double bigAntiPenetrate = 0;
    public double mediumAntiPenetrate = 0;
    public double smallAntiPenetrate = 0;
    public double throwAntiPenetrate = 0;

    public double bigAntiTenacity = 0;
    public double mediumAntiTenacity = 0;
    public double smallAntiTenacity = 0;
    public double throwAntiTenacity = 0;

    public double bigTenacity = 0;
    public double mediumTenacity = 0;
    public double smallTenacity = 0;
    public double throwTenacity = 0;

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println(e.getCause());
        }
        return null;
    }

    /*
    // use this instead of clone method to avoid handling of unnecessary exception
    public WeaponAttribute getCopy() {
        WeaponAttribute weaponAttribute = new WeaponAttribute();
        weaponAttribute.bigHitRate = bigHitRate;
        weaponAttribute.mediumHitRate = mediumHitRate;
        weaponAttribute.smallHitRate = smallHitRate;
        weaponAttribute.throwHitRate = throwHitRate;

        weaponAttribute.bigEvasionRate = bigEvasionRate;
        weaponAttribute.mediumEvasionRate = mediumEvasionRate;
        weaponAttribute.smallEvasionRate = smallEvasionRate;
        weaponAttribute.throwEvasionRate = throwEvasionRate;

        weaponAttribute.bigCriticalChance = bigCriticalChance;
        weaponAttribute.mediumCriticalChance = mediumCriticalChance;
        weaponAttribute.smallCriticalChance = smallCriticalChance;
        weaponAttribute.throwCriticalChance = throwCriticalChance;

        weaponAttribute.bigAntiCriticalChance = bigAntiCriticalChance;
        weaponAttribute.mediumAntiCriticalChance = mediumAntiCriticalChance;
        weaponAttribute.smallAntiCriticalChance = smallAntiCriticalChance;
        weaponAttribute.throwAntiCriticalChance = throwAntiCriticalChance;

        weaponAttribute.weaponCriticalDamage = weaponCriticalDamage;
        weaponAttribute.weaponAntiCriticalDamage = weaponAntiCriticalDamage;

        weaponAttribute.bigExtraDamage = bigExtraDamage;
        weaponAttribute.mediumExtraDamage = mediumExtraDamage;
        weaponAttribute.smallExtraDamage = smallExtraDamage;
        weaponAttribute.throwExtraDamage = throwExtraDamage;

        weaponAttribute.bigExtraPercentageDamage = bigExtraPercentageDamage;
        weaponAttribute.mediumExtraPercentageDamage = mediumExtraPercentageDamage;
        weaponAttribute.smallExtraPercentageDamage = smallExtraPercentageDamage;
        weaponAttribute.throwExtraPercentageDamage = throwExtraPercentageDamage;

        weaponAttribute.bigPenetrate = bigPenetrate;
        weaponAttribute.mediumPenetrate = mediumPenetrate;
        weaponAttribute.smallPenetrate = smallPenetrate;
        weaponAttribute.throwPenetrate = throwPenetrate;

        weaponAttribute.bigAntiPenetrate = bigAntiPenetrate;
        weaponAttribute.mediumAntiPenetrate = mediumAntiPenetrate;
        weaponAttribute.smallAntiPenetrate = smallAntiPenetrate;
        weaponAttribute.throwAntiPenetrate = throwAntiPenetrate;

        weaponAttribute.bigAntiTenacity = bigAntiTenacity;
        weaponAttribute.mediumAntiTenacity = mediumAntiTenacity;
        weaponAttribute.smallAntiTenacity = smallAntiTenacity;
        weaponAttribute.throwAntiTenacity = throwAntiTenacity;

        weaponAttribute.bigTenacity = bigTenacity;
        weaponAttribute.mediumTenacity = mediumTenacity;
        weaponAttribute.smallTenacity = smallTenacity;
        weaponAttribute.throwTenacity = throwTenacity;
        return weaponAttribute;
    }

     */
}
