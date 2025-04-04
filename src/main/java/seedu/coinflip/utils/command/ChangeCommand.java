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

    private static final Integer NUMBER_OF_WORDS = 2;
    private final String[] words;
    private final Storage storage;
    private final UserData userData;

    //@@author timothyloh0523
    public ChangeCommand(String[] words, UserData userData, Storage storage) {
        this.words = words;
        this.userData = userData;
        this.storage = storage;
    }

    //@@author timothyloh0523
    @Override
    public void execute() throws CoinflipException {
        CoinflipLogger.info("Executing change command");
        this.change();
        storage.saveData(userData);
        CoinflipLogger.info("Finished executing change command");
    }

    //@@author OliverQiL
    public void change() throws CoinflipException {

        checkNumberOfWords(this.words);
        checkNumerical(this.words[1]);
        checkCanBeInteger(this.words[1]);

        Integer betAmount = Integer.parseInt(this.words[1]);

        checkNonNegative(betAmount);
        checkWithinBalance(betAmount, userData.balance);

        userData.betAmount = betAmount;
        Printer.printBetAmount(userData.betAmount);
    }

    //@@author OliverQiL
    private static void checkNumberOfWords(String[] words) throws CoinflipException {
        if (words.length != NUMBER_OF_WORDS) {
            CoinflipLogger.warning("Invalid command format");
            throw new CoinflipException(CoinflipException.CHANGE_INVALID_FORMAT);
        }
    }

    //@@author OliverQiL
    private static void checkNumerical(String input) throws CoinflipException {
        if (!input.matches("[0-9]+")) {
            CoinflipLogger.warning("Invalid bet amount: non-numerical");
            throw new CoinflipException(CoinflipException.CHANGE_BET_AMOUNT_INVALID);
        }
    }

    //@@author HTY2003
    private static void checkCanBeInteger(String input) throws CoinflipException {
        if (input.length() > 9) {
            CoinflipLogger.warning("Invalid bet amount: number too long");
            throw new CoinflipException(CoinflipException.CHANGE_BET_AMOUNT_TOO_LARGE);
        }
    }

    //@@author OliverQiL
    private static void checkNonNegative(Integer betAmount) throws CoinflipException {
        if (betAmount < 0) {
            CoinflipLogger.warning("Invalid bet amount: negative");
            throw new CoinflipException(CoinflipException.CHANGE_BET_AMOUNT_INVALID);
        }
    }

    //@@author OliverQiL
    private static void checkWithinBalance(Integer betAmount, Integer balance) throws CoinflipException {
        if (betAmount > balance) {
            CoinflipLogger.warning("Invalid bet amount: bet amount exceeds balance");
            throw new CoinflipException(CoinflipException.CHANGE_BET_AMOUNT_EXCEEDS_BALANCE);
        }
    }
}
