package seedu.coinflip.utils.exceptions;

//@@author HTY2003

public class CoinflipException extends Exception {

    public static final String FLIP_INVALID_FORMAT = "Invalid command format! " +
            "Please follow this format: flip <heads>/<tails>";

    public static final String BET_AMOUNT_INVALID_FORMAT = "Please provide a valid positive number as your bet amount!";

    public static final String CHECK_INVALID_FORMAT = "Invalid command format! " +
            "Please follow this format: check <bet>/<balance>.";

    public String message = "";

    public CoinflipException() {

    }

    public CoinflipException(String message) {
        this.message = message;
    }
}
