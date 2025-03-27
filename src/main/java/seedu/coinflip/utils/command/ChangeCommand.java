package seedu.coinflip.utils.command;

import seedu.coinflip.utils.exceptions.CoinflipException;
import seedu.coinflip.utils.logger.CoinflipLogger;
import seedu.coinflip.utils.printer.Printer;
import seedu.coinflip.utils.storage.Storage;
import seedu.coinflip.utils.userdata.UserData;

/**
 * Handles the command to change the bet amount.
 */
public class ChangeCommand extends Command {
    private final String[] words;
    private final Storage storage;
    private final UserData userData;

    public ChangeCommand(String[] words, UserData userData, Storage storage) {
        this.words = words;
        this.userData = userData;
        this.storage = storage;
    }

    @Override
    public void execute() throws CoinflipException {
        CoinflipLogger.info("Executing change command");
        this.change(words);
    }

    //@@author OliverQiL
    private void change(String[] words) throws CoinflipException {

        if (words.length < 2) {
            CoinflipLogger.warning("Invalid bet command format: too few words");
            throw new CoinflipException(CoinflipException.BET_AMOUNT_INVALID_FORMAT);
        }

        if (words.length > 2) {
            CoinflipLogger.warning("Invalid bet command format: too many words");
            throw new CoinflipException(CoinflipException.BET_AMOUNT_INVALID_FORMAT);
        }

        if (!words[1].matches("[0-9]+")) {
            CoinflipLogger.warning("Invalid bet amount: non-numerical");
            throw new CoinflipException(CoinflipException.BET_AMOUNT_INVALID_FORMAT);
        }

        userData.betAmount = Integer.parseInt(words[1]);

        if (userData.betAmount < 0) {
            CoinflipLogger.warning("Invalid bet amount: negative");
            throw new CoinflipException(CoinflipException.BET_AMOUNT_INVALID_FORMAT);
        }

        if (userData.betAmount > userData.balance) {
            CoinflipLogger.warning("Bet amount exceeded balance");
            Printer.printNotEnoughCoins();
            return;
        }

        Printer.printBetAmount(userData.betAmount);

        storage.saveData(userData);
    }
}
