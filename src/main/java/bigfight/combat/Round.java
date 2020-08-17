package bigfight.combat;

import bigfight.combat.util.CombatAlgo;
import bigfight.combat.fighter.Fighter;
import bigfight.combat.util.CombatRandom;
import bigfight.combat.attack.*;
import bigfight.data.DataConfig;
import bigfight.model.warrior.component.Empowerment;
import bigfight.model.weapon.struct.WeaponType;
import bigfight.ui.Uiable;

public class Round {
    private Fighter attacker;
    private Fighter defender;
    private Empowerment empowerment;
    private CombatRandom random;
    private Uiable ui;

    public Round(Fighter attacker, Fighter defender, Empowerment empowerment, CombatRandom random, Uiable ui) {
        this.attacker = attacker;
        this.defender = defender;
        this.empowerment = empowerment;
        this.random = random;
        this.ui = ui;
        attacker.newRoundUpdate();
        defender.newRoundUpdate();
    }

    public void fight() {
        defender.getFighterFlag().ignored += roundChangeBySpeed();
        attacker.changeWeapon(empowerment);
        // todo: print weapon ui here instead of insides the attack
        if (empowerment.getSkill() != null) {
            new SkillAttack(attacker, defender, empowerment.getSkill(), random, ui).attack();
        } else if (empowerment.getWeapon() == null) {
            new UnarmedAttack(attacker, defender, random, ui).attack();
        } else if (empowerment.getWeapon().getType() == WeaponType.THROW) {
            new ThrowTypeAttack(attacker, defender, empowerment.getWeapon(), random, ui).attack();
        } else if (random.getThrowWeaponRandom() > 1 - DataConfig.THROW_WEAPON_CHANCE) {
            new ThrowOutAttack(attacker, defender, empowerment.getWeapon(), random, ui).attack();
        } else if (empowerment.getWeapon().getType() == WeaponType.SMALL) {
            new SmallTypeAttack(attacker, defender, empowerment.getWeapon(), random, ui).attack();
        } else if (empowerment.getWeapon().getType() == WeaponType.MEDIUM) {
            new MediumTypeAttack(attacker, defender, empowerment.getWeapon(), random, ui).attack();
        } else if (empowerment.getWeapon().getType() == WeaponType.BIG) {
            new BigTypeAttack(attacker, defender, empowerment.getWeapon(), random, ui).attack();
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
