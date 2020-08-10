package bigfight.combat.attack;

import bigfight.combat.fighter.FighterStatus;
import bigfight.combat.util.CombatAlgo;
import bigfight.combat.util.CombatRandom;
import bigfight.model.weapon.Weapon;
import bigfight.ui.Uiable;

public class MediumTypeAttack implements Attackable{
    private FighterStatus attacker;
    private FighterStatus defender;
    private Weapon weapon;
    private CombatRandom random;
    private Uiable ui;

    public MediumTypeAttack(FighterStatus attacker, FighterStatus defender, Weapon weapon, CombatRandom random, Uiable ui) {
        this.attacker = attacker;
        this.defender = defender;
        this.weapon = weapon;
        this.random = random;
        this.ui = ui;
    }

    @Override
    public void attack() {
        ui.printWeaponMediumAttack(attacker.getName(), weapon.getName());
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
        double escape = defender.getAdvancedAttribute().mediumEvasionRate - attacker.getAdvancedAttribute().mediumHitRate;
        escape += CombatAlgo.escapeByAgility(defender.getAgility(), attacker.getAgility());
        return random.getEscapeRandom() < escape;
    }

    private int calculateDamage() {
        int weaponDamage = random.getWeaponDamageRandom(weapon.getDamage().lower(), weapon.getDamage().higher());
        double strengthMultiply = CombatAlgo.multiplyByStrength(attacker.getStrength(), defender.getStrength() );
        double extraDamageMultiply = attacker.getAdvancedAttribute().mediumExtraPercentageDamage;
        double antiExtraDamageMultiple = defender.getAdvancedAttribute().antiMediumExtraPercentageDamage;
        double multiply = strengthMultiply + extraDamageMultiply - antiExtraDamageMultiple;
        int damage = (int) (weaponDamage * (1 + multiply));
        damage = AttackUtil.invokeHakiProtect(defender, damage, random);
        return damage;
    }
}
