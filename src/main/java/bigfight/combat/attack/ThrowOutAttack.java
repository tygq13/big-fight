package bigfight.combat.attack;

import bigfight.combat.fighter.FighterStatus;
import bigfight.combat.util.CombatAlgo;
import bigfight.combat.util.CombatRandom;
import bigfight.model.warrior.component.Empowerment;
import bigfight.model.weapon.Weapon;
import bigfight.model.weapon.struct.WeaponIdentity;
import bigfight.model.weapon.weapons.GasHammer;
import bigfight.model.weapon.weapons.Trident;

public class ThrowOutAttack implements Attackable {
    private FighterStatus attacker;
    private FighterStatus defender;
    private Weapon weapon;
    private CombatRandom random;
    private boolean isEscaped;

    public ThrowOutAttack(FighterStatus attacker, FighterStatus defender, Weapon weapon, CombatRandom random) {
        this.attacker = attacker;
        this.defender = defender;
        this.weapon = weapon;
        this.random = random;
    }

    @Override
    public void attack() {
        if (!escaped()) {
            int weaponDamage = weapon.getDamage().getKey();
            double multiply = CombatAlgo.multiplyByAgility(attacker.getAgility(), defender.getAgility() );
            defender.updateHealth(defender.getHealth() - (int) (weaponDamage * (1 + multiply)));
            isEscaped = false;
        }
        isEscaped = true;

        // loss the weapon after throwing out
        Weapon unarmed = null;
        attacker.changeWeapon(new Empowerment(unarmed));
        counterAttack();
    }

    private void counterAttack() {
        new CounterAttack(defender, attacker, isEscaped, random).specialCounter();
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
}
