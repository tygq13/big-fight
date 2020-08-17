package bigfight.combat.attack;

import bigfight.combat.fighter.Fighter;
import bigfight.combat.fighter.components.CombatSelector;
import bigfight.combat.util.CombatAlgo;
import bigfight.combat.util.CombatRandom;
import bigfight.model.skill.skills.special.BloodThirsty;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.weapon.Weapon;
import bigfight.model.weapon.struct.WeaponIdentity;
import bigfight.model.weapon.weapons.GasHammer;
import bigfight.ui.Uiable;

public class BigTypeAttack implements Attackable{
    private Fighter attacker;
    private Fighter defender;
    private Weapon weapon;
    private CombatRandom random;
    private Uiable ui;
    private AttackCalculator attackCalculator;

    public BigTypeAttack(Fighter attacker, Fighter defender, Weapon weapon, CombatRandom random, Uiable ui) {
        this.attacker = attacker;
        this.defender = defender;
        this.weapon = weapon;
        this.random = random;
        this.ui = ui;
        this.attackCalculator = new AttackCalculator(attacker.getAdvancedAttribute().bigAttackAttribute(),
                defender.getAdvancedAttribute().bigDefenceAttribute(), random);
    }

    @Override
    public void attack() {
        ui.printWeaponBigAttack(attacker.getName(), weapon.getName());
        // special case
        // todo: refactor this
        if (weapon.getIdentity() == WeaponIdentity.TRIDENT) {
            attacker.getFighterFlag().ignored += 1;
        }

        if (escaped()) {
            ui.printDodge(defender.getName());
        } else {
            defender.getFighterFlag().ignored += ignoreOpponent();
            int damage = calculateDamage();
            defender.updateHealth(defender.getHealth() - damage);
            lifeSteal(damage);
            ui.printInjury(defender.getName(), damage, defender.getHealth());
            CounterAttack counterAttack = new CounterAttack(defender, attacker, random, ui);
            if (!(counterAttack.specialCounter(damage))) {
                counterAttack.counterAttack();
            }
        }
        if (random.doubleHitRandom() < attacker.getAdvancedAttribute().doubleHitChance && !attacker.getFighterFlag().doubleHited) {
            attacker.getFighterFlag().doubleHited = true;
            attack();
            attacker.getFighterFlag().doubleHited = false;
        }
    }

    private void lifeSteal(int damage) {
        double lifeSteal = attacker.getCombatSelector().selectBloodThirsty(random);
        attacker.updateHealth(attacker.getHealth() + (int) (damage * lifeSteal));
    }


    private int ignoreOpponent() {
        if (weapon.getIdentity() == null) {
            return 0;
        }
        switch (weapon.getIdentity()) {
            case GAS_HAMMER:
                GasHammer gasHammer = (GasHammer) weapon.getModel();
                return random.getIgnoreRandom() < gasHammer.getIgnoreChance() ? 1 : 0;
        }
        return 0;
    }

    private boolean escaped() {
        if (weapon.getIdentity() == WeaponIdentity.DEMON_SCYTHE) {
            return false;
        }
        return attackCalculator.isEscape(attacker.getAgility(), defender.getAgility());
    }

    private int calculateDamage() {
        return attackCalculator.calculateDamage(attacker.getUnarmedDamage(), attacker.getStrength(), defender.getStrength(),
                defender.getCombatSelector());
    }
}
