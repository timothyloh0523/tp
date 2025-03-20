package seedu.coinflip;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class CoinflipTest {
    @Test
    public void sampleTest() {
        assertTrue(true);
    }

    @Test
    public void testDefaultValues() throws SecurityException, IOException {
        Coinflip program = new Coinflip();
        assertEquals(500, program.getBalance());
        assertEquals(20, program.getBetAmount());
    }
}
