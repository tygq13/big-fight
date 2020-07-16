package bigfight.combat.attack;

import bigfight.combat.fighter.FighterStatus;
import bigfight.combat.util.CombatAlgo;
import bigfight.combat.util.CombatRandom;
import bigfight.data.DataConfig;
import bigfight.model.skill.skills.ApparentDeath;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.weapon.Weapon;
import bigfight.ui.Uiable;

class CounterAttack {
    private FighterStatus defender;
    private FighterStatus attacker;
    private boolean isEscaped;
    private CombatRandom random;
    private Uiable ui;

    CounterAttack(FighterStatus defender, FighterStatus attacker, boolean isEscaped, CombatRandom random, Uiable ui) {
        this.defender = defender;
        this.attacker = attacker;
        this.isEscaped = isEscaped;
        this.random = random;
        this.ui = ui;
    }

    boolean specialCounter() {
        if (defender.getHealth() <= 0 && defender.hasSkill(SkillIdentity.APPARENT_DEATH)) {
            ApparentDeath apparentDeath = (ApparentDeath) defender.getSkill(SkillIdentity.APPARENT_DEATH);
            int lastHealth = apparentDeath.getLastHealth();
            defender.updateHealth(lastHealth);
            defender.removeSkill(SkillIdentity.APPARENT_DEATH);
            ui.printSkillApparentDeath(defender.getName());
            return true;
        }
        return false;
    }

    int counterAttack() {
        if (specialCounter()) {
            return 0;
        }
        if (random.getCounterAttackRandom() < DataConfig.COUNTER_ATTACK_CHANCE) {
            Weapon weapon = defender.getHoldingWeapon();
            ui.printCounterAttackWeapon(defender.getName(), weapon == null ? "his fist" : weapon.getName());

            if (counterEscaped()) {
                // the counter attack is escaped
                ui.printCounterAttackDodge(attacker.getName());
                return 0;
            } else {
                double multiply = CombatAlgo.multiplyByStrength(defender.getStrength(), attacker.getStrength());
                // no multiply whatsoever
                int damage =  (weapon == null
                        ? random.getWeaponDamageRandom(defender.getUnarmedDamage().lower(), defender.getUnarmedDamage().higher())
                        : random.getWeaponDamageRandom(weapon.getDamage().lower(), weapon.getDamage().higher()));
                ui.printCounterAttackInjury(attacker.getName(), damage, attacker.getHealth());
                return (int) (damage * (1 + multiply));
            }
        }
        return 0;
    }

    private boolean counterEscaped() {
        double escape = defender.getFocus() - attacker.getEscape();
        escape += CombatAlgo.escapeByAgility(defender.getAgility(), attacker.getAgility());
        return random.getCounterEscapeRandom() < escape;
    }
}
