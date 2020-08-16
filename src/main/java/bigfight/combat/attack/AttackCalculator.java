package bigfight.combat.attack;

import bigfight.combat.fighter.components.CombatSelector;
import bigfight.combat.util.CombatAlgo;
import bigfight.combat.util.CombatRandom;
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
        return damage;
    }

    boolean isEscape(int attackerAgility, int defenderAgility) {
        double escape = defenceAttribute.getEvasionRate() - attackAttribute.getHitRate();
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
