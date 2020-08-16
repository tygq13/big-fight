package bigfight.combat.attack;

import bigfight.model.warrior.component.attr.AttackAttribute;
import bigfight.model.warrior.component.attr.DefenceAttribute;

public class AttackCalculator {

    public int damageAttributeMultiply(int damage, AttackAttribute attackAttribute, DefenceAttribute defenceAttribute) {
        double extraPercentageDamage = attackAttribute.getExtraPercentageDamage() - defenceAttribute.getAntiExtraPercentageDamage();
        extraPercentageDamage = Math.max(extraPercentageDamage, 0);
        damage = (int) (damage * (1 + extraPercentageDamage));
        return damage;
    }
}
