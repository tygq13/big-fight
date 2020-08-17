package bigfight.combat.attack;

import bigfight.combat.fighter.components.CombatSelector;
import bigfight.combat.util.CombatAlgo;
import bigfight.combat.util.CombatRandom;
import bigfight.data.DataConfig;
import bigfight.model.warrior.component.attr.AttackAttribute;
import bigfight.model.warrior.component.attr.DefenceAttribute;
import bigfight.model.weapon.struct.Damage;

class AttackCalculator {
    private AttackAttribute attackAttribute;
    private DefenceAttribute defenceAttribute;
    private CombatRandom random;

    AttackCalculator(AttackAttribute attackAttribute, DefenceAttribute defenceAttribute, CombatRandom random) {
        this.attackAttribute = attackAttribute;
        this.defenceAttribute = defenceAttribute;
        this.random = random;
    }

    int damageAttributeMultiply(int damage) {
        double extraPercentageDamage = attackAttribute.getExtraPercentageDamage() - defenceAttribute.getAntiExtraPercentageDamage();
        extraPercentageDamage = Math.max(extraPercentageDamage, 0);
        damage = (int) (damage * (1 + extraPercentageDamage));

        // critical attack
        if (random.getCriticalAttackRandom() > (1 - attackAttribute.getCriticalChance() + defenceAttribute.getAntiCriticalChance())) {
            double criticalMultiply = attackAttribute.getCriticalDamage() - defenceAttribute.getAntiCriticalDamage();
            criticalMultiply = Math.max(criticalMultiply, 0);
            damage *= DataConfig.CRITICAL_DAMAGE_BASE + criticalMultiply;
        }
        return damage;
    }

    boolean isEscape(int attackerAgility, int defenderAgility) {
        return isEscape(0, attackerAgility, defenderAgility);
    }

    boolean isEscape(double baseEscape, int attackerAgility, int defenderAgility) {
        double escape = baseEscape;
        escape += defenceAttribute.getEvasionRate() - attackAttribute.getHitRate();
        escape += CombatAlgo.escapeByAgility(defenderAgility, attackerAgility);
        return random.getEscapeRandom() > (1 - escape);
    }

    int calculateDamage(Damage damage, int attackerAttribute, int defenderAttribute, CombatSelector combatSelector) {
        int weaponDamage = damage == null ? 0 : random.getWeaponDamageRandom(damage.lower(), damage.higher());
        int result = weaponDamage + CombatAlgo.extraDamageByAttribute(attackerAttribute, defenderAttribute);
        result = damageAttributeMultiply(result);
        result *= (1 - combatSelector.selectHakiProtect(random));
        return result;
    }
}
