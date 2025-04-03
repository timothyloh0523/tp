package seedu.coinflip.utils.storage;

import static org.junit.jupiter.api.Assertions.*;
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
        tempUserData.balance = 500;
        tempUserData.totalLosings = 0;
        tempUserData.totalWinnings = 0;
        tempUserData.winCount = 0;
        tempUserData.loseCount = 0;
        storage.setSaveFilePath("./src/test/testResources/storageTestFiles/validTestFile.csv");
        storage.saveData(tempUserData);
        UserData loadedData = storage.loadSave();
        assertEquals(tempUserData.balance, loadedData.balance);
        assertEquals(tempUserData.totalLosings, loadedData.totalLosings);
        assertEquals(tempUserData.totalWinnings, loadedData.totalWinnings);
        assertEquals(tempUserData.winCount, loadedData.winCount);
        assertEquals(tempUserData.loseCount, loadedData.loseCount);
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
}
