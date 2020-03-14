package bigfight.combat.attack;

import bigfight.combat.fighter.FighterStatus;
import bigfight.combat.util.CombatAlgo;
import bigfight.combat.util.CombatRandom;
import bigfight.model.weapon.Weapon;

public class UnarmedAttack implements Attackable {
    private FighterStatus attacker;
    private FighterStatus defender;
    private CombatRandom random;
    private boolean isEscaped;

    public UnarmedAttack(FighterStatus attacker, FighterStatus defender,  CombatRandom random) {
        this.attacker = attacker;
        this.defender = defender;
        this.random = random;
    }

    @Override
    public void attack() {
        if (escaped()) {
            isEscaped = true;
        } else {
            int baseDamage = attacker.getUnarmedDamage();
            double multiply = CombatAlgo.multiplyByStrength(attacker.getStrength(), defender.getStrength() );
            defender.updateHealth(defender.getHealth() - (int) (baseDamage * (1 + multiply)));
        }
    }

    @Override
    public int getRoundChange() {
        return 0;
    }

    @Override
    public void counterAttack() {
        int damage = new CounterAttack(defender, attacker, isEscaped, random).counterAttack();
        attacker.updateHealth(attacker.getHealth() - damage);
    }

    private boolean escaped() {
        double escape = attacker.getFocus() - defender.getEscape();
        escape += CombatAlgo.escapeByAgility(attacker.getAgility(), defender.getAgility());
        return random.getEscapeRandom() < escape;
    }
}
