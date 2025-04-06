package seedu.coinflip.utils.command;

import seedu.coinflip.utils.exceptions.CoinflipException;
import seedu.coinflip.utils.logger.CoinflipLogger;
import seedu.coinflip.utils.printer.Printer;

//@@author OliverQiL

/**
 * Helper class for help command logic:
 * printing all available commands.
 */
public class HelpCommand extends Command {

    private static final Integer NUMBER_OF_WORDS = 1;
    private final String[] words;

    /**
     * Constructs HelpCommand object.
     *
     * @param words List of words in user command
     */
    public HelpCommand(String[] words) {
        this.words = words;
    }

    /**
     * Executes helper command logic: printing all available commands.
     *
     * @throws CoinflipException if command is invalid
     */
    @Override
    public void execute() throws CoinflipException {
        CoinflipLogger.info("Executing help command");

        checkNumberOfWords(words);
        Printer.printHelp();

        CoinflipLogger.info("Finished executing help command");
    }

    private static void checkNumberOfWords(String[] words) throws CoinflipException {
        if (words.length != NUMBER_OF_WORDS) {
            CoinflipLogger.warning("Invalid command format");
            throw new CoinflipException(CoinflipException.HELP_INVALID_FORMAT);
        }
    }
}
