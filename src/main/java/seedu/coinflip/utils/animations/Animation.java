package seedu.coinflip.utils.animations;

import seedu.coinflip.utils.exceptions.CoinflipException;

public class Animation {
    protected static void sleep(int duration_ms) throws CoinflipException {
        try {
            Thread.sleep(duration_ms);
        } catch (InterruptedException e) {
            throw new CoinflipException(CoinflipException.ANIMATION_ERROR);
        }
    }
}
