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
    private final String[] words;
    private final Storage storage;
    private final UserData userData;

    public FlipCommand(String[] words, UserData userData, Storage storage) {
        this.words = words;
        this.userData = userData;
        this.storage = storage;
        CoinflipLogger.fine("FlipCommand created");
    }

    @Override
    public void execute() throws CoinflipException {
        CoinflipLogger.info("Executing flip command");
        this.flip();
        storage.saveData(userData);
    }

    //@@author wongyihao0506

    /**
     * Simulates flipping a coin and determines the outcome based on the user's choice.
     * <p>
     * This method validates the input, checks if the user has sufficient balance to place a bet,
     * and then performs a coin flip to determine the result. If the user wins, their balance is
     * increased by the bet amount; otherwise, it is decreased. The method also updates the user's
     * win/loss counts and total winnings/losses.
     * </p>
     *
     * @throws CoinflipException If the input command format is invalid or the flip choice is not "heads" or "tails".
     */
    public void flip() throws CoinflipException {
        CoinflipLogger.info("User attempting to flip a coin");

        if (words.length != 2) {
            CoinflipLogger.warning("Invalid flip command format");
            throw new CoinflipException(CoinflipException.FLIP_INVALID_FORMAT);
        }

        if (!words[1].equals("heads") && !words[1].equals("tails")) {
            CoinflipLogger.warning("Invalid flip choice: " + words[1]);
            throw new CoinflipException(CoinflipException.FLIP_INVALID_FORMAT);
        }

        if (userData.betAmount > userData.balance) {
            CoinflipLogger.warning("Bet amount exceeds balance: bet = "
                    + userData.betAmount
                    + ", balance = "
                    + userData.balance);
            Printer.printNotEnoughCoins();
            return;
        }

        Random random = new Random();
        String coinFlip = random.nextBoolean() ? "Heads" : "Tails";
        Boolean outcome = coinFlip.equalsIgnoreCase(words[1]);

        if (outcome) {
            userData.balance += userData.betAmount;
            increaseWinCount();
            increaseTotalWinnings(userData.betAmount);
            CoinflipLogger.info("User won "
                    + userData.betAmount
                    + " coins. New balance: "
                    + userData.balance);
        } else {
            userData.balance -= userData.betAmount;
            increaseLoseCount();
            increaseTotalLosses(userData.betAmount);
            CoinflipLogger.info("User lost " + userData.betAmount + " coins. New balance: " + userData.balance);
        }

        Printer.printBetAmount(userData.betAmount);
        Printer.printFlipOutcome(coinFlip, outcome, userData.betAmount);
        Printer.printBalance(userData.balance);

        assert userData.balance >= 0 : "balance should be more than or equal to 0";
    }

    //@@author CRL006
    private void increaseWinCount() {
        userData.winCount += 1;
    }

    //@@author CRL006
    private void increaseLoseCount() {
        userData.loseCount += 1;
    }

    //@@author CRL006
    private void increaseTotalWinnings(int winnings) {
        userData.totalWinnings += winnings;
    }

    //@@author CRL006
    private void increaseTotalLosses(int losses) {
        userData.totalLosings += losses;
    }
}
