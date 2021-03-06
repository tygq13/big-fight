package bigfight.combat.attack;

import bigfight.combat.fighter.Fighter;
import bigfight.combat.util.CombatAlgo;
import bigfight.combat.util.CombatRandom;
import bigfight.model.skill.skills.special.BloodThirsty;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.warrior.component.Empowerment;
import bigfight.model.weapon.Weapon;
import bigfight.ui.Uiable;

public class ThrowTypeAttack implements Attackable{
    private Fighter attacker;
    private Fighter defender;
    private Weapon weapon;
    private CombatRandom random;
    private Uiable ui;
    private AttackCalculator attackCalculator;

    public ThrowTypeAttack(Fighter attacker, Fighter defender, Weapon weapon, CombatRandom random, Uiable ui) {
        this.attacker = attacker;
        this.defender = defender;
        this.weapon = weapon;
        this.random = random;
        this.ui = ui;
        this.attackCalculator = new AttackCalculator(attacker.getAdvancedAttribute().throwAttackAttribute(),
                defender.getAdvancedAttribute().throwDefenceAttribute(), random);
    }

    @Override
    public void attack() {
        for (int i = 0; i < 2; i++) {
            ui.printWeaponThrowAttack(attacker.getName(), weapon.getName());
            if (!escaped()) {
                int damage = calculateDamage();
                defender.updateHealth(defender.getHealth() - damage);
                lifeSteal(damage);
                ui.printInjury(defender.getName(), damage, defender.getHealth());
                new CounterAttack(defender, attacker, random, ui).specialCounter(damage);
            } else {
                ui.printDodge(defender.getName());
            }
            if (random.doubleHitRandom() < attacker.getAdvancedAttribute().doubleHitChance && !attacker.getFighterFlag().doubleHited) {
                attacker.getFighterFlag().doubleHited = true;
                i -= 1;
            }
        }
        attacker.getFighterFlag().doubleHited = false;
        Weapon unarmed = null;
        attacker.changeWeapon(new Empowerment(unarmed));
    }

    private void lifeSteal(int damage) {
        double lifeSteal = attacker.getCombatSelector().selectBloodThirsty(random);
        attacker.updateHealth(attacker.getHealth() + (int) (damage * lifeSteal));
    }


    private boolean escaped() {
        return attackCalculator.isEscape(attacker.getAgility(), defender.getAgility());
    }

    private int calculateDamage() {
        return attackCalculator.calculateDamage(attacker.getUnarmedDamage(), attacker.getAgility(), defender.getAgility(),
                defender.getCombatSelector());
    }
}
