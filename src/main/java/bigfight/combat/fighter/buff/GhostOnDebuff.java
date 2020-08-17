package bigfight.combat.fighter.buff;

import bigfight.combat.fighter.Fighter;
import bigfight.model.skill.skills.GhostOn;

public class GhostOnDebuff implements Buff{
    private double reduction;
    private int rounds;
    private boolean initialRound;
    private boolean isEnd;

    public GhostOnDebuff(GhostOn ghostOn) {
        reduction = ghostOn.getReduction();
        rounds = ghostOn.getRounds();
        initialRound = true;
    }

    @Override
    public void invoke(Fighter fighter) {
        if (initialRound) {
            fighter.getAdvancedAttribute().antiBigExtraPercentageDamage -= reduction;
            fighter.getAdvancedAttribute().antiMediumExtraPercentageDamage -= reduction;
            fighter.getAdvancedAttribute().antiSmallExtraPercentageDamage -= reduction;
            fighter.getAdvancedAttribute().antiThrowExtraPercentageDamage -= reduction;
            fighter.getAdvancedAttribute().antiUnarmedExtraPercentageDamage -= reduction;
            fighter.getAdvancedAttribute().antiSkillExtraPercentageDamage -= reduction;
            fighter.getAdvancedAttribute().bigEvasionRate -= reduction;
            fighter.getAdvancedAttribute().mediumEvasionRate -= reduction;
            fighter.getAdvancedAttribute().smallEvasionRate -= reduction;
            fighter.getAdvancedAttribute().throwEvasionRate -= reduction;
            fighter.getAdvancedAttribute().unarmedEvasionRate -= reduction;
            fighter.getAdvancedAttribute().skillEvasionRate -= reduction;
            initialRound = false;
        } else if (rounds == 0) {
            fighter.getAdvancedAttribute().antiBigExtraPercentageDamage += reduction;
            fighter.getAdvancedAttribute().antiMediumExtraPercentageDamage += reduction;
            fighter.getAdvancedAttribute().antiSmallExtraPercentageDamage += reduction;
            fighter.getAdvancedAttribute().antiThrowExtraPercentageDamage += reduction;
            fighter.getAdvancedAttribute().antiUnarmedExtraPercentageDamage += reduction;
            fighter.getAdvancedAttribute().antiSkillExtraPercentageDamage += reduction;
            fighter.getAdvancedAttribute().bigEvasionRate += reduction;
            fighter.getAdvancedAttribute().mediumEvasionRate += reduction;
            fighter.getAdvancedAttribute().smallEvasionRate += reduction;
            fighter.getAdvancedAttribute().throwEvasionRate += reduction;
            fighter.getAdvancedAttribute().unarmedEvasionRate += reduction;
            fighter.getAdvancedAttribute().skillEvasionRate += reduction;
            isEnd = true;
        }
        rounds -= 1;
    }

    @Override
    public boolean isEnd() {
        return isEnd;
    }
}
