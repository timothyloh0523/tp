package seedu.coinflip.utils.animation;

import seedu.coinflip.utils.exceptions.CoinflipException;

public class ScanningWordAnimation extends Animation {
    private static final int SLEEP_DURATION_MS = 300;

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
