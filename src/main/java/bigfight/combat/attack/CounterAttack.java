package bigfight.combat.attack;

import bigfight.combat.fighter.FighterStatus;
import bigfight.combat.util.CombatAlgo;
import bigfight.combat.util.CombatRandom;
import bigfight.model.skill.skills.ApparentDeath;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.weapon.Weapon;

class CounterAttack {
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

    boolean specialCounter() {
        if (defender.getHealth() <= 0 && defender.hasSkill(SkillIdentity.APPARENT_DEATH)) {
            ApparentDeath apparentDeath = (ApparentDeath) defender.getSkill(SkillIdentity.APPARENT_DEATH);
            int lastHealth = apparentDeath.getLastHealth();
            defender.updateHealth(lastHealth);
            return true;
        }
        return false;
    }

    int counterAttack() {
        if (specialCounter()) {
            return 0;
        }
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

    private boolean counterEscaped() {
        double escape = defender.getFocus() - attacker.getEscape();
        escape += CombatAlgo.escapeByAgility(defender.getAgility(), attacker.getAgility());
        return random.getCounterEscapeRandom() < escape;
    }
}
