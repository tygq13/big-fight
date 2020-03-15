package bigfight.command;

import bigfight.combat.Combat;
import bigfight.combat.fighter.Fighter;

public class FightCommand {
    private Fighter fighter1;
    private Fighter fighter2;
    public FightCommand(Fighter fighter1, Fighter fighter2) {
        this.fighter1 = fighter1;
        this.fighter2 = fighter2;
    }

    public void execute() {
        Combat combat = new Combat(fighter1, fighter2);
        boolean isWin = combat.start();
        if (Boolean.TRUE.equals(isWin)) {
            System.out.println(String.format("%s has won.", fighter1.getName()));
        } else {
            System.out.println(String.format("%s has won.", fighter2.getName()));
        }
    }
}
