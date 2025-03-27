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
    private final String[] words;
    private final UserData userData;

    public CheckCommand(String[] words, UserData userData) {
        this.words = words;
        this.userData = userData;
    }

    @Override
    public void execute() throws CoinflipException {
        CoinflipLogger.info("Executing check command");
        this.check(words);
    }

    //@@author timothyloh0523

    /**
     * Checks the user's existing balance or bet amount
     * depending on the second word in the user's response.
     * Prints balance or bet accordingly.
     *
     * @param words Words from the user's response
     * @throws CoinflipException if the user gives an invalid command
     */
    private void check(String[] words) throws CoinflipException {
        if (words.length != 2) {
            CoinflipLogger.warning("Invalid check command format");
            throw new CoinflipException(CoinflipException.CHECK_INVALID_FORMAT);
        }

        if (!words[1].equals("balance") && !words[1].equals("bet")) {
            CoinflipLogger.warning("Invalid check command format");
            throw new CoinflipException(CoinflipException.CHECK_INVALID_FORMAT);
        }

        if (words[1].equals("balance")) {
            CoinflipLogger.info("User checked balance = " + userData.balance);
            Printer.printBalance(userData.balance);
        } else {
            CoinflipLogger.info("User checked bet amount = " + userData.betAmount);
            Printer.printBetAmount(userData.betAmount);
        }
    }
}
