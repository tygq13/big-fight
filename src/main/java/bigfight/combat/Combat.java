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
        int roundDecision = 0;

        CombatRandom rand = new CombatRandom();
        while(heroStatus.getHealth() != 0 || opponentStatus.getHealth() != 0) {
            if (herosRound()) {
                Empowerment empowerment = hero.SelectEmpowerment(rand);
                roundDecision += new Round(heroStatus, opponentStatus, empowerment, rand).fight();
            }
        }
        return true;
    }

    private boolean herosRound() {
        return true;
    }

}
