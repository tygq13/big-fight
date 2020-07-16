package bigfight.logic.parser.specifics;

import bigfight.logic.command.Commandable;
import bigfight.logic.command.FightCommand;
import bigfight.logic.parser.exception.ParserException;
import bigfight.logic.parser.exception.ParserMessage;

public class FightCommandParser implements CommandParser {

    private int parseIndex(String[] words) throws ParserException{
        if (words.length < 2) {
            throw new ParserException(ParserMessage.NOT_ENOUGH_ARGUMENTS);
        }
        int index;
        try {
            index = Integer.parseInt(words[1]);
        } catch (NumberFormatException e) {
            throw new ParserException(ParserMessage.INVALID_NUMBER_FORMAT);
        }
        return index;
    }

    @Override
    public FightCommand parse(String[] words) throws ParserException {
        int index = parseIndex(words);
        return new FightCommand(index);
    }
}
