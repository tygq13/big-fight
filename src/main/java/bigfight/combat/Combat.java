package bigfight.combat;

import bigfight.combat.fighter.*;
import bigfight.combat.util.CombatRandom;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.warrior.component.Empowerment;
import bigfight.ui.Uiable;

// responsible for controlling the rounds of a fight
public class Combat {
    private Fighter hero;
    private Fighter opponent;
    private Uiable ui;
    private int roundDecision;

    final private int HERO_TURN = 1;
    final private int OPPONENT_TURN = 0;

    public Combat(Fighter first, Fighter second, Uiable ui) {
        hero = first;
        opponent = second;
        this.ui = ui;
    }

    public boolean start() {
        FighterStatus heroStatus = new FighterStatus(hero);
        FighterStatus opponentStatus = new FighterStatus(opponent);
        roundDecision = decideFirstRound();

        CombatRandom rand = new CombatRandom();
        while(heroStatus.getHealth() > 0 && opponentStatus.getHealth() > 0) {
            if (heroRound(heroStatus.getFighterFlag(), opponentStatus.getFighterFlag())) {
                startRound(heroStatus, opponentStatus, rand, hero);
            } else {
                startRound(opponentStatus, heroStatus, rand, opponent);
            }
            System.out.print(System.lineSeparator());
        }
        return opponentStatus.getHealth() <= 0;
    }

    private void startRound(FighterStatus attackerStatus, FighterStatus defenderStatus, CombatRandom rand, Fighter attacker) {
        Empowerment empowerment = selectEmpowerment(attacker, defenderStatus, rand);
        attacker.selectAuxiliarySkill(attackerStatus.getFighterFlag(), rand);
        new Round(attackerStatus, defenderStatus, empowerment, rand, ui).fight();
    }

    private int decideFirstRound() {
        return hero.getSpeed() >= opponent.getSpeed() ? 1 : 0;
    }

    private boolean heroRound(FighterFlag heroFlag, FighterFlag opponentFlag) {
        // decide by ignore
        if (heroFlag.ignored - opponentFlag.ignored > 0) {
            heroFlag.ignored -= 1;
            return true;
        } else if (heroFlag.ignored - opponentFlag.ignored < 0) {
            opponentFlag.ignored -= 1;
            return false;
        } else {
            // decide by normal round turns
            if (roundDecision == HERO_TURN) {
                roundDecision = OPPONENT_TURN;
                return true;
            } else {
                roundDecision = HERO_TURN;
                return false;
            }
        }
    }

    private Empowerment selectEmpowerment(Fighter hero, FighterStatus opponent, CombatRandom random) {
        Empowerment empowerment = hero.selectEmpowerment(random);
        // this feature untested
        while (empowerment.getSkill() != null && empowerment.getSkill().getIdentity() == SkillIdentity.DISARM &&
            opponent.getHoldingWeapon() == null) {
            empowerment = hero.selectEmpowerment(random);
        }
        return empowerment;
    }
}
