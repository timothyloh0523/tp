package seedu.coinflip.utils.command;

import seedu.coinflip.utils.achievement.AchievementList;
import seedu.coinflip.utils.exceptions.CoinflipException;
import seedu.coinflip.utils.logger.CoinflipLogger;
import seedu.coinflip.utils.printer.Printer;
import seedu.coinflip.utils.storage.Storage;
import seedu.coinflip.utils.userdata.UserData;

import java.util.Random;

/**
 * Helper class for flip command:
 * flipping a random coin, updating user data based on user command and the outcome,
 * updating achievements and saving user data.
 */
public class FlipCommand extends Command {
    private static final Integer NUMBER_OF_WORDS = 2;
    private final String[] words;
    private final Storage storage;
    private final UserData userData;
    private final AchievementList achievementList;

    /**
     * Constructs FlipCommand object.
     *
     * @param words           List of words in user command
     * @param userData        UserData object to modify and access
     * @param achievementList AchievementList object to modify and access
     * @param storage         Storage helper to be used
     */
    public FlipCommand(String[] words, UserData userData, AchievementList achievementList, Storage storage) {
        this.words = words;
        this.userData = userData;
        this.achievementList = achievementList;
        this.storage = storage;
    }

    /**
     * Executes flip command logic:
     * flipping coin, updating user data based on user command and the outcome,
     * updating achievements and saving user data.
     *
     * @throws CoinflipException if command is invalid
     */
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
     * win/loss counts and total won/lost, and updates if any new achievements have been unlocked.
     *
     * @throws CoinflipException If the input command format is invalid
     */
    public void flip() throws CoinflipException {
        CoinflipLogger.info("User attempting to flip a coin");

        checkNumberOfWords(words);
        checkValidOutcomeWord(words[1]);
        checkWithinBalance(userData.betAmount, userData.balance);

        String actualFlip = generateFlip();
        Boolean outcome = getOutcome(actualFlip, words[1]);

        achievementList.update(userData, outcome);
        processOutcome(outcome);

        Printer.printFlipOutcome(actualFlip, outcome, userData.betAmount);
        Printer.printFlipSummary(userData);
        Printer.printUnlockedAchievements(achievementList);

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

        if (userData.balance < 0 || userData.balance > 999999999) {
            userData.balance = 999999999;
        }

        increaseWinCount();
        increaseTotalWon(userData.betAmount);
        increaseCurrentWinStreak();
        updateHighestWinStreak();
        resetLoseStreak();
        CoinflipLogger.info("User won " +
                userData.betAmount +
                " coins. New balance: " +
                userData.balance +
                " coins. Current win streak:" +
                userData.currentWinStreak + ".");
    }

    private void updateUserLost() {
        userData.balance -= userData.betAmount;
        increaseLoseCount();
        increaseTotalLost(userData.betAmount);
        increaseCurrentLoseStreak();
        updateHighestLoseStreak();
        resetWinStreak();
        CoinflipLogger.info("User lost " +
                userData.betAmount +
                " coins. New balance: " +
                userData.balance +
                " coins. Current loss streak:" +
                userData.currentWinStreak + ".");
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
        if (userData.winCount < 0 || userData.winCount > 999999999) {
            userData.winCount = 999999999;
        }
    }

    private void increaseLoseCount() {
        userData.loseCount += 1;
        if (userData.loseCount < 0 || userData.loseCount > 999999999) {
            userData.loseCount = 999999999;
        }
    }

    private void increaseCurrentWinStreak() {
        userData.currentWinStreak += 1;
        if (userData.currentWinStreak < 0 || userData.currentWinStreak > 999999999) {
            userData.currentWinStreak = 999999999;
        }
    }

    private void increaseCurrentLoseStreak() {
        userData.currentLoseStreak += 1;
        if (userData.currentLoseStreak < 0 || userData.currentLoseStreak > 999999999) {
            userData.currentLoseStreak = 999999999;
        }
    }

    private void updateHighestWinStreak() {
        userData.highestWinStreak = Math.max(userData.highestWinStreak, userData.currentWinStreak);
    }

    private void updateHighestLoseStreak() {
        userData.highestLoseStreak = Math.max(userData.highestLoseStreak, userData.currentLoseStreak);
    }

    private void resetWinStreak() {
        userData.currentWinStreak = 0;
    }

    private void resetLoseStreak() {
        userData.currentLoseStreak = 0;
    }

    private void increaseTotalWon(int earnings) {
        userData.totalWon += earnings;
        if (userData.totalWon < 0 || userData.totalWon > 999999999) {
            userData.totalWon = 999999999;
        }
    }

    private void increaseTotalLost(int losses) {
        userData.totalLost += losses;
        if (userData.totalLost < 0 || userData.totalLost > 999999999) {
            userData.totalLost = 999999999;
        }
    }
}
