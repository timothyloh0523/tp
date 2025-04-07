package seedu.coinflip.utils.command;

import org.junit.jupiter.api.Test;
import seedu.coinflip.utils.exceptions.CoinflipException;
import seedu.coinflip.utils.storage.Storage;
import seedu.coinflip.utils.userdata.UserData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ResetCommandTest {

    @Test
    public void executeValidResetTest() {
        String[] commandWords = {"reset"};
        UserData userData = new UserData();
        Storage storage = new Storage();

        // Modify userData to have non-default values
        userData.balance = 100;
        userData.betAmount = 50;
        userData.winCount = 10;
        userData.loseCount = 5;
        userData.totalWon = 500;
        userData.totalLost = 250;
        userData.currentWinStreak = 3;
        userData.currentLoseStreak = 0;
        userData.highestWinStreak = 5;
        userData.highestLoseStreak = 4;

        ResetCommand command = new ResetCommand(commandWords, userData, storage);

        try {
            command.execute();

            // Check that userData was reset to default values
            assertEquals(500, userData.balance);
            assertEquals(20, userData.betAmount);
            assertEquals(0, userData.winCount);
            assertEquals(0, userData.loseCount);
            assertEquals(0, userData.totalWon);
            assertEquals(0, userData.totalLost);
            assertEquals(0, userData.currentWinStreak);
            assertEquals(0, userData.currentLoseStreak);
            assertEquals(0, userData.highestWinStreak);
            assertEquals(0, userData.highestLoseStreak);

        } catch (CoinflipException e) {
            assert false : e.message;
        }
    }

    @Test
    public void executeTooManyWordsTest() {
        String[] commandWords = {"reset", "game"};
        UserData userData = new UserData();
        Storage storage = new Storage();

        ResetCommand command = new ResetCommand(commandWords, userData, storage);

        // Should throw an exception due to invalid format
        assertThrows(CoinflipException.class, () -> command.execute());
    }

    @Test
    public void executeSpecificExceptionMessageTest() {
        String[] commandWords = {"reset", "all"};
        UserData userData = new UserData();
        Storage storage = new Storage();

        ResetCommand command = new ResetCommand(commandWords, userData, storage);

        try {
            command.execute();
            assert false : "Should have thrown an exception";
        } catch (CoinflipException e) {
            // Verify that the correct exception message is set
            assertTrue(e.message.equals(CoinflipException.RESET_INVALID_FORMAT),
                    "Expected exception message to be RESET_INVALID_FORMAT");
        }
    }
}
