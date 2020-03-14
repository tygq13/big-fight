package bigfight.combat.attack;

import bigfight.combat.fighter.FighterStatus;
import bigfight.combat.util.CombatAlgo;
import bigfight.combat.util.CombatRandom;
import bigfight.model.warrior.component.Empowerment;
import bigfight.model.weapon.Weapon;

public class ThrowOutAttack implements Attackable {
    private FighterStatus attacker;
    private FighterStatus defender;
    private Weapon weapon;
    private CombatRandom random;

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
        }

        // loss the weapon after throwing out
        Weapon unarmed = null;
        attacker.changeWeapon(new Empowerment(unarmed));
    }

    @Override
    public void counterAttack() {
    }

    @Override
    public int getRoundChange() {
        return 0;
    }

    private boolean escaped() {
        double escape = attacker.getFocus() - defender.getEscape();
        escape += CombatAlgo.escapeByAgility(attacker.getAgility(), defender.getAgility());
        return random.getEscapeRandom() < escape;
    }
}
