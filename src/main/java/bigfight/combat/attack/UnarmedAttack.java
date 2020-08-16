package bigfight.combat.attack;

import bigfight.combat.fighter.Fighter;
import bigfight.combat.util.CombatAlgo;
import bigfight.combat.util.CombatRandom;
import bigfight.model.skill.skills.special.BloodThirsty;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.ui.Uiable;

public class UnarmedAttack implements Attackable {
    private Fighter attacker;
    private Fighter defender;
    private CombatRandom random;
    private Uiable ui;
    private AttackCalculator attackCalculator;

    public UnarmedAttack(Fighter attacker, Fighter defender, CombatRandom random, Uiable ui) {
        this.attacker = attacker;
        this.defender = defender;
        this.random = random;
        this.ui = ui;
        this.attackCalculator = new AttackCalculator(attacker.getAdvancedAttribute().unarmedAttackAttribute(),
                defender.getAdvancedAttribute().unarmedDefenceAttribute());
    }

    @Override
    public void attack() {
        ui.printUnarmedAttack(attacker.getName());
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
        return attackCalculator.isEscape(attacker.getAgility(), defender.getAgility(), random);
    }

    private int calculateDamage() {
        int baseDamage = random.getWeaponDamageRandom(attacker.getUnarmedDamage().lower(), attacker.getUnarmedDamage().higher());
        int damage = baseDamage + CombatAlgo.extraDamageByAttribute(attacker.getStrength(), defender.getStrength());
        damage = attackCalculator.damageAttributeMultiply(damage);
        damage = AttackUtil.invokeHakiProtect(defender, damage, random);
        return damage;
    }
}
