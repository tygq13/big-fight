package bigfight.combat;

import bigfight.combat.fighter.*;
import bigfight.combat.util.CombatRandom;
import bigfight.model.warrior.component.Empowerment;

import java.util.Random;

public class Combat {
    private Fighter hero;
    private Fighter opponent;

    public Combat(Fighter first, Fighter second) {
        hero = first;
        opponent = second;
    }

    public boolean start() {
        FighterStatus heroStatus = new FighterStatus(hero);
        FighterStatus opponentStatus = new FighterStatus(opponent);
        int roundDecision = decideFirstRound();

        CombatRandom rand = new CombatRandom();
        while(heroStatus.getHealth() > 0 && opponentStatus.getHealth() > 0) {
            if (heroRound(roundDecision, rand)) {
                Empowerment empowerment = hero.SelectEmpowerment(rand);
                roundDecision += new Round(heroStatus, opponentStatus, empowerment, rand).fight();
            } else {
                Empowerment empowerment = opponent.SelectEmpowerment(rand);
                roundDecision += new Round(opponentStatus, heroStatus, empowerment, rand).fight();
            }
            roundDecision = nextRound(roundDecision);
        }
        return opponentStatus.getHealth() <= 0;
    }

    private int decideFirstRound() {
        return hero.getSpeed() >= opponent.getSpeed() ? 1 : 0;
    }

    private boolean heroRound(int roundDecision, CombatRandom rand) {
        if (roundDecision > 0) {
            return true;
        } else if (roundDecision < 0) {
            return false;
        } else {
            // 50-50 chance for both sides
            return rand.getRoundRandom() < 0.5;
        }
    }

    private int nextRound(int roundDecision) {
        if (roundDecision > 0) {
            return roundDecision - 1;
        } else if (roundDecision < 0) {
            return roundDecision + 1;
        } else {
            return roundDecision;
        }
    }
}
