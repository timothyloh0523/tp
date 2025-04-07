package seedu.coinflip.utils.command;

import org.junit.jupiter.api.Test;

import seedu.coinflip.utils.userdata.UserData;
import seedu.coinflip.utils.exceptions.CoinflipException;
import seedu.coinflip.utils.printer.Printer;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;
import org.mockito.MockedStatic;

public class CheckCommandTest {
    @Test
    public void checkBalanceTest() {
        String[] commandWords = {"check", "balance"};
        UserData userData = new UserData();
        CheckCommand command = new CheckCommand(commandWords, userData);

        try (MockedStatic<Printer> printerMock = mockStatic(Printer.class)){

            command.execute();
            printerMock.verify(() -> Printer.printBalance(userData.balance));

        } catch (CoinflipException e) {
            assert false: e.message;
        }
    }

    @Test
    public void checkBetTest() {
        String[] commandWords = {"check", "bet"};
        UserData userData = new UserData();
        CheckCommand command = new CheckCommand(commandWords, userData);

        try (MockedStatic<Printer> printerMock = mockStatic(Printer.class)){

            command.execute();
            printerMock.verify(() -> Printer.printBetAmount(userData.betAmount));

        } catch (CoinflipException e) {
            assert false: e.message;
        }
    }

    @Test
    public void checkHistory() {
        String[] commandWords = {"check", "history"};
        UserData userData = new UserData();
        CheckCommand command = new CheckCommand(commandWords, userData);

        try (MockedStatic<Printer> printerMock = mockStatic(Printer.class)){

            command.execute();
            printerMock.verify(() -> Printer.printStats(userData));

        } catch (CoinflipException e) {
            assert false: e.message;
        }
    }

    @Test
    public void checkWrongNumberOfWordsTest() {
        String[] commandWords = {"check", "too", "many", "words"};
        UserData userData = new UserData();
        CheckCommand command = new CheckCommand(commandWords, userData);
        assertThrows(CoinflipException.class, () -> command.execute());
    }

    @Test
    public void checkInvalidCommandTest() {
        String[] commandWords = {"check", "invalidWord"};
        UserData userData = new UserData();
        CheckCommand command = new CheckCommand(commandWords, userData);
        assertThrows(CoinflipException.class, () -> command.execute());
    }
}
