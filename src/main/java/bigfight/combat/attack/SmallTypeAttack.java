package bigfight.combat.attack;

import bigfight.combat.fighter.FighterStatus;
import bigfight.combat.util.CombatAlgo;
import bigfight.combat.util.CombatRandom;
import bigfight.model.weapon.Weapon;
import bigfight.model.weapon.struct.WeaponIdentity;

public class SmallTypeAttack implements Attackable{
    private FighterStatus attacker;
    private FighterStatus defender;
    private Weapon weapon;
    private CombatRandom random;
    private boolean isEscaped;

    public SmallTypeAttack(FighterStatus attacker, FighterStatus defender, Weapon weapon, CombatRandom random) {
        this.attacker = attacker;
        this.defender = defender;
        this.weapon = weapon;
        this.random = random;
    }

    @Override
    public void attack() {
        String attackString = String.format("%s stand still, suddenly pocketing out a small weapon %s stabbing" +
                "towards the center of the opponent", attacker.getName(), weapon.getName());
        if (escaped()) {
            attackString += String.format("%s dances in the air, twisting his body and dodge the attack", defender.getName());

            isEscaped = true;
        } else {
            int weaponDamage = weapon.getDamage().getKey();
            double multiply = CombatAlgo.multiplyByStrength(attacker.getStrength(), defender.getStrength() );
            int damage = (int) (weaponDamage * (1 + multiply));
            defender.updateHealth(defender.getHealth() - damage);
            attackString += String.format("%s covers his wound in pain, losing HP %d (HP %d remains)",
                    defender.getName(), damage, defender.getHealth());
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
        if (weapon.getIdentity() == WeaponIdentity.JUDGE_PENCIL) {
            return false;
        }
        double escape = attacker.getFocus() - defender.getEscape();
        escape += CombatAlgo.escapeByAgility(defender.getAgility(), attacker.getAgility());
        return random.getEscapeRandom() < escape;
    }
}
