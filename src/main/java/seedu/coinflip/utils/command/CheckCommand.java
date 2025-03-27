package seedu.coinflip.utils.command;

import seedu.coinflip.utils.exceptions.CoinflipException;
import seedu.coinflip.Coinflip;
import seedu.coinflip.utils.logger.CoinflipLogger;

/**
 * Handles the command to check either the bet or balance amount,
 * depending on user input.
 */
public class CheckCommand extends Command {
    private final String[] words;
    private final Coinflip coinflip;

    public CheckCommand(String[] words, Coinflip coinflip) {
        this.words = words;
        this.coinflip = coinflip;
    }

    @Override
    public void execute() throws CoinflipException {
        CoinflipLogger.info("Executing check command");
        coinflip.check(words);
    }
}
