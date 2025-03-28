package seedu.coinflip.utils.command;

import org.junit.jupiter.api.Test;
import seedu.coinflip.utils.userdata.UserData;
import seedu.coinflip.utils.exceptions.CoinflipException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FlipCommandTest {
    //@@author HTY2003
    @Test
    public void singleFlipTest() {
        String[] commandWords = {"flip", "heads"};
        UserData userData = new UserData();
        FlipCommand command = new FlipCommand(commandWords, userData, null);
        try {
            command.flip();
            assertEquals(20, userData.betAmount);
            assertEquals(20, userData.totalWinnings + userData.totalLosings);
            assertEquals(1, userData.winCount + userData.loseCount);
            assertTrue(userData.balance == 480 || userData.balance == 520);
        } catch (CoinflipException e) {
            assert false : e.message;
        }
    }

    //@@author HTY2003
    @Test
    public void multipleFlipsTest() {
        String[] commandWords = {"flip", "heads"};
        UserData userData = new UserData();
        FlipCommand command = new FlipCommand(commandWords, userData, null);
        try {
            for (int i = 0; i < 10; i++) {
                command.flip();
            }

            assertEquals(20, userData.betAmount);
            assertEquals(10, userData.winCount + userData.loseCount);

        } catch (CoinflipException e) {
            assert false : e.message;
        }
    }
}
