package bigfight.combat.attack;

import bigfight.combat.fighter.FighterStatus;
import bigfight.combat.util.CombatAlgo;
import bigfight.combat.util.CombatRandom;
import bigfight.model.weapon.Weapon;
import bigfight.model.weapon.struct.WeaponIdentity;
import bigfight.model.weapon.weapons.GasHammer;
import bigfight.ui.Uiable;

public class BigTypeAttack implements Attackable{
    private FighterStatus attacker;
    private FighterStatus defender;
    private Weapon weapon;
    private CombatRandom random;
    private Uiable ui;

    public BigTypeAttack(FighterStatus attacker, FighterStatus defender, Weapon weapon, CombatRandom random, Uiable ui) {
        this.attacker = attacker;
        this.defender = defender;
        this.weapon = weapon;
        this.random = random;
        this.ui = ui;
    }

    @Override
    public void attack() {
        ui.printWeaponBigAttack(attacker.getName(), weapon.getName());
        // special case
        // todo: refactor this
        if (weapon.getIdentity() == WeaponIdentity.TRIDENT) {
            attacker.getFighterFlag().ignored += 1;
        }

        if (escaped()) {
            ui.printDodge(defender.getName());
        } else {
            defender.getFighterFlag().ignored += ignoreOpponent();
            int damage = calculateDamage();
            defender.updateHealth(defender.getHealth() - damage);
            ui.printInjury(defender.getName(), damage, defender.getHealth());
            CounterAttack counterAttack = new CounterAttack(defender, attacker, random, ui);
            if (!(counterAttack.specialCounter(damage))) {
                counterAttack.counterAttack();
            }
        }
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
        if (weapon.getIdentity() == WeaponIdentity.DEMON_SCYTHE) {
            return false;
        }
        double escape = defender.getAdvancedAttribute().bigEvasionRate - attacker.getAdvancedAttribute().bigHitRate;
        escape += CombatAlgo.escapeByAgility(defender.getAgility(), attacker.getAgility());
        return random.getEscapeRandom() < escape;
    }

    private int calculateDamage() {
        int weaponDamage = random.getWeaponDamageRandom(weapon.getDamage().lower(), weapon.getDamage().higher());
        double strengthMultiply = CombatAlgo.multiplyByStrength(attacker.getStrength(), defender.getStrength() );
        double extraDamageMultiply = attacker.getAdvancedAttribute().bigExtraPercentageDamage;
        double antiExtraDamageMultiple = defender.getAdvancedAttribute().antiBigExtraPercentageDamage;
        double multiply = strengthMultiply + extraDamageMultiply - antiExtraDamageMultiple;
        int damage = (int) (weaponDamage * (1 + multiply));
        damage = AttackUtil.invokeHakiProtect(defender, damage, random);
        return damage;
    }
}
