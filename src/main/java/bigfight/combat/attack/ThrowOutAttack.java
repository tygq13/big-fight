package bigfight.combat.attack;

import bigfight.combat.fighter.FighterStatus;
import bigfight.combat.util.CombatAlgo;
import bigfight.combat.util.CombatRandom;
import bigfight.model.warrior.component.Empowerment;
import bigfight.model.weapon.Weapon;
import bigfight.model.weapon.struct.WeaponIdentity;
import bigfight.model.weapon.weapons.GasHammer;
import bigfight.model.weapon.weapons.Trident;
import bigfight.ui.Uiable;

public class ThrowOutAttack implements Attackable {
    private FighterStatus attacker;
    private FighterStatus defender;
    private Weapon weapon;
    private CombatRandom random;
    private Uiable ui;
    private boolean isEscaped;

    public ThrowOutAttack(FighterStatus attacker, FighterStatus defender, Weapon weapon, CombatRandom random, Uiable ui) {
        this.attacker = attacker;
        this.defender = defender;
        this.weapon = weapon;
        this.random = random;
        this.ui = ui;
    }

    @Override
    public void attack() {
        ui.printThrowOutAttack(attacker.getName(), weapon.getName());
        if (!escaped()) {
            int damage = calculateDamage();
            defender.updateHealth(defender.getHealth() - damage);
            ui.printInjury(defender.getName(), damage, defender.getHealth());
            isEscaped = false;
        }
        isEscaped = true;
        ui.printDodge(defender.getName());

        // loss the weapon after throwing out
        Weapon unarmed = null;
        attacker.changeWeapon(new Empowerment(unarmed));
        counterAttack();
    }

    private void counterAttack() {
        new CounterAttack(defender, attacker, isEscaped, random, ui).specialCounter();
    }

    @Override
    public int getRoundChange() {
        switch (weapon.getIdentity()) {
            case TRIDENT:
                Trident trident = (Trident) weapon.getModel();
                return 0 - trident.getRestRound();
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
        double escape = attacker.getFocus() - defender.getEscape();
        escape += CombatAlgo.escapeByAgility(defender.getAgility(), attacker.getAgility());
        return random.getEscapeRandom() < escape;
    }

    private int calculateDamage() {
        int weaponDamage = random.getWeaponDamageRandom(weapon.getDamage().lower(), weapon.getDamage().higher());
        double strengthMultiply = CombatAlgo.multiplyByStrength(attacker.getStrength(), defender.getStrength() );
        double extraDamageMultiply = attacker.getWeaponAttribute().bigExtraPercentageDamage;
        double multiply = strengthMultiply + extraDamageMultiply;
        int damage = (int) (weaponDamage * (1 + multiply));
        return damage;
    }
}
