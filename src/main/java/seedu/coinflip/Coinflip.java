package seedu.coinflip;

import java.util.Scanner;
import java.io.IOException;
import java.util.Random;

import seedu.coinflip.utils.command.Command;
import seedu.coinflip.utils.command.ExitCommand;
import seedu.coinflip.utils.exceptions.CoinflipException;
import seedu.coinflip.utils.exceptions.CoinflipFileException;
import seedu.coinflip.utils.logger.CoinflipLogger;
import seedu.coinflip.utils.parser.Parser;
import seedu.coinflip.utils.printer.Printer;
import seedu.coinflip.utils.storage.Storage;
import seedu.coinflip.utils.userdata.UserData;

public class Coinflip {
    private Storage storage;
    private UserData userData;
    private Parser parser;

    //@@author HTY2003

    /**
     * Constructs Coinflip object
     */
    Coinflip() throws SecurityException, IOException {
        // Initialize the logger
        if (!CoinflipLogger.init()) {
            throw new IOException("Failed to initialize logger");
        }

        storage = new Storage();
        userData = new UserData();
        parser = new Parser(this);

        CoinflipLogger.info("Coinflip application started");
    }

    /**
     * Returns user's coin balance
     *
     * @return user's balance in coins
     */
    public int getBalance() {
        return userData.balance;
    }

    /**
     * Returns the amount of coins a user bets on each coinflip
     *
     * @return user's betting amount in coins
     */
    public int getBetAmount() {
        return userData.betAmount;
    }

    /**
     * Returns the total number of times a user has won
     *
     * @return user's win count
     */
    public int getWinCount() {
        return userData.winCount;
    }

    /**
     * Returns the total number of times a user has lost
     *
     * @return user's lose count
     */
    public int getLoseCount() {
        return userData.loseCount;
    }

    /**
     * Returns the cumulative total of all winnings the user has in all coinflips
     *
     * @return user's total winnings in coins
     */
    public int getTotalWinnings() {
        return userData.totalWinnings;
    }

    /**
     * Returns the cumulative total of all losses the user has in all coinflips
     *
     * @return user's total losses in coins
     */
    public int getTotalLosses() {
        return userData.totalLosses;
    }

    //@@author CRL006
    public void saveData() throws CoinflipFileException {
        storage.saveData(userData);
    }

    //@@author CRL006
    public void increaseWinCount() {
        userData.winCount += 1;
    }

    //@@author CRL006
    public void increaseLoseCount() {
        userData.loseCount += 1;
    }

    //@@author CRL006
    public void increaseTotalWinnings(int winnings) {
        userData.totalWinnings += winnings;
    }

    //@@author CRL006
    public void increaseTotalLosses(int losses) {
        userData.totalLosses += losses;
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
    public void check(String[] words) throws CoinflipException {
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

    //@@author OliverQiL
    public void change(String[] words) throws CoinflipException {

        if (words.length < 2) {
            CoinflipLogger.warning("Invalid bet amount");
            throw new CoinflipException(CoinflipException.BET_AMOUNT_INVALID_FORMAT);
        }

        if (!words[1].matches("[0-9]+")) {
            CoinflipLogger.warning("Invalid bet amount");
            throw new CoinflipException(CoinflipException.BET_AMOUNT_INVALID_FORMAT);
        }

        userData.betAmount = Integer.parseInt(words[1]);

        if (userData.betAmount < 0) {
            CoinflipLogger.warning("Invalid bet amount");
            throw new CoinflipException(CoinflipException.BET_AMOUNT_INVALID_FORMAT);
        }

        if (userData.betAmount > userData.balance) {
            CoinflipLogger.warning("Bet amount exceeded balance");
            Printer.printNotEnoughCoins();
            return;
        }

        Printer.printBetAmount(userData.betAmount);
    }

    //@@author wongyihao0506
    public void bet(String[] words) throws CoinflipException {
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
            userData.balance += getBetAmount();
            increaseWinCount();
            increaseTotalWinnings(getBetAmount());
            CoinflipLogger.info("User won " + getBetAmount() + " coins. New balance: " + userData.balance);
        } else {
            userData.balance -= getBetAmount();
            increaseLoseCount();
            increaseTotalLosses(getBetAmount());
            CoinflipLogger.info("User lost " + getBetAmount() + " coins. New balance: " + userData.balance);
        }

        Printer.printBetAmount(userData.betAmount);
        Printer.printFlipOutcome(coinFlip, outcome, userData.betAmount);
        Printer.printBalance(userData.balance);

        assert userData.balance >= 0 : "balance should be more than or equal to 0";
    }

    //@@author HTY2003

    /**
     * Runs main Coinflip program, which waits for next line of user input
     * before outputting an appropriate response
     *
     * @param args Arguments included with command to start Coinflip
     */
    public void run(String[] args) {
        CoinflipLogger.info("Starting Coinflip application run loop");

        Scanner in = new Scanner(System.in);
        Printer.printWelcome();

        try {
            userData = storage.loadSave();
        } catch (CoinflipFileException e) {
            CoinflipLogger.warning("File setup / loading issue: " + e.message);
            Printer.printException(e);
        }

        boolean isExit = false;

        while (!isExit) {
            String input = in.nextLine();
            CoinflipLogger.info("Received user input: " + input);

            try {
                Command command = parser.parseUserInput(input);
                command.execute();

                if (command instanceof ExitCommand) {
                    CoinflipLogger.info("Exit command received. Terminating application");
                    isExit = true;
                }

            } catch (CoinflipException e) {
                CoinflipLogger.warning("Command execution error: " + e.message);
                Printer.printException(e);
            }
        }

        CoinflipLogger.info("Coinflip application terminated");
        CoinflipLogger.close();
    }

    /**
     * Main entry-point for the java.seedu.coinflip.Coinflip application.
     *
     * @param args Arguments included with command to start Coinflip
     */
    public static void main(String[] args) {
        try {
            Coinflip program = new Coinflip();
            program.run(args);
        } catch (SecurityException | IOException e) {
            Printer.printLoggerFail();
            e.printStackTrace();
        }
    }
}
