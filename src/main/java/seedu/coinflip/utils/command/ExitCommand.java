package seedu.coinflip.utils.command;

import seedu.coinflip.utils.exceptions.CoinflipException;
import seedu.coinflip.utils.logger.CoinflipLogger;
import seedu.coinflip.utils.printer.Printer;

/**
 * Helper class for exit command logic:
 * printing goodbye message.
 */
public class ExitCommand extends Command {

    private static final Integer NUMBER_OF_WORDS = 1;
    private final String[] words;

    /**
     * Constructs ExitCommand object.
     *
     * @param words List of words in user command
     */
    public ExitCommand(String[] words) {
        this.words = words;
    }

    // @@author HTY2003

    /**
     * Executes exit command logic: printing a goodbye message.
     * The actual exit of the program is handled in Coinflip class.
     *
     * @throws CoinflipException if command is invalid
     */
    @Override
    public void execute() throws CoinflipException {
        CoinflipLogger.info("Executing exit command");

        checkNumberOfWords(words);
        Printer.printBye();

        CoinflipLogger.info("Exit command execution completed");
    }

    private static void checkNumberOfWords(String[] words) throws CoinflipException {
        if (words.length != NUMBER_OF_WORDS) {
            CoinflipLogger.warning("Invalid command format");
            throw new CoinflipException(CoinflipException.EXIT_INVALID_FORMAT);
        }
    }
}
