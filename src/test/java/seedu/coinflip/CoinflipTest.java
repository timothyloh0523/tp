package seedu.coinflip;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

class CoinflipTest {
    @Test
    public void sampleTest() {
        assertTrue(true);
    }

    //@@author HTY2003
    @Test
    public void testDefaultValues() throws SecurityException, IOException {
        Coinflip program = new Coinflip();
        assertEquals(500, program.getBalance());
        assertEquals(20, program.getBetAmount());
        assertEquals(0, program.getWinCount());
        assertEquals(0, program.getLoseCount());
        assertEquals(0, program.getTotalWon());
        assertEquals(0, program.getTotalLost());
    }

    //@@author CRL006
    @Test
    public void testBetCommand() throws SecurityException, IOException {
        Coinflip coinflip = new Coinflip();
        String testInput = "change 50\n"
                + "exit\n";
        System.setIn(new ByteArrayInputStream(testInput.getBytes()));
        coinflip.run(new String[]{});
        assertEquals(50, coinflip.getBetAmount());
    }

    //@@author CRL006
    @Test
    public void setupFile() throws SecurityException, IOException {
        Coinflip coinflip = new Coinflip();
        String testInput = "exit\n";
        System.setIn(new ByteArrayInputStream(testInput.getBytes()));
        coinflip.run(new String[]{});
        File file = new File("./data/coinflip.csv");
        assertTrue(file.exists());
    }
}
