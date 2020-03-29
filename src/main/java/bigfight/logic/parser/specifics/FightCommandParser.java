package bigfight.logic.parser.specifics;

import bigfight.logic.command.FightCommand;

public class FightCommandParser implements CommandParser {

    @Override
    public FightCommand parse(String[] words) {
        int index = Integer.parseInt(words[1]);
        return new FightCommand(index);
    }
}
