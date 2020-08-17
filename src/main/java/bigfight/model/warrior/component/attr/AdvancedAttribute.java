package bigfight.model.warrior.component.attr;

public class AdvancedAttribute implements Cloneable{
    public double bigHitRate = 0;
    public double mediumHitRate = 0;
    public double smallHitRate = 0;
    public double throwHitRate = 0;
    public double skillHitRate = 0;
    public double unarmedHitRate = 0;

    public double bigEvasionRate = 0;
    public double mediumEvasionRate = 0;
    public double smallEvasionRate = 0;
    public double throwEvasionRate = 0;
    public double skillEvasionRate = 0;
    public double unarmedEvasionRate = 0;

    public double bigCriticalChance = 0;
    public double mediumCriticalChance = 0;
    public double smallCriticalChance = 0;
    public double throwCriticalChance = 0;
    public double skillCriticalChance = 0;
    public double unarmedCriticalChance = 0;

    public double antiBigCriticalChance = 0;
    public double antiMediumCriticalChance = 0;
    public double antiSmallCriticalChance = 0;
    public double antiThrowCriticalChance = 0;
    public double antiSkillCriticalChance = 0;
    public double antiUnarmedCriticalChance = 0;

    public double weaponCriticalDamagePercentage = 0;
    public double skillCriticalDamagePercentage = 0;
    public double unarmedCriticalDamagePercentage = 0;

    public double antiWeaponCriticalDamagePercentage = 0;
    public double antiSkillCriticalDamagePercentage = 0;
    public double antiUnarmedCriticalDamagePercentage = 0;

    public int bigExtraDamage = 0;
    public int mediumExtraDamage = 0;
    public int smallExtraDamage = 0;
    public int throwExtraDamage = 0;
    public int skillExtraDamage = 0;
    public int unarmedExtraDamage = 0;

    public int antiBigExtraDamage = 0;
    public int antiMediumExtraDamage = 0;
    public int antiSmallExtraDamage = 0;
    public int antiThrowExtraDamage = 0;
    public int antiSkillExtraDamage = 0;
    public int antiUnarmedExtraDamage = 0;

    public double bigExtraPercentageDamage = 0;
    public double mediumExtraPercentageDamage = 0;
    public double smallExtraPercentageDamage = 0;
    public double throwExtraPercentageDamage = 0;
    public double skillExtraPercentageDamage = 0;
    public double unarmedExtraPercentageDamage = 0;

    public double antiBigExtraPercentageDamage = 0;
    public double antiMediumExtraPercentageDamage = 0;
    public double antiSmallExtraPercentageDamage = 0;
    public double antiThrowExtraPercentageDamage = 0;
    public double antiSkillExtraPercentageDamage = 0;
    public double antiUnarmedExtraPercentageDamage = 0;

    public double bigPenetrate = 0;
    public double mediumPenetrate = 0;
    public double smallPenetrate = 0;
    public double throwPenetrate = 0;
    public double skillPenetrate = 0;
    public double unarmedPenetrate = 0;

    public double antiBigPenetrate = 0;
    public double antiMediumPenetrate = 0;
    public double antiSmallPenetrate = 0;
    public double antiThrowPenetrate = 0;
    public double antiSkillPenetrate = 0;
    public double antiUnarmedPenetrate = 0;


    public double antiBigTenacity = 0;
    public double antiMediumTenacity = 0;
    public double antiSmallTenacity = 0;
    public double antiThrowTenacity = 0;
    public double antiSkillTenacity = 0;

    public double bigTenacity = 0;
    public double mediumTenacity = 0;
    public double smallTenacity = 0;
    public double throwTenacity = 0;
    public double skillTenacity = 0;

    public double counterAttackChance = 0;

    public double doubleHitChance = 0;

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println(e.getCause());
        }
        return null;
    }

    public AttackAttribute bigAttackAttribute() {
        return new AttackAttribute(bigHitRate, bigCriticalChance, weaponCriticalDamagePercentage, bigExtraDamage,
                bigExtraPercentageDamage, bigPenetrate, antiBigTenacity);
    }

    public AttackAttribute smallAttackAttribute() {
        return new AttackAttribute(smallHitRate, smallCriticalChance, weaponCriticalDamagePercentage, smallExtraDamage,
                smallExtraPercentageDamage, smallPenetrate, antiSmallTenacity);
    }

    public AttackAttribute mediumAttackAttribute() {
        return new AttackAttribute(mediumHitRate, mediumCriticalChance, weaponCriticalDamagePercentage, mediumExtraDamage,
                mediumExtraPercentageDamage, mediumPenetrate, antiMediumTenacity);
    }

    public AttackAttribute throwAttackAttribute() {
        return new AttackAttribute(throwHitRate, throwCriticalChance, weaponCriticalDamagePercentage, throwExtraDamage,
                throwExtraPercentageDamage, throwPenetrate, antiThrowTenacity);
    }

    public AttackAttribute unarmedAttackAttribute() {
        return new AttackAttribute(unarmedHitRate, unarmedCriticalChance, unarmedCriticalDamagePercentage, unarmedExtraDamage,
                unarmedExtraPercentageDamage, unarmedPenetrate, 0);
    }

    public AttackAttribute skillAttackAttribute() {
        return new AttackAttribute(skillHitRate, skillCriticalChance, skillCriticalDamagePercentage, skillExtraDamage,
                skillExtraPercentageDamage, skillPenetrate, antiSkillTenacity);
    }

    public DefenceAttribute bigDefenceAttribute() {
        return new DefenceAttribute(bigEvasionRate, antiBigCriticalChance, weaponCriticalDamagePercentage, antiBigExtraDamage,
                antiBigExtraPercentageDamage, antiBigPenetrate, antiBigTenacity);
    }

    public DefenceAttribute mediumDefenceAttribute() {
        return new DefenceAttribute(mediumEvasionRate, antiMediumCriticalChance, weaponCriticalDamagePercentage, antiMediumExtraDamage,
                antiMediumExtraPercentageDamage, antiMediumPenetrate, mediumTenacity);
    }

    public DefenceAttribute smallDefenceAttribute() {
        return new DefenceAttribute(smallEvasionRate, antiSmallCriticalChance, weaponCriticalDamagePercentage, antiSmallExtraDamage,
                antiSmallExtraPercentageDamage, antiSmallPenetrate, smallTenacity);
    }

    public DefenceAttribute throwDefenceAttribute() {
        return new DefenceAttribute(throwEvasionRate, antiThrowCriticalChance, weaponCriticalDamagePercentage, antiThrowExtraDamage,
                antiThrowExtraPercentageDamage, antiThrowPenetrate, throwTenacity);
    }

    public DefenceAttribute unarmedDefenceAttribute() {
        return new DefenceAttribute(unarmedEvasionRate, antiUnarmedCriticalChance, antiUnarmedCriticalDamagePercentage, antiUnarmedExtraDamage,
                antiUnarmedExtraPercentageDamage, antiUnarmedPenetrate, 0);
    }

    public DefenceAttribute skillDefenceAttribute() {
        return new DefenceAttribute(skillEvasionRate, antiSkillCriticalChance, antiSkillCriticalDamagePercentage, antiSkillExtraDamage,
                antiSkillExtraPercentageDamage, antiSkillPenetrate, skillTenacity);
    }
}
