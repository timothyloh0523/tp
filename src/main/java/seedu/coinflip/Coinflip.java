package seedu.coinflip;

import java.io.IOException;

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
    private final Storage storage;
    private UserData userData;

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
     * Returns the cumulative total of all winnings the user has
     *
     * @return user's total winnings in coins
     */
    public int getTotalWinnings() {
        return userData.totalWinnings;
    }

    /**
     * Returns the cumulative total of all losses the user has
     *
     * @return user's total losses in coins
     */
    public int getTotalLosings() {
        return userData.totalLosings;
    }

    //@@author HTY2003

    /**
     * Runs main Coinflip program, which waits for a line of user input
     * before outputting an appropriate response
     *
     * @param args Arguments included with command to start Coinflip
     */
    public void run(String[] args) {
        CoinflipLogger.info("Starting main program loop...");
        Printer.printWelcome();

        try {
            userData = storage.loadSave();
        } catch (CoinflipFileException e) {
            CoinflipLogger.warning("Error reading/creating save file: " + e.message);
            Printer.printException(e);
        }

        Printer.printUnderscoreLine();

        Parser parser = new Parser(userData, storage);
        boolean isExit = false;

        while (!isExit) {
            parser.receiveUserInput();
            Printer.printUnderscoreLine();

            try {
                Command command = parser.parseUserInput();
                command.execute();

                if (command instanceof ExitCommand) {
                    CoinflipLogger.info("Exiting main program loop...");
                    isExit = true;
                }

            } catch (CoinflipException e) {
                CoinflipLogger.warning("Error executing command: " + e.message);
                Printer.printException(e);
            }

            Printer.printUnderscoreLine();
        }

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

