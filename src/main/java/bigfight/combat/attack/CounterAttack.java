package bigfight.combat.attack;

import bigfight.combat.fighter.FighterStatus;
import bigfight.combat.util.CombatAlgo;
import bigfight.combat.util.CombatRandom;
import bigfight.data.DataConfig;
import bigfight.model.skill.skills.ApparentDeath;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.weapon.Weapon;
import bigfight.ui.Uiable;

import java.util.Random;

class CounterAttack {
    private FighterStatus defender;
    private FighterStatus attacker;
    private boolean isEscaped;
    private CombatRandom random;
    private Uiable ui;

    CounterAttack(FighterStatus defender, FighterStatus attacker, boolean isEscaped, CombatRandom random, Uiable ui) {
        this.defender = defender;
        this.attacker = attacker;
        this.isEscaped = isEscaped;
        this.random = random;
        this.ui = ui;
    }

    boolean specialCounter() {
        if (defender.getHealth() <= 0 && defender.hasSkill(SkillIdentity.APPARENT_DEATH)) {
            ApparentDeath apparentDeath = (ApparentDeath) defender.getSkill(SkillIdentity.APPARENT_DEATH);
            int lastHealth = apparentDeath.getLastHealth();
            defender.updateHealth(lastHealth);
            defender.removeSkill(SkillIdentity.APPARENT_DEATH);
            ui.printSkillApparentDeath(defender.getName());
            return true;
        }
        return false;
    }

    int counterAttack() {
        if (specialCounter()) {
            return 0;
        }
        // Could change it to the specific attack type to have recursive counter-attack, but the counter-attack mechanism
        // for now is still unclear.
        if (isCounter()) {
            Weapon weapon = defender.getHoldingWeapon();
            ui.printCounterAttackWeapon(defender.getName(), weapon == null ? "his fist" : weapon.getName());

            if (counterEscaped()) {
                // the counter attack is escaped
                ui.printCounterAttackDodge(attacker.getName());
                return 0;
            } else {
                int damage = calculateDamage();
                ui.printCounterAttackInjury(attacker.getName(), damage, attacker.getHealth());
                return damage;
            }
        }
        return 0;
    }

    private boolean counterEscaped() {
        double escape = defender.getFocus() - attacker.getEscape();
        escape += CombatAlgo.escapeByAgility(defender.getAgility(), attacker.getAgility());
        return random.getCounterEscapeRandom() < escape;
    }

    private int calculateDamage() {
        double strengthMultiply = CombatAlgo.multiplyByStrength(defender.getStrength(), attacker.getStrength());
        Weapon weapon = defender.getHoldingWeapon();
        if (weapon == null) {
            int unarmedDamage = random.getWeaponDamageRandom(defender.getUnarmedDamage().lower(), defender.getUnarmedDamage().higher());
            return (int) (unarmedDamage * (1 + strengthMultiply));
        } else {
            int weaponDamage = random.getWeaponDamageRandom(weapon.getDamage().lower(), weapon.getDamage().higher());
            double extraDamageMultiply = 0;
            switch (weapon.getType()) {
                case BIG:
                    extraDamageMultiply = defender.getAdvancedAttribute().bigExtraPercentageDamage;
                    break;
                case MEDIUM:
                    extraDamageMultiply = defender.getAdvancedAttribute().mediumExtraPercentageDamage;
                    break;
                case SMALL:
                    extraDamageMultiply = defender.getAdvancedAttribute().smallExtraPercentageDamage;
                    break;
                case THROW:
                    extraDamageMultiply = defender.getAdvancedAttribute().throwExtraPercentageDamage;

            }
            double totalMultiply = strengthMultiply + extraDamageMultiply;
            return (int) (weaponDamage * (1 + totalMultiply));
        }
    }

    private boolean isCounter() {
        if (!isEscaped) {
            return random.getCounterAttackRandom() < defender.getAdvancedAttribute().counterAttackChance;
        }
        return false;
    }
}
