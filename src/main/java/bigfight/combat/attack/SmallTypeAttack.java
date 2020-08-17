package bigfight.combat.attack;

import bigfight.combat.fighter.Fighter;
import bigfight.combat.util.CombatAlgo;
import bigfight.combat.util.CombatRandom;
import bigfight.model.skill.skills.special.BloodThirsty;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.weapon.Weapon;
import bigfight.model.weapon.struct.WeaponIdentity;
import bigfight.ui.Uiable;

public class SmallTypeAttack implements Attackable{
    private Fighter attacker;
    private Fighter defender;
    private Weapon weapon;
    private CombatRandom random;
    private Uiable ui;
    private AttackCalculator attackCalculator;

    public SmallTypeAttack(Fighter attacker, Fighter defender, Weapon weapon, CombatRandom random, Uiable ui) {
        this.attacker = attacker;
        this.defender = defender;
        this.weapon = weapon;
        this.random = random;
        this.ui = ui;
        this.attackCalculator = new AttackCalculator(attacker.getAdvancedAttribute().smallAttackAttribute(),
                defender.getAdvancedAttribute().smallDefenceAttribute(), random);
    }

    @Override
    public void attack() {
        ui.printWeaponSmallAttack(attacker.getName(), weapon.getName());
        if (escaped()) {
            ui.printDodge(defender.getName());
        } else {
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

    private boolean escaped() {
        if (weapon.getIdentity() == WeaponIdentity.JUDGE_PENCIL) {
            return false;
        }
        return attackCalculator.isEscape(attacker.getAgility(), defender.getAgility());
    }

    private int calculateDamage() {
        return attackCalculator.calculateDamage(attacker.getUnarmedDamage(), attacker.getStrength(), defender.getStrength(),
                defender.getCombatSelector());
    }
}
