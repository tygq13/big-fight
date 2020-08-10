package bigfight.combat.attack;

import bigfight.combat.fighter.Fighter;
import bigfight.combat.util.CombatAlgo;
import bigfight.combat.util.CombatRandom;
import bigfight.ui.Uiable;

public class UnarmedAttack implements Attackable {
    private Fighter attacker;
    private Fighter defender;
    private CombatRandom random;
    private Uiable ui;

    public UnarmedAttack(Fighter attacker, Fighter defender, CombatRandom random, Uiable ui) {
        this.attacker = attacker;
        this.defender = defender;
        this.random = random;
        this.ui = ui;
    }

    @Override
    public void attack() {
        ui.printUnarmedAttack(attacker.getName());
        if (escaped()) {
            ui.printDodge(defender.getName());
        } else {
            int damage = calculateDamage();
            defender.updateHealth(defender.getHealth() - damage);
            ui.printInjury(defender.getName(), damage, defender.getHealth());
            CounterAttack counterAttack = new CounterAttack(defender, attacker, random, ui);
            if (!(counterAttack.specialCounter(damage))) {
                counterAttack.counterAttack();
            }
        }
    }

    private boolean escaped() {
        double escape = defender.getAdvancedAttribute().unarmedEvasionRate - attacker.getAdvancedAttribute().bigHitRate;
        escape += CombatAlgo.escapeByAgility(defender.getAgility(), attacker.getAgility());
        return random.getEscapeRandom() < escape;
    }

    private int calculateDamage() {
        int baseDamage = random.getWeaponDamageRandom(attacker.getUnarmedDamage().lower(), attacker.getUnarmedDamage().higher());
        double strengthMultiply = CombatAlgo.multiplyByStrength(attacker.getStrength(), defender.getStrength() );
        double extraDamageMultiply = attacker.getAdvancedAttribute().unarmedExtraPercentageDamage;
        double antiExtraDamageMultiple = defender.getAdvancedAttribute().antiUnarmedExtraPercentageDamage;
        double multiply = strengthMultiply + extraDamageMultiply - antiExtraDamageMultiple;
        int damage = (int) (baseDamage * (1 + multiply));
        damage = AttackUtil.invokeHakiProtect(defender, damage, random);
        return damage;
    }
}
