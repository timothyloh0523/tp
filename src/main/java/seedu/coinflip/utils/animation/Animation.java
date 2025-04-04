package seedu.coinflip.utils.animation;

import seedu.coinflip.utils.exceptions.CoinflipException;

public class Animation {
    protected static void sleep(int duration) throws CoinflipException {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            throw new CoinflipException(CoinflipException.ANIMATION_ERROR);
        }
    }
}
