package bigfight.logic.parser;

import bigfight.logic.command.Commandable;
import bigfight.logic.command.FightCommand;
import bigfight.logic.command.HelpCommand;
import bigfight.logic.parser.exception.ParserException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class ParserTest {

    @Test
    void test_correct_command_returned_with_example_fight_command_parser() throws ParserException {
        final String INPUT = "fight 0";
        Commandable command = Parser.parse(INPUT);
        assertTrue(command instanceof FightCommand);
    }

    @Test
    void test_help_command_called_with_dash_h_with_example_fight_command() throws ParserException {
        final String INPUT = "fight invalid -h";
        Commandable command = Parser.parse(INPUT);
        assertTrue(command instanceof HelpCommand);
    }

    @Test
    void test_throw_exception_with_nonexistent_command() {
        final String INVALID_INPUT = "nonexistent command";
        assertThrows(ParserException.class, () -> Parser.parse(INVALID_INPUT));
    }
}
