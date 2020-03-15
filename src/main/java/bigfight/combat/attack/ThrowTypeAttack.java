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
            if (!escaped()) {
                isEscaped = false;
                int weaponDamage = weapon.getDamage().getKey();
                double multiply = CombatAlgo.multiplyByAgility(attacker.getAgility(), defender.getAgility());
                defender.updateHealth(defender.getHealth() - (int) (weaponDamage * (1 + multiply)));
            }
            isEscaped = true;
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
