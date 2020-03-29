package bigfight.logic.parser;

import bigfight.logic.command.Commandable;
import bigfight.logic.command.FightCommand;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class ParserTest {

    @Test
    void test_correct_command_returned_with_example_fight_command_parser() {
        final String input = "fight 0";
        Commandable command = Parser.parse(input);
        assertTrue(command instanceof FightCommand);
    }
}
