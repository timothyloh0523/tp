package seedu.coinflip.utils.command;

import org.junit.jupiter.api.Test;
import seedu.coinflip.utils.exceptions.CoinflipException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExitCommandTest {

    @Test
    public void executeValidCommandTest() {
        String[] commandWords = {"exit"};
        ExitCommand command = new ExitCommand(commandWords);

        try {
            // This should not throw an exception
            command.execute();
            // If we reach here, the test passes
            assertTrue(true);
        } catch (CoinflipException e) {
            assert false : e.message;
        }
    }

    @Test
    public void executeTooManyWordsTest() {
        String[] commandWords = {"exit", "program"};
        ExitCommand command = new ExitCommand(commandWords);

        // Should throw an exception due to invalid format
        assertThrows(CoinflipException.class, () -> command.execute());
    }

    @Test
    public void executeSpecificExceptionMessageTest() {
        String[] commandWords = {"exit", "now"};
        ExitCommand command = new ExitCommand(commandWords);

        try {
            command.execute();
            assert false : "Should have thrown an exception";
        } catch (CoinflipException e) {
            // Verify that the correct exception message is set
            assertTrue(e.message.equals(CoinflipException.EXIT_INVALID_FORMAT),
                    "Expected exception message to be EXIT_INVALID_FORMAT");
        }
    }
}
