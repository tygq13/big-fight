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
        roundDecision = decideFirstRound();

        CombatRandom rand = new CombatRandom();
        while(hero.getHealth() > 0 && opponent.getHealth() > 0) {
            if (heroRound(hero.getFighterFlag(), opponent.getFighterFlag())) {
                startRound(hero, opponent, rand);
            } else {
                startRound(opponent, hero, rand);
            }
            System.out.print(System.lineSeparator());
        }
        return opponent.getHealth() <= 0;
    }

    private void startRound(Fighter attackerStatus, Fighter defenderStatus, CombatRandom rand) {
        Empowerment empowerment = selectEmpowerment(attackerStatus, defenderStatus, rand);
        attackerStatus.selectAuxiliarySkill(rand);
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

    private Empowerment selectEmpowerment(Fighter attacker, Fighter defender, CombatRandom random) {
        Empowerment empowerment = attacker.selectEmpowerment(random);
        // this feature untested
        while (empowerment.getSkill() != null && empowerment.getSkill().getIdentity() == SkillIdentity.DISARM &&
            defender.getHoldingWeapon() == null) {
            empowerment = attacker.selectEmpowerment(random);
        }
        return empowerment;
    }
}
