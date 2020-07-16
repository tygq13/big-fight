package bigfight.logic.parser;

import bigfight.logic.command.Commandable;
import bigfight.logic.command.HelpCommand;
import bigfight.logic.parser.exception.ParserException;
import bigfight.logic.parser.exception.ParserMessage;
import bigfight.logic.parser.specifics.FightCommandParser;

public class Parser {

    public static Commandable parse(String input) throws ParserException {
        String[] words = input.split(" ");
        String command = words[0];
        command = command.trim().toLowerCase();
        boolean isHelp = Boolean.TRUE.equals(hasHelp(words));
        if (isHelp) {
            return new HelpCommand(command);
        }
        switch (command) {
            case "fight":
                return new FightCommandParser().parse(words);
            default:
                throw new ParserException(ParserMessage.COMMAND_NOT_EXIST);
        }
    }

    private static boolean hasHelp(String[] words) {
        if (words[0].trim().toLowerCase().equals("help")) {
            return true;
        }
        for (String word : words) {
            if (word.equals("-h")) {
                return true;
            }
        }
        return false;
    }
}
