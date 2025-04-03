package seedu.coinflip.utils.command;

import seedu.coinflip.utils.exceptions.CoinflipException;
import seedu.coinflip.utils.logger.CoinflipLogger;
import seedu.coinflip.utils.printer.Printer;

/**
 * Handles the command to exit the program.
 */
public class ExitCommand extends Command {

    private static final Integer NUMBER_OF_WORDS = 1;
    private final String[] words;

    public ExitCommand(String[] words) {
        this.words = words;
    }

    @Override
    // @@author HTY2003
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
