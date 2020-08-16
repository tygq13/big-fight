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
                defender.getAdvancedAttribute().throwDefenceAttribute());
    }

    @Override
    public void attack() {
        for (int i = 0; i < 2; i++) {
            ui.printWeaponThrowAttack(attacker.getName(), weapon.getName());
            if (!escaped()) {
                int damage = calculateDamage();
                defender.updateHealth(defender.getHealth() - damage);
                if (attacker.hasSkill(SkillIdentity.BLOOD_THIRSTY)) {
                    BloodThirsty bloodThirsty = (BloodThirsty) attacker.getSkill(SkillIdentity.BLOOD_THIRSTY);
                    if (random.getBloodThirstyRandom() < bloodThirsty.getInvocationChance()) {
                        attacker.updateHealth(attacker.getHealth() + (int)(damage * bloodThirsty.getLifeStealPercentage()));
                    }
                }
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

    private boolean escaped() {
        return attackCalculator.isEscape(attacker.getAgility(), defender.getAgility(), random);
    }

    private int calculateDamage() {
        int weaponDamage = random.getWeaponDamageRandom(weapon.getDamage().lower(), weapon.getDamage().higher());
        int damage = weaponDamage + CombatAlgo.extraDamageByAttribute(attacker.getAgility(), defender.getAgility());
        damage = attackCalculator.damageAttributeMultiply(damage);
        damage = AttackUtil.invokeHakiProtect(defender, damage, random);
        return damage;
    }
}
