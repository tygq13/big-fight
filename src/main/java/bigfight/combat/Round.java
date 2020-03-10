package bigfight.combat;

import bigfight.combat.CombatAlgo;
import bigfight.combat.fighter.FighterStatus;
import bigfight.model.skill.SkillModel;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.warrior.component.Empowerment;
import bigfight.model.weapon.Weapon;

import java.util.Random;

public class Round {
    FighterStatus attacker;
    FighterStatus defender;
    Empowerment empowerment;
    Random rand;

    public Round(FighterStatus attacker, FighterStatus defender, Empowerment empowerment, Random rand) {
        this.attacker = attacker;
        this.defender = defender;
        this.empowerment = empowerment;
        this.rand = rand;
    }

    public int fight() {
        int roundChange = roundChangeBySpeed(); // use random
        attacker.changeWeapon(empowerment);
        int damage = calculateDamage();
        if (rand.nextDouble() > calculateEscapeChance()) {
            defender.updateHealth(defender.getHealth() - damage);
            roundChange += roundChangeSpecial();
        }
        return roundChange;
    }

    private int calculateDamage() {
        double multiply = calculateStrengthMultiply();
        if (empowerment.getWeapon() != null) {
            // attack using weapon
            Weapon weapon = empowerment.getWeapon();
            int weaponDamage =  weapon.getDamage().getKey();
            return (int) (weaponDamage * (1 + multiply));
        } else if (empowerment.getSkill() != null) {
            // attack using skill
            SkillModel skill = empowerment.getSkill();
            if (skill.getIdentity() == SkillIdentity.ROAR) {
                return getRoarDamage(skill);
            }
        }

        // attack by hands
        return (int) (attacker.getUnarmedDamage() * (1 + multiply));
    }

    private double calculateEscapeChance() {
       double escape = attacker.getFocus() - defender.getEscape();
        escape += CombatAlgo.escapeByAgility(attacker.getAgility(), defender.getAgility());
        return escape;
    }

    private int roundChangeBySpeed() {
        double ignore = CombatAlgo.ignoreBySpeed(attacker.getSpeed(), defender.getSpeed());
        if (rand.nextDouble() < ignore) {
            return 1;
        }
        return 0;
    }

    private double calculateStrengthMultiply() {
        double multiply = CombatAlgo.multiplyByStrength(attacker.getStrength(), defender.getStrength());
        return multiply;
    }

    private int roundChangeSpecial() {
        if (empowerment.getSkill() != null) {
            // the cases of special skills
            SkillModel skill = empowerment.getSkill();
            if (skill.getIdentity() == SkillIdentity.ROAR) {
                return 1;
            }
        }
        return 0;
    }

    // probably should refactor skill class
    private int getRoarDamage(SkillModel skill) {
        return 15;
    }
}
