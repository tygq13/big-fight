package bigfight.combat.attack;

import bigfight.combat.fighter.Fighter;
import bigfight.combat.util.CombatAlgo;
import bigfight.combat.util.CombatRandom;
import bigfight.model.skill.skills.special.BloodThirsty;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.weapon.Weapon;
import bigfight.ui.Uiable;

public class MediumTypeAttack implements Attackable{
    private Fighter attacker;
    private Fighter defender;
    private Weapon weapon;
    private CombatRandom random;
    private Uiable ui;

    public MediumTypeAttack(Fighter attacker, Fighter defender, Weapon weapon, CombatRandom random, Uiable ui) {
        this.attacker = attacker;
        this.defender = defender;
        this.weapon = weapon;
        this.random = random;
        this.ui = ui;
    }

    @Override
    public void attack() {
        ui.printWeaponMediumAttack(attacker.getName(), weapon.getName());
        if (escaped()) {
            ui.printDodge(defender.getName());
        } else {
            int damage = calculateDamage();
            defender.updateHealth(defender.getHealth() - damage);
            if (attacker.hasSkill(SkillIdentity.BLOOD_THIRSTY)) {
                BloodThirsty bloodThirsty = (BloodThirsty) attacker.getSkill(SkillIdentity.BLOOD_THIRSTY);
                if (random.getBloodThirstyRandom() < bloodThirsty.getInvocationChance()) {
                    attacker.updateHealth(attacker.getHealth() + (int)(damage * bloodThirsty.getLifeStealPercentage()));
                }
            }
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

    private boolean escaped() {
        double escape = defender.getAdvancedAttribute().mediumEvasionRate - attacker.getAdvancedAttribute().mediumHitRate;
        escape += CombatAlgo.escapeByAgility(defender.getAgility(), attacker.getAgility());
        return random.getEscapeRandom() < escape;
    }

    private int calculateDamage() {
        int weaponDamage = random.getWeaponDamageRandom(weapon.getDamage().lower(), weapon.getDamage().higher());
        int damage = weaponDamage + CombatAlgo.extraDamageByAttribute(attacker.getStrength(), defender.getStrength());
        damage = new AttackCalculator().damageAttributeMultiply(damage, attacker.getAdvancedAttribute().mediumAttackAttribute(),
                defender.getAdvancedAttribute().mediumDefenceAttribute());
        damage = AttackUtil.invokeHakiProtect(defender, damage, random);
        return damage;
    }
}
