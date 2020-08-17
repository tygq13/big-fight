package bigfight.combat;

import bigfight.combat.fighter.*;
import bigfight.combat.fighter.components.FighterFlag;
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

    private void startRound(Fighter attacker, Fighter defender, CombatRandom rand) {
        attacker.getCombatSelector().selectHealingSkill(rand, attacker.getHealthObj(), attacker.getLevel());
        Empowerment empowerment = selectEmpowerment(attacker, defender, rand);
        // bad implementation, find a better way to change this
        if (!attacker.getFighterFlag().ignoredByUnselection) {
            new Round(attacker, defender, empowerment, rand, ui).fight();
        }
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
        Empowerment empowerment = attacker.getCombatSelector().selectEmpowerment(random);
        // this feature untested
        while (empowerment.getSkill() != null && empowerment.getSkill().getIdentity() == SkillIdentity.DISARM &&
            defender.getHoldingWeapon() == null) {
            empowerment = attacker.getCombatSelector().selectEmpowerment(random);
        }
        return empowerment;
    }
}
