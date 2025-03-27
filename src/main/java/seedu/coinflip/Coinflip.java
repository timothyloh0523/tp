package seedu.coinflip;

import java.util.Scanner;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import seedu.coinflip.utils.command.Command;
import seedu.coinflip.utils.command.ExitCommand;
import seedu.coinflip.utils.exceptions.CoinflipException;
import seedu.coinflip.utils.exceptions.CoinflipFileException;
import seedu.coinflip.utils.logger.CoinflipLogger;
import seedu.coinflip.utils.parser.Parser;
import seedu.coinflip.utils.printer.Printer;

public class Coinflip {
    private static String saveFilePath = "./data/coinflip.csv";

    private int balance = 500;
    private int betAmount = 20;
    private int winCount = 0;
    private int loseCount = 0;
    private int totalWinnings = 0;
    private int totalLosses = 0;

    //@@author HTY2003

    /**
     * Constructs Coinflip object
     */
    Coinflip() throws SecurityException, IOException {
        // Initialize the logger
        if (!CoinflipLogger.init()) {
            throw new IOException("Failed to initialize logger");
        }
        CoinflipLogger.info("Coinflip application started");
    }

    /**
     * Returns user's coin balance
     *
     * @return user's balance in coins
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Returns the amount of coins a user bets on each coinflip
     *
     * @return user's betting amount in coins
     */
    public int getBetAmount() {
        return betAmount;
    }

    /**
     * Returns the total number of times a user has won
     *
     * @return user's win count
     */
    public int getWinCount() {
        return winCount;
    }

    /**
     * Returns the total number of times a user has lost
     *
     * @return user's lose count
     */
    public int getLoseCount() {
        return loseCount;
    }

    /**
     * Returns the cumulative total of all winnings the user has in all coinflips
     *
     * @return user's total winnings in coins
     */
    public int getTotalWinnings() {
        return totalWinnings;
    }

    /**
     * Returns the cumulative total of all losses the user has in all coinflips
     *
     * @return user's total losses in coins
     */
    public int getTotalLosses() {
        return totalLosses;
    }

    /**
     * Parses a string to an integer, handling potential number format exceptions.
     *
     * @param input The string to parse into an integer.
     * @return The parsed integer.
     * @throws NumberFormatException If the input is not a valid integer.
     */
    public static int parseToInt(String input) throws NumberFormatException {
        return Integer.parseInt(input.trim());
    }

    //@@author CRL006
    /**
     * Creates a save file in the designated directory.
     * Ensures that the parent directory exists before attempting to create the file.
     * If the file creation fails, a custom {@code CoinflipFileException} is thrown.
     *
     * @throws CoinflipFileException if the save file cannot be created
     */
    private void createSaveFile() throws CoinflipFileException {
        try {
            Files.createDirectories(Paths.get("./data"));
            assert Files.exists(Paths.get("./data")) : "Directory './data' should have been created";
            Files.createFile(Paths.get(saveFilePath));
            CoinflipLogger.info("Created new save file at " + saveFilePath);
        } catch (IOException e) {
            CoinflipLogger.exception("Failed to create save file", e);
            throw new CoinflipFileException(CoinflipFileException.SAVE_FILE_CANNOT_CREATE);
        }
    }

    //@@author CRL006

    /**
     * Ensures the presence of a save file at the specified path.
     * If the save file does not exist, this method creates the necessary file
     * and directory structure by calling the {@link #createSaveFile()} method. It also
     * prints a notification using {@link Printer#printNewSaveFileNote()}.
     *
     * @throws CoinflipFileException if the save file cannot be created.
     */
    private void setupFile() throws CoinflipFileException {
        File userData = new File(saveFilePath);
        if (!userData.exists()) {
            CoinflipLogger.info("Save file not found, creating new one");
            createSaveFile();
            Printer.printNewSaveFileNote();
        } else {
            CoinflipLogger.info("Using existing save file at " + saveFilePath);
        }
    }

    //@@author CRL006

    /**
     * Skips the header line of the csv file by
     * reading the first line in the csv file without using it for anything.
     * If an error occurs while reading, a {@link CoinflipFileException} is thrown.
     *
     * @param reader the BufferedReader object used to read the file content.
     * @throws IOException if an I/O error occurs while reading the header.
     * @throws CoinflipFileException if the header line cannot be read.
     */
    private void skipHeader(BufferedReader reader) throws IOException, CoinflipFileException {
        try {
            reader.readLine();
        } catch (IOException e) {
            throw new CoinflipFileException(CoinflipFileException.SAVE_FILE_CANNOT_READ);
        }
    }

    //@@author CRL006

    /**
     * Loads the saved balance data from the specified save file with path {@code saveFilePath}.
     * Reads the file line by line after skipping the header,
     * processing each line to load the user data from the csv file.
     * If an error occurs, {@link CoinflipFileException} will be thrown.
     *
     * @throws CoinflipFileException
     */
    private void loadFromFile() throws CoinflipFileException {
        CoinflipLogger.info("Attempting to load data from save file");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(saveFilePath));
            String data;
            skipHeader(reader);
            while (((data = reader.readLine()) != null)) {
                try {
                    String[] values = data.split(",");
                    if (values.length != 5) {
                        CoinflipLogger.warning("Corrupted save file: incorrect column count");
                        throw new CoinflipFileException(CoinflipFileException.SAVE_FILE_CORRUPTED);
                    }
                    balance = parseToInt(values[0]);
                    winCount = parseToInt(values[1]);
                    loseCount = parseToInt(values[2]);
                    totalWinnings = parseToInt(values[3]);
                    totalLosses = parseToInt(values[4]);
                    CoinflipLogger.info("Successfully loaded user data");
                } catch (NumberFormatException e) {
                    CoinflipLogger.exception("Failed to parse save file data", e);
                    throw new CoinflipFileException(CoinflipFileException.SAVE_FILE_CORRUPTED);
                }
            }
        } catch (IOException e) {
            CoinflipLogger.exception("Failed to access save file", e);
            throw new CoinflipFileException(CoinflipFileException.SAVE_FILE_NO_ACCESS);
        }
    }

    //@@author CRL006
    private void saveData() throws CoinflipFileException {
        CoinflipLogger.info("Saving user data to file");
        try {
            FileWriter writer = new FileWriter(saveFilePath);
            writer.write("Balance,WinCount,LoseCount,Total Winnings,Total Losses\n");
            writer.write(getBalance() + "," + getWinCount() + "," +
                    getLoseCount() + "," + getTotalWinnings() + "," +
                    getTotalLosses() + "\n");
            writer.close();
            CoinflipLogger.info("Successfully saved user data");
        } catch (IOException e) {
            CoinflipLogger.exception("Failed to save user data", e);
            throw new CoinflipFileException(CoinflipFileException.SAVE_FILE_CANNOT_SAVE);
        }
    }

    //@@author CRL006
    public void saveToFile() throws CoinflipFileException {
        saveData();
    }

    //@@author CRL006
    public void increaseWinCount() {
        winCount += 1;
    }

    //@@author CRL006
    public void increaseLoseCount() {
        loseCount += 1;
    }

    //@@author CRL006
    public void increaseTotalWinnings(int winnings) {
        totalWinnings += winnings;
    }

    //@@author CRL006
    public void increaseTotalLosses(int losses) {
        totalLosses += losses;
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
            CoinflipLogger.info("User checked balance = " + balance);
            Printer.printBalance(balance);
        } else {
            CoinflipLogger.info("User checked bet amount = " + betAmount);
            Printer.printBetAmount(betAmount);
        }
    }

    //@@author OliverQiL
    public void change(String[] words) throws CoinflipException {
        try {
            betAmount = parseToInt(words[1]);

            if (betAmount < 0) {
                CoinflipLogger.warning("Invalid bet amount");
                throw new CoinflipException(CoinflipException.BET_AMOUNT_INVALID_FORMAT);
            }

            if (betAmount > balance) {
                CoinflipLogger.warning("Bet amount exceeded balance");
                Printer.printNotEnoughCoins();
                return;
            }

            Printer.printBetAmount(betAmount);

        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            CoinflipLogger.warning("Invalid bet amount");
            throw new CoinflipException(CoinflipException.BET_AMOUNT_INVALID_FORMAT);
        }
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

        if (betAmount > balance) {
            CoinflipLogger.warning("Bet amount exceeds balance: bet = " + betAmount + ", balance = " + balance);
            Printer.printNotEnoughCoins();
            return;
        }

        Random random = new Random();
        String coinFlip = random.nextBoolean() ? "Heads" : "Tails";
        Boolean outcome = coinFlip.equalsIgnoreCase(words[1]);

        if (outcome) {
            balance += getBetAmount();
            increaseWinCount();
            increaseTotalWinnings(getBetAmount());
            CoinflipLogger.info("User won " + getBetAmount() + " coins. New balance: " + balance);
        } else {
            balance -= getBetAmount();
            increaseLoseCount();
            increaseTotalLosses(getBetAmount());
            CoinflipLogger.info("User lost " + getBetAmount() + " coins. New balance: " + balance);
        }

        Printer.printBetAmount(betAmount);
        Printer.printFlipOutcome(coinFlip, outcome, betAmount);
        Printer.printBalance(balance);

        assert balance >= 0 : "balance should be more than or equal to 0";
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
            setupFile();
            loadFromFile();
        } catch (CoinflipFileException e) {
            CoinflipLogger.warning("File setup / loading issue: " + e.message);
            Printer.printException(e);
        }

        boolean isExit = false;
        Parser parser = new Parser(this);

        while (!isExit) {
            String input = in.nextLine();
            CoinflipLogger.info("Received user input: " + input);

            try {
                Command command = (parser).parseUserInput(input);
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
