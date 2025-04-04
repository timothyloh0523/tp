package seedu.coinflip.utils.command;

import seedu.coinflip.utils.exceptions.CoinflipException;
import seedu.coinflip.utils.logger.CoinflipLogger;
import seedu.coinflip.utils.printer.Printer;
import seedu.coinflip.utils.storage.Storage;
import seedu.coinflip.utils.userdata.UserData;

import java.util.Random;

/**
 * Handles the command to flip a coin, given a certain bet amount.
 */
public class FlipCommand extends Command {
    private static final Integer NUMBER_OF_WORDS = 2;
    private final String[] words;
    private final Storage storage;
    private final UserData userData;

    public FlipCommand(String[] words, UserData userData, Storage storage) {
        this.words = words;
        this.userData = userData;
        this.storage = storage;
    }

    @Override
    public void execute() throws CoinflipException {
        CoinflipLogger.info("Executing flip command");
        this.flip();
        storage.saveData(userData);
        CoinflipLogger.info("Finished executing flip command");
    }

    //@@author wongyihao0506

    /**
     * Simulates flipping a coin and determines the outcome based on the user's choice.
     * <p>
     * This method validates the input, checks if the user has sufficient balance to place a bet,
     * and then performs a coin flip to determine the result. If the user wins, their balance is
     * increased by the bet amount; otherwise, it is decreased. The method also updates the user's
     * win/loss counts and total won/lost.
     * </p>
     *
     * @throws CoinflipException If the input command format is invalid or the flip choice is not "heads" or "tails".
     */
    public void flip() throws CoinflipException {
        CoinflipLogger.info("User attempting to flip a coin");

        checkNumberOfWords(words);
        checkValidOutcomeWord(words[1]);
        checkWithinBalance(userData.betAmount, userData.balance);

        String actualFlip = generateFlip();
        Boolean outcome = getOutcome(actualFlip, words[1]);
        processOutcome(outcome);

        Printer.printFlipOutcome(actualFlip, outcome, userData.betAmount);
        Printer.printFlipSummary(userData);

        assert userData.balance >= 0 : "balance should be more than or equal to 0";
    }

    private void processOutcome(Boolean outcome) {
        if (outcome) {
            updateUserWon();
        } else {
            updateUserLost();
        }
    }

    private void updateUserWon() {
        userData.balance += userData.betAmount;
        increaseWinCount();
        increaseTotalWon(userData.betAmount);
        increaseWinStreak();
        resetLoseStreak();
        CoinflipLogger.info("User won " +
                userData.betAmount +
                " coins. New balance: " +
                userData.balance +
                " coins. Current win streak:" +
                userData.winStreak + ".");
    }

    private void updateUserLost() {
        userData.balance -= userData.betAmount;
        increaseLoseCount();
        increaseTotalLost(userData.betAmount);
        increaseLoseStreak();
        resetWinStreak();
        CoinflipLogger.info("User lost " +
                userData.betAmount +
                " coins. New balance: " +
                userData.balance +
                " coins. Current loss streak:" +
                userData.winStreak + ".");
    }

    private static String generateFlip() {
        Random random = new Random();
        return random.nextBoolean() ? "Heads" : "Tails";
    }

    private static Boolean getOutcome(String actualFlip, String desiredFlip) {
        return actualFlip.equalsIgnoreCase(desiredFlip);
    }

    private static void checkNumberOfWords(String[] words) throws CoinflipException {
        if (words.length != NUMBER_OF_WORDS) {
            CoinflipLogger.warning("Invalid flip command format");
            throw new CoinflipException(CoinflipException.FLIP_INVALID_FORMAT);
        }
    }

    private static void checkValidOutcomeWord(String input) throws CoinflipException {
        if (!input.equals("heads") && !input.equals("tails")) {
            CoinflipLogger.warning("Invalid flip choice: " + input);
            throw new CoinflipException(CoinflipException.FLIP_INVALID_FORMAT);
        }
    }

    private static void checkWithinBalance(Integer betAmount, Integer balance) throws CoinflipException {
        if (betAmount > balance) {
            CoinflipLogger.warning("Bet amount exceeds balance: bet = " +
                    betAmount +
                    ", balance = " +
                    balance);
            throw new CoinflipException(CoinflipException.FLIP_BET_AMOUNT_EXCEEDS_BALANCE);
        }
    }

    //@@author CRL006
    private void increaseWinCount() {
        userData.winCount += 1;
    }

    private void increaseLoseCount() {
        userData.loseCount += 1;
    }

    private void increaseWinStreak() {
        userData.winStreak += 1;
    }

    private void increaseLoseStreak() {
        userData.loseStreak += 1;
    }

    private void resetWinStreak() {
        userData.winStreak = 0;
    }

    private void resetLoseStreak() {
        userData.loseStreak = 0;
    }

    private void increaseTotalWon(int earnings) {
        userData.totalWon += earnings;
    }

    private void increaseTotalLost(int losses) {
        userData.totalLost += losses;
    }
}
