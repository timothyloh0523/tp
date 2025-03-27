package seedu.coinflip.utils.command;

import seedu.coinflip.utils.exceptions.CoinflipException;
import seedu.coinflip.Coinflip;
import seedu.coinflip.utils.logger.CoinflipLogger;

/**
 * Handles the command to flip a coin, given a certain bet amount.
 */
public class FlipCommand extends Command {
    private final String[] words;
    private final Coinflip coinflip;

    public FlipCommand(String[] words, Coinflip coinflip) {
        this.words = words;
        this.coinflip = coinflip;
        CoinflipLogger.fine("FlipCommand created");
    }

    @Override
    public void execute() throws CoinflipException {
        CoinflipLogger.info("Executing flip command");
        coinflip.bet(words);
    }
}
