package bigfight.combat.attack;

import bigfight.combat.fighter.FighterStatus;
import bigfight.combat.util.CombatAlgo;
import bigfight.combat.util.CombatRandom;
import bigfight.model.warrior.component.Empowerment;
import bigfight.model.weapon.Weapon;
import bigfight.ui.Uiable;

public class ThrowTypeAttack implements Attackable{
    private FighterStatus attacker;
    private FighterStatus defender;
    private Weapon weapon;
    private CombatRandom random;
    private Uiable ui;
    private boolean isEscaped;

    public ThrowTypeAttack(FighterStatus attacker, FighterStatus defender, Weapon weapon, CombatRandom random, Uiable ui) {
        this.attacker = attacker;
        this.defender = defender;
        this.weapon = weapon;
        this.random = random;
        this.ui = ui;
    }

    @Override
    public void attack() {
        for (int i = 0; i < 2; i++) {
            ui.printWeaponThrowAttack(attacker.getName(), weapon.getName());
            if (!escaped()) {

                isEscaped = false;
                int damage = calculateDamage();
                defender.updateHealth(defender.getHealth() - damage);
                ui.printInjury(defender.getName(), damage, defender.getHealth());
                new CounterAttack(defender, attacker, random, ui).specialCounter(damage);
            } else {
                isEscaped = true;
                ui.printDodge(defender.getName());
            }
        }
        Weapon unarmed = null;
        attacker.changeWeapon(new Empowerment(unarmed));
    }

    private boolean escaped() {
        double escape = defender.getAdvancedAttribute().throwEvasionRate - attacker.getAdvancedAttribute().throwHitRate;
        escape += CombatAlgo.escapeByAgility(defender.getAgility(), attacker.getAgility());
        return random.getEscapeRandom() < escape;
    }

    private int calculateDamage() {
        int weaponDamage = random.getWeaponDamageRandom(weapon.getDamage().lower(), weapon.getDamage().higher());
        double strengthMultiply = CombatAlgo.multiplyByStrength(attacker.getStrength(), defender.getStrength() );
        double extraDamageMultiply = attacker.getAdvancedAttribute().throwExtraPercentageDamage;
        double antiExtraDamageMultiple = defender.getAdvancedAttribute().antiThrowExtraPercentageDamage;
        double multiply = strengthMultiply + extraDamageMultiply - antiExtraDamageMultiple;
        int damage = (int) (weaponDamage * (1 + multiply));
        damage = AttackUtil.invokeHakiProtect(defender, damage, random);
        return damage;
    }
}
