package seedu.coinflip;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.File;

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
    }

    //@@author CRL006
    @Test
    public void changeBetAmount() throws SecurityException, IOException {
        Coinflip coinflip = new Coinflip();
        coinflip.run(new String[]{"change", "50"});
        assertEquals(50, coinflip.getBetAmount());
    }

    //@@author CRL006
    @Test
    public void setupFile() throws SecurityException, IOException{
        Coinflip coinflip = new Coinflip();
        coinflip.run(new String[]{});
        File file = new File("./data/coinflip.csv");
        assertTrue(file.exists());
    }
}
