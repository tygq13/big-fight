package bigfight.logic.parser.specifics;

import bigfight.logic.command.Commandable;

public interface CommandParser {

    Commandable parse(String[] words);
}
