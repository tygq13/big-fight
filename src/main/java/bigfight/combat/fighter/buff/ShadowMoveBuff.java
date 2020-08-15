package bigfight.combat.fighter.buff;

import bigfight.combat.fighter.Fighter;
import bigfight.model.skill.skills.special.ShadowMove;
import bigfight.model.warrior.component.AdvancedAttribute;
import bigfight.model.warrior.component.BasicAttribute;

public class ShadowMoveBuff implements Buff {
    private int rounds;
    private int initialRound;
    private double speedMultiply;
    private double damageMultiply;

    public ShadowMoveBuff(ShadowMove shadowMove) {
        this.speedMultiply = shadowMove.getSpeedMultiply();
        this.damageMultiply = shadowMove.getDamageMultiply();
        this.rounds = shadowMove.getMaxRound();
        initialRound = rounds;
    }

    @Override
    public void invoke(Fighter fighter) {
        if (rounds == initialRound) {
            BasicAttribute speed = fighter.getSpeedObj();
            AdvancedAttribute advancedAttribute = fighter.getAdvancedAttribute();
            speed.assignBase((int) (speed.getBase() * (1 + speedMultiply)));
            advancedAttribute.bigExtraPercentageDamage += damageMultiply;
            advancedAttribute.mediumExtraPercentageDamage += damageMultiply;
            advancedAttribute.smallExtraPercentageDamage += damageMultiply;
            advancedAttribute.throwExtraPercentageDamage += damageMultiply;
            advancedAttribute.unarmedExtraPercentageDamage += damageMultiply;
            advancedAttribute.skillExtraPercentageDamage += damageMultiply;
        } else if (rounds == 0){
            BasicAttribute speed = fighter.getSpeedObj();
            AdvancedAttribute advancedAttribute = fighter.getAdvancedAttribute();
            speed.assignBase((int) Math.round(speed.getBase() / (1 + speedMultiply)));
            advancedAttribute.bigExtraPercentageDamage -= damageMultiply;
            advancedAttribute.mediumExtraPercentageDamage -= damageMultiply;
            advancedAttribute.smallExtraPercentageDamage -= damageMultiply;
            advancedAttribute.throwExtraPercentageDamage -= damageMultiply;
            advancedAttribute.unarmedExtraPercentageDamage -= damageMultiply;
            advancedAttribute.skillExtraPercentageDamage -= damageMultiply;
        }
        rounds -= 1;
    }

    @Override
    public boolean isEnd() {
        return rounds < 0;
    }
}
