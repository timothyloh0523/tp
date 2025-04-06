package seedu.coinflip.utils.animation;

import seedu.coinflip.utils.exceptions.CoinflipException;

/**
 * Abstract class containing shared logic for delaying program for fixed durations.
 */

public abstract class Animation {
    
    /**
     * Stalls program for given duration (in milliseconds).
     *
     * @param duration Duration to stall program by
     * @throws CoinflipException if program delay is interrupted
     */
    protected static void sleep(int duration) throws CoinflipException {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            throw new CoinflipException(CoinflipException.ANIMATION_ERROR);
        }
    }
}
