package bigfight.combat.attack;

import bigfight.combat.fighter.FighterStatus;
import bigfight.combat.util.CombatAlgo;
import bigfight.combat.util.CombatRandom;
import bigfight.model.weapon.Weapon;

public class ThrowTypeAttack implements Attackable{
    private FighterStatus attacker;
    private FighterStatus defender;
    private Weapon weapon;
    private CombatRandom random;
    private boolean isEscaped;

    public ThrowTypeAttack(FighterStatus attacker, FighterStatus defender, Weapon weapon, CombatRandom random) {
        this.attacker = attacker;
        this.defender = defender;
        this.weapon = weapon;
        this.random = random;
    }

    @Override
    public void attack() {
        for (int i = 0; i < 2; i++) {
            String attackString = String.format("%s swing a circle the weapon %s. The %s fleets towards the opponent",
                    attacker.getName(), weapon.getName(), weapon.getName());
            if (!escaped()) {

                isEscaped = false;
                int weaponDamage = weapon.getDamage().getKey();
                double multiply = CombatAlgo.multiplyByAgility(attacker.getAgility(), defender.getAgility());
                int damage = (int) (weaponDamage * (1 + multiply));
                defender.updateHealth(defender.getHealth() - damage);
                attackString += String.format("%s covers his wound in pain, losing HP %d (HP %d remains)",
                        defender.getName(), damage, defender.getHealth());
            }
            isEscaped = true;
            attackString += String.format("%s dances in the air, twisting his body and dodge the attack", defender.getName());

            System.out.println(attackString);
            counterAttack();
        }
    }

    @Override
    public int getRoundChange() {
        return 0;
    }

    private void counterAttack() {
        new CounterAttack(defender, attacker, isEscaped, random).specialCounter();
    }

    private boolean escaped() {
        double escape = attacker.getFocus() - defender.getEscape();
        escape += CombatAlgo.escapeByAgility(defender.getAgility(), attacker.getAgility());
        return random.getEscapeRandom() < escape;
    }
}
