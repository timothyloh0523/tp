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

public class Storage {
    private static String saveFilePath = "./data/coinflip.csv";
    private static String saveFileFolderPath = "./data";

    //@@author HTY2003
    public Storage() {
    }

    /**
     * Sets the path for the save file. Only used for testing.
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
     * Creates a save file in the designated directory.
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

    //@@author HTY2003
    private void createSaveFileDirectory() throws IOException {
        Path fullFolderPath = Paths.get(saveFileFolderPath);
        Files.createDirectories(fullFolderPath);
        assert Files.exists(fullFolderPath) : "Directory at saveFileFolderPath should exist";
    }

    //@@author HTY2003
    public void createSaveFile() throws IOException {
        Path fullFilePath = Paths.get(saveFilePath);
        Files.createFile(fullFilePath);
        assert Files.exists(fullFilePath) : "File at saveFilePath should exist";
    }

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
    public void checkData(String[] values) throws CoinflipFileException {
        checkNumberOfFields(values);

        for (int i = 0; i < 10; i++) {
            checkNumerical(values[i]);
            checkCanBeInteger(values[i]);
            int value = Integer.parseInt(values[i]);
            checkNonNegative(value);
        }

        int winStreak = Integer.parseInt(values[6]);
        int loseStreak = Integer.parseInt(values[7]);
        checkOneFieldZero(winStreak, loseStreak);
    }

    //@@author HTY2003
    private static void checkNumberOfFields(String[] values) throws CoinflipFileException {
        if (values.length != 10) {
            CoinflipLogger.warning("Corrupted save file: incorrect column count");
            throw new CoinflipFileException(CoinflipFileException.SAVE_FILE_CORRUPTED);
        }
    }

    //@@author timothyloh0523
    private static void checkOneFieldZero(int winStreak, int loseStreak) throws CoinflipFileException {
        if (winStreak != 0 && loseStreak != 0) {
            CoinflipLogger.warning("Corrupted save file: nonzero win and loss streak count");
            throw new CoinflipFileException(CoinflipFileException.SAVE_FILE_CORRUPTED);
        }
    }

    //@@author HTY2003
    private static void checkNumerical(String input) throws CoinflipFileException {
        if (!input.matches("[0-9]+")) {
            CoinflipLogger.warning("Corrupted save file: non-numerical");
            throw new CoinflipFileException(CoinflipFileException.SAVE_FILE_CORRUPTED);
        }
    }

    //@@author HTY2003
    private static void checkCanBeInteger(String input) throws CoinflipFileException {
        if (input.length() > 9) {
            CoinflipLogger.warning("Corrupted save file: number too long");
            throw new CoinflipFileException(CoinflipFileException.SAVE_FILE_CORRUPTED);
        }
    }

    //@@author HTY2003
    private static void checkNonNegative(Integer value) throws CoinflipFileException {
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
        userData.winStreak = Integer.parseInt(values[6]);
        userData.loseStreak = Integer.parseInt(values[7]);
        userData.highestWinStreak = Integer.parseInt(values[8]);
        userData.highestLoseStreak = Integer.parseInt(values[9]);
        userData.fiveWinStreak = Integer.parseInt(values[10]);
        userData.tenWinStreak = Integer.parseInt(values[11]);
        userData.twentyWinStreak = Integer.parseInt(values[12]);
        userData.fiftyWinStreak = Integer.parseInt(values[13]);
        userData.hundredWinStreak = Integer.parseInt(values[14]);

        return userData;
    }

    //@@author CRL006
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
                "Win Streak, Loss Streak, Highest Win Streak, Highest Loss Streak," +
                "Five Win Streak Count, Ten Win Streak Count, Fifty Win Streak Count + Hundred Win Streak Count\n");
        writer.write(userData.betAmount + "," +
                userData.balance + "," +
                userData.winCount + "," +
                userData.loseCount + "," +
                userData.totalWon + "," +
                userData.totalLost + "," +
                userData.winStreak + "," +
                userData.loseStreak + "," +
                userData.highestWinStreak + "," +
                userData.highestLoseStreak + "," +
                userData.fiveWinStreak + "," +
                userData.tenWinStreak + "," +
                userData.twentyWinStreak + "," +
                userData.fiftyWinStreak + "," +
                userData.hundredWinStreak + "\n");
    }
}
