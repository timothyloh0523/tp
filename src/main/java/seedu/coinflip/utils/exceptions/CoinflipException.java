package seedu.coinflip.utils.exceptions;

//@@author HTY2003

public class CoinflipException extends Exception {
    public static final String ANIMATION_ERROR = "Sorry! My animation timer has stopped working. " +
            "Let's proceed.";

    public static final String CHANGE_INVALID_FORMAT = "Invalid command format!\n " +
            "Please follow this format: change <number representing new bet amount>\n" +
            "<number representing new bet amount> should be a positive integer\n" +
            "typed in numerals (e.g. 50, not fifty) which does not exceed your current coin balance.";

    public static final String CHANGE_BET_AMOUNT_INVALID = "Invalid bet amount!\n" +
            "Please provide a positive integer " +
            "typed in numerals (e.g. 50, not fifty) " +
            "which does not exceed your current coin balance.";

    public static final String CHANGE_BET_AMOUNT_EXCEEDS_BALANCE = "Your desired bet amount is " +
            "more than you currently have! " +
            "Please choose another bet amount.";

    public static final String CHANGE_BET_AMOUNT_TOO_LARGE = "Your desired bet amount is too large! " +
            "Please choose a smaller bet amount.";

    public static final String CHECK_INVALID_FORMAT = "Invalid command format! " +
            "Please follow this format: check <bet>/<balance>/<history>";

    public static final String EXIT_INVALID_FORMAT = "Invalid command format! " +
            "Please follow this format: exit";

    public static final String RESET_INVALID_FORMAT = "Invalid command format! " +
            "Please follow this format: reset";

    public static final String HELP_INVALID_FORMAT = "Invalid command format! " +
            "Please follow this format: help";

    public static final String FLIP_INVALID_FORMAT = "Invalid command format! " +
            "Please follow this format: flip <heads>/<tails>";

    public static final String FLIP_BET_AMOUNT_EXCEEDS_BALANCE = "You are betting more coins " +
            "than you currently have! " +
            "Please choose another bet amount.";

    //@@author timothyloh0523
    public static final String INVALID_COMMAND = "You have entered an unrecognised command! " +
            "Please type \"help\" for a list of possible commands.";

    public static final String STREAK_LOADING_ERROR = "Streak data not available.";

    //@@author HTY2003
    public static String message = "";

    public CoinflipException() {

    }

    public CoinflipException(String message) {
        CoinflipException.message = message;
    }
}
