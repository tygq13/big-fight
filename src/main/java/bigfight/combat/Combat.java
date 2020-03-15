package bigfight.combat;

import bigfight.combat.fighter.*;
import bigfight.combat.util.CombatRandom;
import bigfight.model.warrior.component.Empowerment;

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
                roundDecision -= 1;
                Empowerment empowerment = hero.SelectEmpowerment(rand);
                roundDecision += new Round(heroStatus, opponentStatus, empowerment, rand).fight();
                if (roundDecision == 0) {
                    // no ignore from the hero's side
                    roundDecision -= 1;
                }
            } else {
                roundDecision += 1;
                Empowerment empowerment = opponent.SelectEmpowerment(rand);
                roundDecision -= new Round(opponentStatus, heroStatus, empowerment, rand).fight();
                if (roundDecision == 0) {
                    // no ignore from the hero's side
                    roundDecision += 1;
                }
            }
            System.out.print(System.lineSeparator());
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
}
