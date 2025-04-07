package seedu.coinflip.utils.command;

import org.junit.jupiter.api.Test;
import seedu.coinflip.utils.exceptions.CoinflipException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HelpCommandTest {

    @Test
    public void executeValidCommandTest() {
        String[] commandWords = {"help"};
        HelpCommand command = new HelpCommand(commandWords);

        try {
            // This should not throw an exception
            command.execute();
            // If we get here, the test passes
            assertTrue(true);
        } catch (CoinflipException e) {
            assert false : e.message;
        }
    }

    @Test
    public void executeTooManyWordsTest() {
        String[] commandWords = {"help", "me"};
        HelpCommand command = new HelpCommand(commandWords);

        // Should throw an exception due to invalid format
        assertThrows(CoinflipException.class, () -> command.execute());
    }

    @Test
    public void executeSpecificExceptionMessageTest() {
        String[] commandWords = {"help", "please"};
        HelpCommand command = new HelpCommand(commandWords);

        try {
            command.execute();
            assert false : "Should have thrown an exception";
        } catch (CoinflipException e) {
            // Verify that the correct exception message is set
            assertTrue(e.message.equals(CoinflipException.HELP_INVALID_FORMAT),
                    "Expected exception message to be HELP_INVALID_FORMAT");
        }
    }

    @Test
    public void executeEmptyCommandArrayTest() {
        String[] commandWords = {};
        HelpCommand command = new HelpCommand(commandWords);

        // Should throw an exception due to array index out of bounds or similar
        assertThrows(Exception.class, () -> command.execute());
    }

    @Test
    public void executeNullCommandArrayTest() {
        // This is an edge case - passing null as command words
        HelpCommand command = new HelpCommand(null);

        // Should throw a NullPointerException or similar
        assertThrows(Exception.class, () -> command.execute());
    }
}
