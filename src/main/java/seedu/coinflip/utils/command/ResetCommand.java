package seedu.coinflip.utils.command;

import seedu.coinflip.utils.exceptions.CoinflipException;
import seedu.coinflip.utils.logger.CoinflipLogger;
import seedu.coinflip.utils.printer.Printer;
import seedu.coinflip.utils.storage.Storage;
import seedu.coinflip.utils.userdata.UserData;


//@@author wongyihao0506

/**
 * Helper class for reset command logic:
 * resetting user data to default values and saving it.
 */
public class ResetCommand extends Command {

    private static final Integer NUMBER_OF_WORDS = 1;
    private final String[] words;
    private final Storage storage;
    private final UserData userData;

    /**
     * Constructs ResetCommand object.
     *
     * @param words    List of words in user command
     * @param userData UserData object to modify
     * @param storage  Storage helper to be used
     */
    public ResetCommand(String[] words, UserData userData, Storage storage) {
        this.words = words;
        this.userData = userData;
        this.storage = storage;
    }

    /**
     * Executes reset command logic: resetting user data to default values and saving it.
     *
     * @throws CoinflipException if command is invalid
     */
    @Override
    public void execute() throws CoinflipException {
        CoinflipLogger.info("Executing reset command");

        checkNumberOfWords(words);
        resetUserData(userData);
        Printer.printResetSuccessful();
        storage.saveData(userData);

        CoinflipLogger.info("Reset command execution completed");
    }

    private void resetUserData(UserData userData) {
        this.userData.balance = 500;
        this.userData.betAmount = 20;

        this.userData.winCount = 0;
        this.userData.loseCount = 0;
        this.userData.totalWon = 0;
        this.userData.totalLost = 0;

        this.userData.currentWinStreak = 0;
        this.userData.currentLoseStreak = 0;
        this.userData.highestWinStreak = 0;
        this.userData.highestLoseStreak = 0;
    }

    private static void checkNumberOfWords(String[] words) throws CoinflipException {
        if (words.length != NUMBER_OF_WORDS) {
            CoinflipLogger.warning("Invalid command format");
            throw new CoinflipException(CoinflipException.RESET_INVALID_FORMAT);
        }
    }

}
