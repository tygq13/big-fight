package bigfight.combat.attack;

import bigfight.combat.fighter.FighterStatus;
import bigfight.combat.util.CombatAlgo;
import bigfight.combat.util.CombatRandom;

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
        String attackString = String.format("%s's body light as swallow, running towards the opponent in quick step " +
                "and attack with cannon fist", attacker.getName());
        if (escaped()) {
            attackString += String.format("%s dances in the air, twisting his body and dodge the attack", defender.getName());

            isEscaped = true;
        } else {
            int baseDamage = attacker.getUnarmedDamage();
            double multiply = CombatAlgo.multiplyByStrength(attacker.getStrength(), defender.getStrength() );
            int damage = (int) (baseDamage * (1 + multiply));
            defender.updateHealth(defender.getHealth() - damage);
            attackString += String.format("%s covers his wound in pain, losing HP %d (HP %d remains)",
                    defender.getName(), damage, defender.getHealth());
            isEscaped = false;
        }
        System.out.println(attackString);
        counterAttack();
    }

    @Override
    public int getRoundChange() {
        return 0;
    }

    private void counterAttack() {
        int damage = new CounterAttack(defender, attacker, isEscaped, random).counterAttack();
        attacker.updateHealth(attacker.getHealth() - damage);
    }

    private boolean escaped() {
        double escape = attacker.getFocus() - defender.getEscape();
        escape += CombatAlgo.escapeByAgility(defender.getAgility(), attacker.getAgility());
        return random.getEscapeRandom() < escape;
    }
}
