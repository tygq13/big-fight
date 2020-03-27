package bigfight.command;

import bigfight.combat.Combat;
import bigfight.combat.fighter.Fighter;
import bigfight.ui.Uiable;

public class FightCommand implements Commandable{
    private Fighter fighter1;
    private Fighter fighter2;
    private Uiable ui;

    public FightCommand(Fighter fighter1, Fighter fighter2, Uiable ui) {
        this.fighter1 = fighter1;
        this.fighter2 = fighter2;
        this.ui = ui;
    }

    @Override
    public void execute() {
        Combat combat = new Combat(fighter1, fighter2, ui);
        boolean isWin = combat.start();
        if (Boolean.TRUE.equals(isWin)) {
            System.out.println(String.format("%s has won.", fighter1.getName()));
        } else {
            System.out.println(String.format("%s has won.", fighter2.getName()));
        }
    }
}
