package bigfight.model.skill.skills.special;

import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.struct.SkillStruct;
import bigfight.model.warrior.component.AdvancedAttribute;
import bigfight.model.warrior.component.BasicAttribute;

public class ShadowMoveUsable extends SkillModel {
    double speedMultiply;
    double damageMultiply;
    int maxRound;
    double invocationChance;

    public ShadowMoveUsable(SkillStruct skill, double speedMultiply, double damageMultiply, int maxRound, double invocationChance) {
        super(skill);
        this.speedMultiply = speedMultiply;
        this.damageMultiply = damageMultiply;
        this.maxRound = maxRound;
        this.invocationChance = invocationChance;
    }

    public double getInvocationChance() {
        return invocationChance;
    }

    public int getMaxRound() {
        return maxRound;
    }

    public double getSpeedMultiply() {
        return speedMultiply;
    }

    public void invoke(AdvancedAttribute advancedAttribute, BasicAttribute speed) {
        speed.assignBase((int) (speed.getBase() * (1 + speedMultiply)));
        advancedAttribute.bigExtraPercentageDamage += damageMultiply;
        advancedAttribute.mediumExtraPercentageDamage += damageMultiply;
        advancedAttribute.smallExtraPercentageDamage += damageMultiply;
        advancedAttribute.throwExtraPercentageDamage += damageMultiply;
        advancedAttribute.unarmedExtraPercentageDamage += damageMultiply;
        advancedAttribute.skillExtraPercentageDamage += damageMultiply;
    }

    public void unInvoke(AdvancedAttribute advancedAttribute, BasicAttribute speed) {
        speed.assignBase((int) (speed.getBase() / (1 + speedMultiply)));
        advancedAttribute.bigExtraPercentageDamage -= damageMultiply;
        advancedAttribute.mediumExtraPercentageDamage -= damageMultiply;
        advancedAttribute.smallExtraPercentageDamage -= damageMultiply;
        advancedAttribute.throwExtraPercentageDamage -= damageMultiply;
        advancedAttribute.unarmedExtraPercentageDamage -= damageMultiply;
        advancedAttribute.skillExtraPercentageDamage -= damageMultiply;
    }
}
