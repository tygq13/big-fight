package bigfight.combat;

import bigfight.combat.util.CombatAlgo;
import bigfight.combat.fighter.FighterStatus;
import bigfight.combat.util.CombatRandom;
import bigfight.data.DataConfig;
import bigfight.model.skill.SkillModel;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.warrior.component.Empowerment;
import bigfight.model.weapon.Weapon;
import bigfight.model.weapon.struct.WeaponType;

public class Round {
    FighterStatus attacker;
    FighterStatus defender;
    Empowerment empowerment;
    CombatRandom random;

    public Round(FighterStatus attacker, FighterStatus defender, Empowerment empowerment, CombatRandom random) {
        this.attacker = attacker;
        this.defender = defender;
        this.empowerment = empowerment;
        this.random = random;
    }

    public int fight() {
        int roundChange = roundChangeBySpeed(); // use random
        attacker.changeWeapon(empowerment);
        if (throwWeapon()) {
            int damage = calculateThrowDamage();
            if (random.getAttackEscapeRandom() > calculateEscapeChance()) {
                defender.updateHealth(defender.getHealth() - damage);
            }
            return roundChange;
        }
        int damage = calculateDamage();
        if (random.getAttackEscapeRandom() > calculateEscapeChance()) {
            // hit
            defender.updateHealth(defender.getHealth() - damage);
            roundChange += roundChangeSpecial();
            // counter attack
            if (random.getCounterAttackRandom() < DataConfig.COUNTER_ATTACK_CHANCE) {
                if (random.getCounterEscapeRandom() > calculateCounterEscape()) {
                    int counterDamage = calculateCounterDamage();
                    attacker.updateHealth((attacker.getHealth() - counterDamage));
                }
            }
        }

        return roundChange;
    }

    private boolean throwWeapon() {
        if (empowerment.getWeapon() != null) {
            Weapon weapon = empowerment.getWeapon();
            return weapon.getType() == WeaponType.THROW || random.getThrowWeaponRandom() < DataConfig.THROW_WEAPON_CHANCE;
        }
        return  false;
    }

    private int calculateThrowDamage() {
        double multiply = CombatAlgo.multiplyByAgility(attacker.getAgility(), defender.getAgility());
        int weaponDamage =  empowerment.getWeapon().getDamage().getKey();
        return (int) (weaponDamage * (1 + multiply));
    }

    private int calculateDamage() {
        double multiply = CombatAlgo.multiplyByStrength(attacker.getStrength(), defender.getStrength());
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

    private int calculateCounterDamage() {
        double multiply = CombatAlgo.multiplyByStrength(attacker.getStrength(), defender.getStrength());
        Weapon weapon = defender.getHoldingWeapon();
        int damage =  (weapon == null? defender.getUnarmedDamage() : weapon.getDamage().getKey());
        return (int) (damage * (1 + multiply));
    }

    private double calculateEscapeChance() {
       double escape = attacker.getFocus() - defender.getEscape();
        escape += CombatAlgo.escapeByAgility(attacker.getAgility(), defender.getAgility());
        return escape;
    }

    private double calculateCounterEscape() {
        double escape = defender.getFocus() - attacker.getEscape();
        escape += CombatAlgo.escapeByAgility(defender.getAgility(), attacker.getAgility());
        return escape;
    }

    private int roundChangeBySpeed() {
        double ignore = CombatAlgo.ignoreBySpeed(attacker.getSpeed(), defender.getSpeed());
        if (random.getIgnoreRandom() < ignore) {
            return 1;
        }
        return 0;
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
