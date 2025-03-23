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
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

import seedu.coinflip.utils.exceptions.CoinflipException;
import seedu.coinflip.utils.exceptions.CoinflipFileException;
import seedu.coinflip.utils.printer.Printer;

public class Coinflip {
    private static String saveFilePath = "./data/coinflip.csv";
    private static String logFilePath = "./data/coinflip.log";
    private static Logger logger;
    private FileHandler fileHandler;

    private int balance = 500;
    private int betAmount = 20;

    //@@author HTY2003

    /**
     * Constructs Coinflip object
     */
    Coinflip() throws SecurityException, IOException {
        logger = Logger.getLogger("seedu.coinflip");
        logger.setUseParentHandlers(false);

        File log = new File(logFilePath);
        if (!log.exists()) {
            Files.createDirectories(Paths.get("./data"));
            Files.createFile(Paths.get(logFilePath));
        }

        fileHandler = new FileHandler(logFilePath);
        logger.addHandler(fileHandler);

        SimpleFormatter formatter = new SimpleFormatter();
        fileHandler.setFormatter(formatter);
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
        } catch (IOException e) {
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
            createSaveFile();
            Printer.printNewSaveFileNote();
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
     * Parses the provided string data to obtain the saved balance as an integer.
     * Formats the string to convert it into an integer.
     *
     * @param data
     * @return the saved balance as an integer after formatting.
     * @throws NumberFormatException if the given data string cannot be parsed into an integer.
     */
    private int getSavedBalance(String data) {
        return Integer.parseInt(data.trim());
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
        try {
            BufferedReader reader = new BufferedReader(new FileReader(saveFilePath));
            String data;
            skipHeader(reader);
            while (((data = reader.readLine()) != null)) {
                try {
                    balance = getSavedBalance(data);
                } catch (NumberFormatException e) {
                    throw new CoinflipFileException(CoinflipFileException.SAVE_FILE_CORRUPTED);
                }
            }
        } catch (IOException e) {
            throw new CoinflipFileException(CoinflipFileException.SAVE_FILE_NO_ACCESS);
        }
    }

    //@@author CRL006
    private void saveBalance(int balance) throws CoinflipFileException {
        try {
            FileWriter writer = new FileWriter(saveFilePath);
            writer.write("Balance\n");
            writer.write(balance + "\n");
            writer.close();
        } catch (IOException e) {
            throw new CoinflipFileException(CoinflipFileException.SAVE_FILE_CANNOT_SAVE);
        }
    }

    //@@author CRL006
    private void saveToFile() throws CoinflipFileException {
        saveBalance(balance);
    }

    //@@author timothyloh0523
    private void check(String[] words) throws CoinflipException {
        if (words.length != 2) {
            throw new CoinflipException(CoinflipException.CHECK_INVALID_FORMAT);
        }

        if (!words[1].equals("balance") && !words[1].equals("bet")) {
            throw new CoinflipException(CoinflipException.CHECK_INVALID_FORMAT);
        }

        if (words[1].equals("balance")) {
            Printer.printBalance(balance);
        } else {
            Printer.printBetAmount(betAmount);
        }
    }

    //@@author OliverQiL
    private void change(String[] words) throws CoinflipException {
        try {
            betAmount = Integer.parseInt(words[1]);

            if (betAmount < 0) {
                throw new CoinflipException(CoinflipException.BET_AMOUNT_INVALID_FORMAT);
            }

            if (betAmount > balance) {
                Printer.printNotEnoughCoins();
                return;
            }

            Printer.printBetAmount(betAmount);

        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new CoinflipException(CoinflipException.BET_AMOUNT_INVALID_FORMAT);
        }
    }

    //@@author wongyihao0506
    private void bet(String[] words) throws CoinflipException {
        if (words.length != 2) {
            throw new CoinflipException(CoinflipException.FLIP_INVALID_FORMAT);
        }

        if (!words[1].equals("heads") && !words[1].equals("tails")) {
            throw new CoinflipException(CoinflipException.FLIP_INVALID_FORMAT);
        }

        if (betAmount > balance) {
            Printer.printNotEnoughCoins();
            return;
        }

        Random random = new Random();
        String coinFlip = random.nextBoolean() ? "Heads" : "Tails";
        Boolean outcome = coinFlip.equalsIgnoreCase(words[1]);

        if (outcome) {
            balance += getBetAmount();
        } else {
            balance -= getBetAmount();
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

        Scanner in = new Scanner(System.in);
        Printer.printWelcome();

        try {
            setupFile();
            loadFromFile();
        } catch (CoinflipFileException e) {
            Printer.printException(e);
        }

        boolean isExit = false;

        while (!isExit) {
            String input = in.nextLine();
            logger.log(Level.INFO, "New Command Received");

            String[] words = input.split("\\s+");

            try {
                switch (words[0]) {
                case "check":
                    check(words);
                    break;
                case "change":
                    change(words);
                    break;
                case "flip":
                    bet(words);
                    break;
                case "help":
                    Printer.printHelp();
                    break;
                case "exit":
                    Printer.printBye();
                    try {
                        saveToFile();
                    } catch (CoinflipFileException e) {
                        Printer.printException(e);
                    }
                    isExit = true;
                    break;
                default:
                    Printer.printInvalidCommand();
                    break;
                }
            } catch (CoinflipException e) {
                Printer.printException(e);
            }
        }
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
