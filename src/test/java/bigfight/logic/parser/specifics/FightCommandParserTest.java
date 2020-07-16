package bigfight.logic.parser.specifics;

import bigfight.logic.command.FightCommand;
import bigfight.logic.command.HelpCommand;
import bigfight.logic.parser.exception.ParserException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class FightCommandParserTest {

    @Test
    void test_standard_parse_successful() throws ParserException {
        final String[] WORDS = {"fight", "0"};
        FightCommand result = new FightCommandParser().parse(WORDS);
        assertNotNull(result);
    }

    @Test
    void test_throw_exception_with_bad_opponent_index() {
        final String[] WORDS = {"fight", "bad input"};
        assertThrows(ParserException.class, () -> new FightCommandParser().parse(WORDS));
    }
}
