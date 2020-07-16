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
    private boolean isEscaped;

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
            isEscaped = true;
            ui.printDodge(defender.getName());
        } else {
            int weaponDamage = random.getWeaponDamageRandom(weapon.getDamage().lower(), weapon.getDamage().higher());
            double multiply = CombatAlgo.multiplyByStrength(attacker.getStrength(), defender.getStrength() );
            int damage = (int) (weaponDamage * (1 + multiply));
            defender.updateHealth(defender.getHealth() - damage);
            ui.printInjury(defender.getName(), damage, defender.getHealth());
        }
        counterAttack();
    }

    @Override
    public int getRoundChange() {
        return 0;
    }

    private void counterAttack() {
        int damage = new CounterAttack(defender, attacker, isEscaped, random, ui).counterAttack();
        attacker.updateHealth(attacker.getHealth() - damage);
    }

    private boolean escaped() {
        double escape = attacker.getFocus() - defender.getEscape();
        escape += CombatAlgo.escapeByAgility(defender.getAgility(), attacker.getAgility());
        return random.getEscapeRandom() < escape;
    }
}
