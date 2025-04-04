package seedu.coinflip.utils.command;

import seedu.coinflip.utils.achievement.MaxWinStreakAchievement;
import seedu.coinflip.utils.achievement.WinsAchievement;
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
        String achievement = processOutcome(outcome);

        userData.fiveWinStreak = WinsAchievement.achievements.get(0).timesCompleted;
        userData.tenWinStreak = WinsAchievement.achievements.get(1).timesCompleted;
        userData.twentyWinStreak = WinsAchievement.achievements.get(2).timesCompleted;
        userData.fiftyWinStreak = WinsAchievement.achievements.get(3).timesCompleted;
        userData.hundredWinStreak = WinsAchievement.achievements.get(4).timesCompleted;

        Printer.printFlipOutcome(actualFlip, outcome, userData.betAmount);
        Printer.printBetAmount(userData.betAmount);
        Printer.printBalance(userData.balance);
        Printer.printStreaks(userData.winStreak, userData.loseStreak);

        checkPrint(achievement);

        assert userData.balance >= 0 : "balance should be more than or equal to 0";
    }

    private String processOutcome(Boolean outcome) {
        if (outcome) {
            return updateUserWon();
        } else {
            updateUserLost();
            return "NA";
        }
    }

    private String updateUserWon() {
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
        return MaxWinStreakAchievement.execute(userData.winStreak, userData);
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

    private void checkPrint(String achievement) {
        switch (achievement) {
        case "5":
            Printer.printUnderscoreLine();
            Printer.printUnlockFiveWinStreakAchievement();
            break;
        case "10":
            Printer.printUnderscoreLine();
            Printer.printUnlockTenWinStreakAchievement();
            break;
        case "20":
            Printer.printUnderscoreLine();
            Printer.printUnlockTwentyWinStreakAchievement();
            break;
        case "50":
            Printer.printUnderscoreLine();
            Printer.printUnlockFiftyWinStreakAchievement();
            break;
        case "100":
            Printer.printUnderscoreLine();
            Printer.printUnlockHundredWinStreakAchievement();
            break;
        case "5 5":
            Printer.printUnderscoreLine();
            Printer.printUnlockFiveFiveWinStreakAchievement();
            break;
        case "10 5":
            Printer.printUnderscoreLine();
            Printer.printUnlockTenFiveWinStreakAchievement();
            break;
        case "20 5":
            Printer.printUnderscoreLine();
            Printer.printUnlockTwentyFiveWinStreakAchievement();
            break;
        case "50 5":
            Printer.printUnderscoreLine();
            Printer.printUnlockFiftyFiveWinStreakAchievement();
            break;
        case "100 5":
            Printer.printUnderscoreLine();
            Printer.printUnlockHundredFiveWinStreakAchievement();
            break;
        case "5 10":
            Printer.printUnderscoreLine();
            Printer.printUnlockFiveTenWinStreakAchievement();
            break;
        case "10 10":
            Printer.printUnderscoreLine();
            Printer.printUnlockTenTenWinStreakAchievement();
            break;
        case "20 10":
            Printer.printUnderscoreLine();
            Printer.printUnlockTwentyTenWinStreakAchievement();
            break;
        case "50 10":
            Printer.printUnderscoreLine();
            Printer.printUnlockFiftyTenWinStreakAchievement();
            break;
        case "100 10":
            Printer.printUnderscoreLine();
            Printer.printUnlockHundredTenWinStreakAchievement();
            break;
        case "5 20":
            Printer.printUnderscoreLine();
            Printer.printUnlockFiveTwentyWinStreakAchievement();
            break;
        case "10 20":
            Printer.printUnderscoreLine();
            Printer.printUnlockTenTwentyWinStreakAchievement();
            break;
        case "20 20":
            Printer.printUnderscoreLine();
            Printer.printUnlockTwentyTwentyWinStreakAchievement();
            break;
        case "50 20":
            Printer.printUnderscoreLine();
            Printer.printUnlockFiftyTwentyWinStreakAchievement();
            break;
        case "100 20":
            Printer.printUnderscoreLine();
            Printer.printUnlockHundredTwentyWinStreakAchievement();
            break;
        case "5 50":
            Printer.printUnderscoreLine();
            Printer.printUnlockFiveFiftyWinStreakAchievement();
            break;
        case "10 50":
            Printer.printUnderscoreLine();
            Printer.printUnlockTenFiftyWinStreakAchievement();
            break;
        case "20 50":
            Printer.printUnderscoreLine();
            Printer.printUnlockTwentyFiftyWinStreakAchievement();
            break;
        case "50 50":
            Printer.printUnderscoreLine();
            Printer.printUnlockFiftyFiftyWinStreakAchievement();
            break;
        case "100 50":
            Printer.printUnderscoreLine();
            Printer.printUnlockHundredFiftyWinStreakAchievement();
            break;
        case "5 100":
            Printer.printUnderscoreLine();
            Printer.printUnlockFiveHundredWinStreakAchievement();
            break;
        case "10 100":
            Printer.printUnderscoreLine();
            Printer.printUnlockTenHundredWinStreakAchievement();
            break;
        case "20 100":
            Printer.printUnderscoreLine();
            Printer.printUnlockTwentyHundredWinStreakAchievement();
            break;
        case "50 100":
            Printer.printUnderscoreLine();
            Printer.printUnlockFiftyHundredWinStreakAchievement();
            break;
        case "100 100":
            Printer.printUnderscoreLine();
            Printer.printUnlockHundredHundredWinStreakAchievement();
            break;
        default:
            break;
        }
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

    //@@author timothyloh0523
    private void increaseWinStreak() {
        userData.winStreak += 10;
    }

    private void increaseLoseStreak() { userData.loseStreak += 1; }

    private void resetWinStreak() { userData.winStreak = 0; }

    private void resetLoseStreak() { userData.loseStreak = 0; }

    private void increaseTotalWon(int earnings) {
        userData.totalWon += earnings;
    }

    private void increaseTotalLost(int losses) {
        userData.totalLost += losses;
    }
}
