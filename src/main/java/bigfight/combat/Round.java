package bigfight.combat;

import bigfight.combat.util.CombatAlgo;
import bigfight.combat.fighter.FighterStatus;
import bigfight.combat.util.CombatRandom;
import bigfight.combat.attack.*;
import bigfight.data.DataConfig;
import bigfight.model.warrior.component.Empowerment;
import bigfight.model.weapon.struct.WeaponType;
import bigfight.ui.Uiable;

public class Round {
    private FighterStatus attacker;
    private FighterStatus defender;
    private Empowerment empowerment;
    private CombatRandom random;
    private Uiable ui;
    // todo: have a static variable to count the number of rounds

    public Round(FighterStatus attacker, FighterStatus defender, Empowerment empowerment, CombatRandom random, Uiable ui) {
        this.attacker = attacker;
        this.defender = defender;
        this.empowerment = empowerment;
        this.random = random;
        this.ui = ui;
    }

    public void fight() {
        attacker.updateStatusByFlag();
        defender.updateStatusByFlag();
        defender.getFighterFlag().ignored += roundChangeBySpeed();
        attacker.changeWeapon(empowerment);
        // todo: print weapon ui here instead of insides the attack
        Attackable attack = null;
        if (empowerment.getSkill() != null) {
            attack = new SkillAttack(attacker, defender, empowerment.getSkill(), random, ui);
        } else if (empowerment.getWeapon() == null) {
            attack = new UnarmedAttack(attacker, defender, random, ui);
        } else if (empowerment.getWeapon().getType() == WeaponType.THROW) {
            attack = new ThrowTypeAttack(attacker, defender, empowerment.getWeapon(), random, ui);
        } else if (random.getThrowWeaponRandom() < DataConfig.THROW_WEAPON_CHANCE) {
            attack = new ThrowOutAttack(attacker, defender, empowerment.getWeapon(), random, ui);
        } else if (empowerment.getWeapon().getType() == WeaponType.SMALL) {
            attack = new SmallTypeAttack(attacker, defender, empowerment.getWeapon(), random, ui);
        } else if (empowerment.getWeapon().getType() == WeaponType.MEDIUM) {
            attack = new MediumTypeAttack(attacker, defender, empowerment.getWeapon(), random, ui);
        } else if (empowerment.getWeapon().getType() == WeaponType.BIG) {
            attack = new BigTypeAttack(attacker, defender, empowerment.getWeapon(), random, ui);
        }
        if (attack != null) {
            attack.attack();
            // double hit
            if (attacker.getFighterFlag().fastHandsFlag) {
                attacker.getFighterFlag().fastHandsFlag = false;
                attack.attack();
            }
        }
    }

    private int roundChangeBySpeed() {
        double ignore = CombatAlgo.ignoreBySpeed(attacker.getSpeed(), defender.getSpeed());
        if (random.getSpeedIgnoreRandom() < ignore) {
            return 1;
        }
        return 0;
    }
}
