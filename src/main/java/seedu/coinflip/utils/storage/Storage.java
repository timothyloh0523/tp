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
    private static final String saveFilePath = "./data/coinflip.csv";
    private static final String saveFileFolderPath = "./data";

    //@@author HTY2003
    public Storage() {
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
    private boolean checkSaveFileExists() {
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

    private void createSave() throws CoinflipFileException {
        try {

            this.createSaveFileDirectory();
            this.createSaveFile();

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
    private void createSaveFile() throws IOException {
        Path fullFilePath = Paths.get(saveFilePath);
        Files.createFile(fullFilePath);
        assert Files.exists(fullFilePath) : "File at saveFilePath should exist";
    }

    //@@author CRL006
    private String readData() throws CoinflipFileException {
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

    //@@author HTY2003
    private void checkData(String[] values) throws CoinflipFileException {
        if (values.length != 5) {
            CoinflipLogger.warning("Corrupted save file: incorrect column count");
            throw new CoinflipFileException(CoinflipFileException.SAVE_FILE_CORRUPTED);
        }

        for (int i = 0; i < 5; i++) {
            if (!values[i].matches("[0-9]+")) {
                CoinflipLogger.warning("Corrupted save file: data is non-numerical");
                throw new CoinflipFileException(CoinflipFileException.SAVE_FILE_CORRUPTED);
            }

            if (Integer.parseInt(values[i]) < 0) {
                CoinflipLogger.warning("Corrupted save file: data contains negative numbers");
                throw new CoinflipFileException(CoinflipFileException.SAVE_FILE_CORRUPTED);
            }
        }
    }

    //@@author HTY2003
    private UserData getUserData(String[] values) throws NumberFormatException {
        UserData userData = new UserData();

        userData.balance = Integer.parseInt(values[0]);
        userData.winCount = Integer.parseInt(values[1]);
        userData.loseCount = Integer.parseInt(values[2]);
        userData.totalWinnings = Integer.parseInt(values[3]);
        userData.totalLosings = Integer.parseInt(values[4]);

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
        writer.write("Balance, Wins, Losses, Amount Won, Amount Lost\n");
        writer.write(userData.balance + "," +
                userData.winCount + "," +
                userData.loseCount + "," +
                userData.totalWinnings + "," +
                userData.totalLosings + "\n");
    }
}
