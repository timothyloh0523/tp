package seedu.coinflip.utils.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import seedu.coinflip.utils.exceptions.CoinflipFileException;
import seedu.coinflip.utils.userdata.UserData;

class StorageTest {

    @Test
    void saveDataAndLoadSave_validData_success() throws CoinflipFileException {
        Storage storage = new Storage();
        UserData tempUserData = new UserData();

        tempUserData.betAmount = 20;
        tempUserData.balance = 500;
        tempUserData.totalWon = 0;
        tempUserData.totalLost = 0;
        tempUserData.winCount = 0;
        tempUserData.loseCount = 0;
        tempUserData.currentWinStreak = 0;
        tempUserData.currentLoseStreak = 0;
        tempUserData.highestWinStreak = 0;
        tempUserData.highestLoseStreak = 0;

        storage.setSaveFilePath("./src/test/testResources/storageTestFiles/validTestFile.csv");
        storage.saveData(tempUserData);
        UserData loadedData = storage.loadSave();

        assertEquals(tempUserData.betAmount, loadedData.betAmount);
        assertEquals(tempUserData.balance, loadedData.balance);
        assertEquals(tempUserData.totalWon, loadedData.totalWon);
        assertEquals(tempUserData.totalLost, loadedData.totalLost);
        assertEquals(tempUserData.winCount, loadedData.winCount);
        assertEquals(tempUserData.loseCount, loadedData.loseCount);
        assertEquals(tempUserData.currentWinStreak, loadedData.currentWinStreak);
        assertEquals(tempUserData.currentLoseStreak, loadedData.currentLoseStreak);
        assertEquals(tempUserData.highestWinStreak, loadedData.highestWinStreak);
        assertEquals(tempUserData.highestLoseStreak, loadedData.highestLoseStreak);
    }

    @Test
    void loadSave_missingFile_createsNewFile() throws Exception {
        Storage storage = spy(new Storage());
        doReturn(false).when(storage).checkSaveFileExists();
        doNothing().when(storage).createSave(); // Mock createSave to do nothing
        UserData userData = storage.loadSave();
        verify(storage).createSave();
        verify(storage).saveData(any(UserData.class));
        assertNotNull(userData);
        assertEquals(500, userData.balance); // Example assertion for default values
    }

    @Test
    void createSave_createsFileSuccessfully() throws CoinflipFileException, IOException {
        Path path = Paths.get("./src/test/testResources/storageTestFiles/testCreateSave.csv");
        if (Files.exists(path)) {
            Files.delete(path);
        }
        Storage storage = new Storage();
        storage.setSaveFilePath("./src/test/testResources/storageTestFiles/testCreateSave.csv");
        storage.createSave();
        assertTrue(Files.exists(path));
    }

    @Test
    void createSave_throwsExceptionWhenCannotCreateFile() throws IOException {
        Storage storage = spy(new Storage());
        doThrow(IOException.class).when(storage).createSaveFile();
        assertThrows(CoinflipFileException.class, storage::createSave);
    }

    @Test
    void readData_returnsNull_throwsException() throws Exception {
        Storage storage = spy(new Storage());
        doReturn(null).when(storage).readData();
        assertThrows(NullPointerException.class, storage::loadSave);
    }

    @Test
    void checkNumberOfFields_incorrectNumberOfFields_throwsException() {
        String[] incorrectValues = new String[5];
        assertThrows(CoinflipFileException.class, () -> Storage.checkNumberOfFields(incorrectValues));
    }

    @Test
    void checkNumberOfFields_correctNumberOfFields_doesNotThrow() throws CoinflipFileException {
        String[] correctValues = new String[10];
        Storage.checkNumberOfFields(correctValues);
    }

    @Test
    void checkCurrentWinLoseStreaksValid_nonZeroWinAndLoseStreak_throwsException() {
        assertThrows(CoinflipFileException.class, () -> Storage.checkCurrentWinLoseStreaksValid(1, 1));
    }

    @Test
    void checkCurrentWinLoseStreaksValid_zeroWinOrLoseStreak_doesNotThrow() throws CoinflipFileException {
        Storage.checkCurrentWinLoseStreaksValid(0, 1);
        Storage.checkCurrentWinLoseStreaksValid(1, 0);
    }

    @Test
    void checkHighestStreakValid_currentStreakGreaterThanHighest_throwsException() {
        assertThrows(CoinflipFileException.class, () -> Storage.checkHighestStreakValid(5, 3));
    }

    @Test
    void checkHighestStreakValid_currentStreakLessThanOrEqualToHighest_doesNotThrow() throws CoinflipFileException {
        Storage.checkHighestStreakValid(3, 5);
        Storage.checkHighestStreakValid(5, 5);
    }

    @Test
    void checkNumerical_nonNumericalInput_throwsException() {
        Storage storage = new Storage();
        assertThrows(CoinflipFileException.class, () -> Storage.checkNumerical("one23"));
        assertThrows(CoinflipFileException.class, () -> Storage.checkNumerical("12three"));
        assertThrows(CoinflipFileException.class, () -> Storage.checkNumerical("fifty"));
    }

    @Test
    void checkCanBeInteger_numberTooLong_throwsException() {
        Storage storage = new Storage();
        assertThrows(CoinflipFileException.class, () -> Storage.checkCanBeInteger("1234567890"));
    }

    @Test
    void checkNonNegative_validValue_doesNotThrow() throws CoinflipFileException {
        Storage storage = new Storage();
        storage.checkNonNegative(0);
        storage.checkNonNegative(10);
    }

    @Test
    void checkNonNegative_negativeValue_throwsException() {
        Storage storage = new Storage();
        assertThrows(CoinflipFileException.class, () -> Storage.checkNonNegative(-1));
    }
}
