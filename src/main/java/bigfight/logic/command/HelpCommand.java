package bigfight.logic.command;

import bigfight.model.warrior.builder.Warrior;
import bigfight.ui.Uiable;

public class HelpCommand implements Commandable{
    private String helpInfo;
    public HelpCommand(String helpInfo) {
        this.helpInfo = helpInfo;
    }

    @Override
    public void execute(Warrior warrior, Uiable ui) {
        System.out.println(helpInfo);
    }
}
