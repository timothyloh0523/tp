package seedu.coinflip.utils.animation;

import seedu.coinflip.utils.exceptions.CoinflipException;

/**
 * Helper class for scanning word animations, which displays a given message word-by-word.
 * Used by Printer.
 */

public class ScanningWordAnimation extends Animation {
    private static final int SLEEP_DURATION_MS = 300;

    /**
     * Prints message word-by-word.
     *
     * @param message Message to be displayed word-by-word
     * @throws CoinflipException if program sleep is interrupted
     */
    public static void animate(String message) throws CoinflipException {
        String[] values = message.split(" ");

        for (String value : values) {
            System.out.print(value);
            System.out.print(" ");
            sleep(SLEEP_DURATION_MS);
        }

        System.out.println();
        sleep(SLEEP_DURATION_MS);
    }
}
