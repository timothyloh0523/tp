package seedu.coinflip.utils.storage;

import seedu.coinflip.utils.exceptions.CoinflipFileException;
import seedu.coinflip.utils.logger.CoinflipLogger;
import seedu.coinflip.utils.printer.Printer;
import seedu.coinflip.utils.userdata.UserData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Helper class which provides high-level abstractions for file saving and loading operations.
 */

public class Storage {
    private static String saveFilePath = "./data/coinflip.csv";
    private static String saveFileFolderPath = "./data";
    private static final int NUMBER_OF_FIELDS = 10;

    //@@author HTY2003

    /**
     * Constructs Storage object with the default values.
     */
    public Storage() {
    }

    /**
     * Sets the path for the save file. Only used for testing.
     *
     * @param filePath new save file path
     */
    public void setSaveFilePath(String filePath) {
        this.saveFilePath = filePath;
    }


    //@@author CRL006

    /**
     * Returns UserData object extracted from save file.
     *
     * @return UserData object containing data from save file
     * @throws CoinflipFileException when file cannot be accessed or data is corrupted
     */
    public UserData loadSave() throws CoinflipFileException {

        boolean saveFileExists = this.checkSaveFileExists();

        if (!saveFileExists) {
            CoinflipLogger.info("Save file not found");
            Printer.printNewSaveFileNote();
            this.createSave();
            CoinflipLogger.info("New save file created");
            this.saveData(new UserData());
            return new UserData();
        }

        CoinflipLogger.info("Attempting to load save file");

        String data = this.readData();
        String[] values = this.processData(data);
        this.checkData(values);
        UserData savedData = getUserData(values);

        CoinflipLogger.info("Successfully loaded user data");

        return savedData;
    }

    //@@author HTY2003
    public boolean checkSaveFileExists() {
        File saveFile = new File(saveFilePath);
        return saveFile.exists();
    }

    //@@author CRL006

    /**
     * Creates both save file directory and save file, if not created already.
     * Ensures that the parent directory exists before attempting to create the file.
     * If the file creation fails, a custom {@code CoinflipFileException} is thrown.
     *
     * @throws CoinflipFileException if the save file cannot be created
     */

    public void createSave() throws CoinflipFileException {
        try {
            this.createSaveFileDirectory();
            this.createSaveFile();
            assert Files.exists(Path.of(saveFileFolderPath)) : "Directory at saveFileFolderPath should exist";
        } catch (IOException e) {
            CoinflipLogger.exception("Failed to create save file", e);
            throw new CoinflipFileException(CoinflipFileException.SAVE_FILE_CANNOT_CREATE);
        }
    }

    /**
     * Creates directory for save file.
     *
     * @throws IOException if the save file cannot be created
     */
    //@@author HTY2003
    private void createSaveFileDirectory() throws IOException {
        Path fullFolderPath = Paths.get(saveFileFolderPath);
        Files.createDirectories(fullFolderPath);
        assert Files.exists(fullFolderPath) : "Directory at saveFileFolderPath should exist";
    }

    /**
     * Creates save file.
     *
     * @throws IOException if the save file cannot be created
     */
    //@@author HTY2003
    public void createSaveFile() throws IOException {
        Path fullFilePath = Paths.get(saveFilePath);
        Files.createFile(fullFilePath);
        assert Files.exists(fullFilePath) : "File at saveFilePath should exist";
    }

    /**
     * Returns 2nd line of save file, which contains comma-delimited user data.
     *
     * @return 2nd line of save file
     * @throws CoinflipFileException
     */
    //@@author CRL006
    public String readData() throws CoinflipFileException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(saveFilePath));

            //Skip header of .csv file
            reader.readLine();
            String data = reader.readLine();

            if (data == null) {
                CoinflipLogger.warning("Corrupted save file: incorrect line count");
                throw new CoinflipFileException(CoinflipFileException.SAVE_FILE_CORRUPTED);
            }

            return data;

        } catch (IOException e) {
            CoinflipLogger.exception("Failed to read save file", e);
            throw new CoinflipFileException(CoinflipFileException.SAVE_FILE_NO_ACCESS);
        }
    }

    //@@author HTY2003
    private String[] processData(String data) {
        return data.split(",");
    }

    //@@author timothyloh0523
    private void checkData(String[] values) throws CoinflipFileException {
        checkNumberOfFields(values);

        for (int i = 0; i < NUMBER_OF_FIELDS; i++) {
            values[i] = values[i].trim();
            checkNumerical(values[i]);
            checkCanBeInteger(values[i]);
            int value = Integer.parseInt(values[i]);
            checkNonNegative(value);
        }

        int currentWinStreak = Integer.parseInt(values[6]);
        int currentLoseStreak = Integer.parseInt(values[7]);
        int highestWinStreak = Integer.parseInt(values[8]);
        int highestLoseStreak = Integer.parseInt(values[9]);

        checkCurrentWinLoseStreaksValid(currentWinStreak, currentLoseStreak);
        checkHighestStreakValid(currentWinStreak, highestWinStreak);
        checkHighestStreakValid(currentLoseStreak, highestLoseStreak);
    }

    //@@author HTY2003

    /**
     * Check whether save file data contains the correct number of values/fields.
     *
     * @param values List of data fields from save file
     * @throws CoinflipFileException if number of fields is incorrect
     */
    public static void checkNumberOfFields(String[] values) throws CoinflipFileException {
        if (values.length != NUMBER_OF_FIELDS) {
            CoinflipLogger.warning("Corrupted save file: incorrect column count");
            throw new CoinflipFileException(CoinflipFileException.SAVE_FILE_CORRUPTED);
        }
    }

    //@@author timothyloh0523

    /**
     * Checks whether current win and lose streak values are valid.
     *
     * @param winStreak  Win streak to check
     * @param loseStreak Lose streak to check
     * @throws CoinflipFileException if win streak and lose streak are both non-zero
     */
    public static void checkCurrentWinLoseStreaksValid(int winStreak, int loseStreak) throws CoinflipFileException {
        if (winStreak != 0 && loseStreak != 0) {
            CoinflipLogger.warning("Corrupted save file: nonzero win and loss streak count");
            throw new CoinflipFileException(CoinflipFileException.SAVE_FILE_CORRUPTED);
        }
    }

    /**
     * Checks whether current and highest streak values are valid.
     *
     * @param currentStreak Current win/lose streak to check
     * @param highestStreak Highest win/lose streak to check
     * @throws CoinflipFileException if currentStreak > highestStreak
     */
    public static void checkHighestStreakValid(int currentStreak, int highestStreak) throws CoinflipFileException {
        if (currentStreak > highestStreak) {
            CoinflipLogger.warning("Corrupted save file: current streak larger than highest streak");
            throw new CoinflipFileException(CoinflipFileException.SAVE_FILE_CORRUPTED);
        }
    }

    //@@author HTY2003

    /**
     * Checks whether given string can be represented as a number.
     *
     * @param input String to be checked
     * @throws CoinflipFileException if given string does not only contain numbers
     */
    public static void checkNumerical(String input) throws CoinflipFileException {
        if (!input.matches("[0-9]+")) {
            CoinflipLogger.warning("Corrupted save file: non-numerical");
            throw new CoinflipFileException(CoinflipFileException.SAVE_FILE_CORRUPTED);
        }
    }

    //@@author HTY2003

    /**
     * Checks whether given string can be stored as an integer (9 digits or lower in length).
     *
     * @param input String to be checked
     * @throws CoinflipFileException if given string is too long
     */
    public static void checkCanBeInteger(String input) throws CoinflipFileException {
        if (input.length() > 9) {
            CoinflipLogger.warning("Corrupted save file: number too long");
            throw new CoinflipFileException(CoinflipFileException.SAVE_FILE_CORRUPTED);
        }
    }

    //@@author HTY2003

    /**
     * Checks whether given value is non-negative (0 or higher).
     *
     * @param value Value to be checked
     * @throws CoinflipFileException if given integer input is negative
     */
    public static void checkNonNegative(Integer value) throws CoinflipFileException {
        if (value < 0) {
            CoinflipLogger.warning("Corrupted save file: negative numbers");
            throw new CoinflipFileException(CoinflipFileException.SAVE_FILE_CORRUPTED);
        }
    }

    //@@author HTY2003
    private UserData getUserData(String[] values) throws NumberFormatException {
        UserData userData = new UserData();

        userData.betAmount = Integer.parseInt(values[0]);
        userData.balance = Integer.parseInt(values[1]);
        userData.winCount = Integer.parseInt(values[2]);
        userData.loseCount = Integer.parseInt(values[3]);
        userData.totalWon = Integer.parseInt(values[4]);
        userData.totalLost = Integer.parseInt(values[5]);
        userData.currentWinStreak = Integer.parseInt(values[6]);
        userData.currentLoseStreak = Integer.parseInt(values[7]);
        userData.highestWinStreak = Integer.parseInt(values[8]);
        userData.highestLoseStreak = Integer.parseInt(values[9]);

        return userData;
    }

    //@@author CRL006

    /**
     * Saves given UserData object to save file.
     *
     * @param userData UserData object to be saved
     * @throws CoinflipFileException if file cannot be written to
     */

    public void saveData(UserData userData) throws CoinflipFileException {
        CoinflipLogger.info("Attempting to save user data");
        try {

            FileWriter writer = new FileWriter(saveFilePath);
            this.writeData(writer, userData);
            writer.close();

            CoinflipLogger.info("Successfully saved user data");

        } catch (IOException e) {
            CoinflipLogger.exception("Failed to save user data", e);
            throw new CoinflipFileException(CoinflipFileException.SAVE_FILE_CANNOT_SAVE);
        }
    }

    //@@author CRL006
    private void writeData(FileWriter writer, UserData userData) throws IOException {
        writer.write("Bet Amount, Balance, Wins, Losses, Total Won, Total Lost, " +
                "Current Win Streak, Current Loss Streak, Highest Win Streak, Highest Loss Streak\n");
        writer.write(userData.betAmount + "," +
                userData.balance + "," +
                userData.winCount + "," +
                userData.loseCount + "," +
                userData.totalWon + "," +
                userData.totalLost + "," +
                userData.currentWinStreak + "," +
                userData.currentLoseStreak + "," +
                userData.highestWinStreak + "," +
                userData.highestLoseStreak + "\n");
    }
}
