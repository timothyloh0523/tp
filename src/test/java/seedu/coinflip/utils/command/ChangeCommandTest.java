package seedu.coinflip.utils.command;

import org.junit.jupiter.api.Test;
import seedu.coinflip.utils.exceptions.CoinflipException;
import seedu.coinflip.utils.storage.Storage;
import seedu.coinflip.utils.userdata.UserData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChangeCommandTest {

    @Test
    public void executeValidChangeTest() {
        String[] commandWords = {"change", "50"};
        UserData userData = new UserData();
        Storage storage = new Storage();

        // Initial bet amount should be 20
        assertEquals(20, userData.betAmount);

        ChangeCommand command = new ChangeCommand(commandWords, userData, storage);

        try {
            command.execute();

            // Check that bet amount was changed to 50
            assertEquals(50, userData.betAmount);

        } catch (CoinflipException e) {
            assert false : e.message;
        }
    }

    @Test
    public void executeTooManyWordsTest() {
        String[] commandWords = {"change", "50", "coins"};
        UserData userData = new UserData();
        Storage storage = new Storage();

        ChangeCommand command = new ChangeCommand(commandWords, userData, storage);

        // Should throw an exception due to invalid format
        assertThrows(CoinflipException.class, () -> command.execute());
    }

    @Test
    public void executeTooFewWordsTest() {
        String[] commandWords = {"change"};
        UserData userData = new UserData();
        Storage storage = new Storage();

        ChangeCommand command = new ChangeCommand(commandWords, userData, storage);

        // Should throw an exception due to invalid format
        assertThrows(CoinflipException.class, () -> command.execute());
    }

    @Test
    public void executeNonNumericalInputTest() {
        String[] commandWords = {"change", "fifty"};
        UserData userData = new UserData();
        Storage storage = new Storage();

        ChangeCommand command = new ChangeCommand(commandWords, userData, storage);

        try {
            command.execute();
            assert false : "Should have thrown an exception";
        } catch (CoinflipException e) {
            // Verify that the correct exception message is set
            assertTrue(e.message.equals(CoinflipException.CHANGE_BET_AMOUNT_INVALID),
                    "Expected exception message to be CHANGE_BET_AMOUNT_INVALID");
        }
    }

    @Test
    public void executeNegativeAmountTest() {
        String[] commandWords = {"change", "-10"};
        UserData userData = new UserData();
        Storage storage = new Storage();

        ChangeCommand command = new ChangeCommand(commandWords, userData, storage);

        // Should throw an exception due to negative value
        assertThrows(CoinflipException.class, () -> command.execute());
    }

    @Test
    public void executeAmountExceedsBalanceTest() {
        String[] commandWords = {"change", "600"};  // Default balance is 500
        UserData userData = new UserData();
        Storage storage = new Storage();

        ChangeCommand command = new ChangeCommand(commandWords, userData, storage);

        try {
            command.execute();
            assert false : "Should have thrown an exception";
        } catch (CoinflipException e) {
            // Verify that the correct exception message is set
            assertTrue(e.message.equals(CoinflipException.CHANGE_BET_AMOUNT_EXCEEDS_BALANCE),
                    "Expected exception message to be CHANGE_BET_AMOUNT_EXCEEDS_BALANCE");
        }
    }

    @Test
    public void executeAmountTooLargeTest() {
        String[] commandWords = {"change", "1000000000"}; // 10 digits, exceeding 9 digit limit
        UserData userData = new UserData();
        Storage storage = new Storage();

        ChangeCommand command = new ChangeCommand(commandWords, userData, storage);

        try {
            command.execute();
            assert false : "Should have thrown an exception";
        } catch (CoinflipException e) {
            // Verify that the correct exception message is set
            assertTrue(e.message.equals(CoinflipException.CHANGE_BET_AMOUNT_TOO_LARGE),
                    "Expected exception message to be CHANGE_BET_AMOUNT_TOO_LARGE");
        }
    }

    @Test
    public void executeZeroAmountTest() {
        String[] commandWords = {"change", "0"};
        UserData userData = new UserData();
        Storage storage = new Storage();

        ChangeCommand command = new ChangeCommand(commandWords, userData, storage);

        try {
            command.execute();

            // Check that bet amount was changed to 0
            assertEquals(0, userData.betAmount);

        } catch (CoinflipException e) {
            assert false : e.message;
        }
    }
}
