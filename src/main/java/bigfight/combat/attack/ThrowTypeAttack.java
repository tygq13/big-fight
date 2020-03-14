package bigfight.combat.attack;

import bigfight.combat.Combat;
import bigfight.combat.fighter.Fighter;
import bigfight.combat.fighter.FighterStatus;
import bigfight.combat.util.CombatAlgo;
import bigfight.combat.util.CombatRandom;
import bigfight.model.weapon.Weapon;

public class ThrowTypeAttack implements Attackable{
    private FighterStatus attacker;
    private FighterStatus defender;
    private Weapon weapon;
    private CombatRandom random;

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
                int weaponDamage = weapon.getDamage().getKey();
                double multiply = CombatAlgo.multiplyByAgility(attacker.getAgility(), defender.getAgility());
                defender.updateHealth(defender.getHealth() - (int) (weaponDamage * (1 + multiply)));
            }
        }
    }

    @Override
    public int getRoundChange() {
        return 0;
    }

    @Override
    public void counterAttack() {
    }

    private boolean escaped() {
        double escape = attacker.getFocus() - defender.getEscape();
        escape += CombatAlgo.escapeByAgility(attacker.getAgility(), defender.getAgility());
        return random.getEscapeRandom() < escape;
    }
}
