package bigfight.logic.parser.specifics;

import bigfight.logic.command.Commandable;
import bigfight.logic.parser.exception.ParserException;

public interface CommandParser {

    Commandable parse(String[] words) throws ParserException;
}
