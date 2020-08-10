package bigfight.combat.attack;

import bigfight.combat.fighter.Fighter;
import bigfight.combat.util.CombatAlgo;
import bigfight.combat.util.CombatRandom;
import bigfight.model.warrior.component.Empowerment;
import bigfight.model.weapon.Weapon;
import bigfight.model.weapon.struct.WeaponIdentity;
import bigfight.model.weapon.weapons.GasHammer;
import bigfight.ui.Uiable;

public class ThrowOutAttack implements Attackable {
    private Fighter attacker;
    private Fighter defender;
    private Weapon weapon;
    private CombatRandom random;
    private Uiable ui;

    public ThrowOutAttack(Fighter attacker, Fighter defender, Weapon weapon, CombatRandom random, Uiable ui) {
        this.attacker = attacker;
        this.defender = defender;
        this.weapon = weapon;
        this.random = random;
        this.ui = ui;
    }

    @Override
    public void attack() {
        ui.printThrowOutAttack(attacker.getName(), weapon.getName());
        // special case
        // todo: refactor this
        if (weapon.getIdentity() == WeaponIdentity.TRIDENT) {
            attacker.getFighterFlag().ignored += 1;
        }

        if (!escaped()) {
            defender.getFighterFlag().ignored += ignoreOpponent();
            int damage = calculateDamage();
            defender.updateHealth(defender.getHealth() - damage);
            ui.printInjury(defender.getName(), damage, defender.getHealth());
            CounterAttack counterAttack = new CounterAttack(defender, attacker, random, ui);
            if (!(counterAttack.specialCounter(damage))) {
                counterAttack.counterAttack();
            }
        }
        ui.printDodge(defender.getName());

        // loss the weapon after throwing out
        Weapon unarmed = null;
        attacker.changeWeapon(new Empowerment(unarmed));
    }

    private int ignoreOpponent() {
        if (weapon.getIdentity() == null) {
            return 0;
        }
        switch (weapon.getIdentity()) {
            case GAS_HAMMER:
                GasHammer gasHammer = (GasHammer) weapon.getModel();
                return random.getIgnoreRandom() < gasHammer.getIgnoreChance() ? 1 : 0;
        }
        return 0;
    }

    private boolean escaped() {
        if (weapon.getIdentity() == WeaponIdentity.DEMON_SCYTHE || weapon.getIdentity() == WeaponIdentity.JUDGE_PENCIL) {
            return false;
        }
        double escape = 0;
        switch (weapon.getType()) {
            case BIG:
                escape = defender.getAdvancedAttribute().bigEvasionRate - attacker.getAdvancedAttribute().bigHitRate;
                break;
            case MEDIUM:
                escape = defender.getAdvancedAttribute().mediumEvasionRate - attacker.getAdvancedAttribute().mediumHitRate;
                break;
            case SMALL:
                escape = defender.getAdvancedAttribute().smallEvasionRate - attacker.getAdvancedAttribute().smallHitRate;
        }
        escape += CombatAlgo.escapeByAgility(defender.getAgility(), attacker.getAgility());
        return random.getEscapeRandom() < escape;
    }

    private int calculateDamage() {
        int weaponDamage = random.getWeaponDamageRandom(weapon.getDamage().lower(), weapon.getDamage().higher());
        double strengthMultiply = CombatAlgo.multiplyByStrength(attacker.getStrength(), defender.getStrength() );
        double extraDamageMultiply = 0;
        switch (weapon.getType()) {
            case BIG:
                extraDamageMultiply = attacker.getAdvancedAttribute().bigExtraPercentageDamage;
                extraDamageMultiply -= defender.getAdvancedAttribute().antiBigExtraPercentageDamage;
                break;
            case MEDIUM:
                extraDamageMultiply = attacker.getAdvancedAttribute().mediumExtraPercentageDamage;
                extraDamageMultiply -= defender.getAdvancedAttribute().antiMediumExtraPercentageDamage;
            break;
            case SMALL:
                extraDamageMultiply = attacker.getAdvancedAttribute().smallExtraPercentageDamage;
                extraDamageMultiply -= defender.getAdvancedAttribute().antiSmallExtraPercentageDamage;
        }
        double multiply = strengthMultiply + extraDamageMultiply;
        int damage = (int) (weaponDamage * (1 + multiply));
        damage = AttackUtil.invokeHakiProtect(defender, damage, random);
        return damage;
    }
}
