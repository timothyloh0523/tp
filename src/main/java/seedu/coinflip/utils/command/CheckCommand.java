package seedu.coinflip.utils.command;

import seedu.coinflip.utils.exceptions.CoinflipException;
import seedu.coinflip.utils.logger.CoinflipLogger;
import seedu.coinflip.utils.printer.Printer;
import seedu.coinflip.utils.userdata.UserData;

/**
 * Handles the command to check either the bet or balance amount,
 * depending on user input.
 */
public class CheckCommand extends Command {
    private static final Integer NUMBER_OF_WORDS = 2;
    private final String[] words;
    private final UserData userData;

    public CheckCommand(String[] words, UserData userData) {
        this.words = words;
        this.userData = userData;
    }

    //@@author timothyloh0523

    /**
     * Checks the user's existing balance or bet amount
     * depending on the second word in the user's response.
     * Prints balance, bet or flip history accordingly
     *
     * @throws CoinflipException if the user gives an invalid command
     */
    @Override
    public void execute() throws CoinflipException {
        CoinflipLogger.info("Executing check command");

        checkNumberOfWords(words);
        check(words[1]);

        CoinflipLogger.info("Finished executing check command");
    }

    private static void checkNumberOfWords(String[] words) throws CoinflipException {
        if (words.length != NUMBER_OF_WORDS) {
            CoinflipLogger.warning("Invalid check command format");
            throw new CoinflipException(CoinflipException.CHECK_INVALID_FORMAT);
        }
    }

    //@@CRL006
    private void check(String word) throws CoinflipException {
        switch (word) {
        case "balance":
            checkBalance();
            break;
        case "bet":
            checkBet();
            break;
        case "history":
            checkHistory();
            break;
        default:
            CoinflipLogger.warning("Invalid check command format");
            throw new CoinflipException(CoinflipException.CHECK_INVALID_FORMAT);
        }
    }

    private void checkBalance() {
        CoinflipLogger.info("User checked balance = " + userData.balance);
        Printer.printBalance(userData.balance);
    }

    private void checkBet() {
        CoinflipLogger.info("User checked bet amount = " + userData.betAmount);
        Printer.printBetAmount(userData.betAmount);
    }

    private void checkHistory() {
        CoinflipLogger.info("User checked stats: \nUser win count: " + userData.winCount +
                "\nUser lose count: " + userData.loseCount +
                "\nUser total won: " + userData.totalWon +
                "\nUser total lost: " + userData.totalLost);
        Printer.printStats(userData.winCount, userData.loseCount, userData.totalWon, userData.totalLost);
    }
}
