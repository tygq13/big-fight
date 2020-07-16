package bigfight.logic.command;

import bigfight.combat.Combat;
import bigfight.combat.fighter.Fighter;
import bigfight.model.warrior.builder.Warrior;
import bigfight.model.warrior.database.DatabaseAccessor;
import bigfight.ui.Uiable;

public class FightCommand implements Commandable{
    private int friendIndex;


    public FightCommand(int index) {
        friendIndex = index;
    }

    @Override
    public void execute(Warrior warrior, Uiable ui) {
        DatabaseAccessor accessor = warrior.getDatabaseAccessor();
        Warrior opponent = accessor.find(warrior.getFriend(friendIndex));
        Combat combat = new Combat(new Fighter(warrior), new Fighter(opponent), ui);
        boolean isWin = combat.start();
        if (Boolean.TRUE.equals(isWin)) {
            ui.printWin();
        } else {
            ui.printLose();
        }
    }
}
