package bigfight.combat.attack;

import bigfight.combat.fighter.FighterStatus;
import bigfight.combat.util.CombatAlgo;
import bigfight.combat.util.CombatRandom;
import bigfight.model.weapon.Weapon;

public class CounterAttack {
    private FighterStatus defender;
    private FighterStatus attacker;
    private boolean isEscaped;
    private CombatRandom random;

    CounterAttack(FighterStatus defender, FighterStatus attacker, boolean isEscaped, CombatRandom random) {
        this.defender = defender;
        this.attacker = attacker;
        this.isEscaped = isEscaped;
        this.random = random;
    }

    public int counterAttack() {
        if (counterEscaped()) {
            // the counter attack is escaped
            return 0;
        } else {
            double multiply = CombatAlgo.multiplyByStrength(defender.getStrength(), attacker.getStrength());
            Weapon weapon = defender.getHoldingWeapon();
            int damage =  (weapon == null? defender.getUnarmedDamage() : weapon.getDamage().getKey());
            return (int) (damage * (1 + multiply));
        }
    }

    public boolean counterEscaped() {
        double escape = defender.getFocus() - attacker.getEscape();
        escape += CombatAlgo.escapeByAgility(defender.getAgility(), attacker.getAgility());
        return random.getAttackEscapeRandom() < escape;
    }
}
