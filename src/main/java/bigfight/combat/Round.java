package bigfight.combat;

import bigfight.combat.util.CombatAlgo;
import bigfight.combat.fighter.FighterStatus;
import bigfight.combat.util.CombatRandom;
import bigfight.combat.attack.*;
import bigfight.data.DataConfig;
import bigfight.model.warrior.component.Empowerment;
import bigfight.model.weapon.struct.WeaponType;

public class Round {
    private FighterStatus attacker;
    private FighterStatus defender;
    private Empowerment empowerment;
    private CombatRandom random;

    public Round(FighterStatus attacker, FighterStatus defender, Empowerment empowerment, CombatRandom random) {
        this.attacker = attacker;
        this.defender = defender;
        this.empowerment = empowerment;
        this.random = random;
    }

    public int fight() {
        int roundChange = roundChangeBySpeed();
        attacker.changeWeapon(empowerment);
        Attackable attack = null;
        if (empowerment.getSkill() != null) {
            attack = new SkillAttack(attacker, defender, empowerment.getSkill(), random);
        } else if (empowerment.getWeapon() == null) {
            attack = new UnarmedAttack(attacker, defender, random);
        } else if (empowerment.getWeapon().getType() == WeaponType.THROW) {
            attack = new ThrowTypeAttack(attacker, defender, empowerment.getWeapon(), random);
        } else if (random.getThrowWeaponRandom() < DataConfig.THROW_WEAPON_CHANCE) {
            attack = new ThrowOutAttack(attacker, defender, empowerment.getWeapon(), random);
        } else if (empowerment.getWeapon().getType() == WeaponType.SMALL) {
            attack = new SmallTypeAttack(attacker, defender, empowerment.getWeapon(), random);
        } else if (empowerment.getWeapon().getType() == WeaponType.MEDIUM) {
            attack = new MediumTypeAttack(attacker, defender, empowerment.getWeapon(), random);
        } else if (empowerment.getWeapon().getType() == WeaponType.BIG) {
            attack = new BigTypeAttack(attacker, defender, empowerment.getWeapon(), random);
        }
        // else throw exception
        if (attack != null) {
            attack.attack();
            roundChange += attack.getRoundChange();
        }
        return roundChange;
    }

    private int roundChangeBySpeed() {
        double ignore = CombatAlgo.ignoreBySpeed(attacker.getSpeed(), defender.getSpeed());
        if (random.getSpeedIgnoreRandom() < ignore) {
            return 1;
        }
        return 0;
    }
}
