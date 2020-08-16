package bigfight.combat.attack;

import bigfight.combat.Combat;
import bigfight.combat.util.CombatAlgo;
import bigfight.combat.util.CombatRandom;
import bigfight.model.warrior.component.attr.AttackAttribute;
import bigfight.model.warrior.component.attr.DefenceAttribute;

public class AttackCalculator {
    AttackAttribute attackAttribute;
    DefenceAttribute defenceAttribute;

    public AttackCalculator(AttackAttribute attackAttribute, DefenceAttribute defenceAttribute) {
        this.attackAttribute = attackAttribute;
        this.defenceAttribute = defenceAttribute;
    }

    public int damageAttributeMultiply(int damage) {
        double extraPercentageDamage = attackAttribute.getExtraPercentageDamage() - defenceAttribute.getAntiExtraPercentageDamage();
        extraPercentageDamage = Math.max(extraPercentageDamage, 0);
        damage = (int) (damage * (1 + extraPercentageDamage));
        return damage;
    }

    public boolean isEscape(int attackerAgility, int defenderAgility, CombatRandom random) {
        double escape = defenceAttribute.getEvasionRate() - attackAttribute.getHitRate();
        escape += CombatAlgo.escapeByAgility(defenderAgility, attackerAgility);
        return random.getEscapeRandom() < escape;
    }
}
