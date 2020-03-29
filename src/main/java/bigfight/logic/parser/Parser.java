package bigfight.logic.parser;

import bigfight.logic.command.Commandable;
import bigfight.logic.parser.specifics.FightCommandParser;

public class Parser {

    public static Commandable parse(String input) {
        String[] words = input.split(" ");
        String command = words[0];
        command = command.trim().toLowerCase();
        switch (command) {
            case "fight":
                return new FightCommandParser().parse(words);
            default:
                return null;
        }
    }
}
