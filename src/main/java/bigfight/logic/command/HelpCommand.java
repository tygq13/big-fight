package bigfight.logic.command;

import bigfight.model.warrior.builder.Warrior;
import bigfight.ui.Uiable;

public class HelpCommand implements Commandable{
    public static final String FIGHT_HELP =
            "Fight [INDEX]    <INDEX is the friends index in the command 'view friends'>";

    // ALL_HELP is less detailed
    public static final String ALL_HELP =
            "Fight [INDEX]    <INDEX is the friends index in the command 'view friends'>";

    private String commandToHelp;
    public HelpCommand(String commandToHelp) {
        this.commandToHelp = commandToHelp;
    }

    @Override
    public void execute(Warrior warrior, Uiable ui) {
        switch (commandToHelp) {
            case "fight":
                System.out.println(FIGHT_HELP);
                break;
            default:
                System.out.println(ALL_HELP);
        }
    }
}
