package seedu.coinflip.utils.command;

import seedu.coinflip.utils.exceptions.CoinflipException;
import seedu.coinflip.Coinflip;

public class FlipCommand extends Command {
    private final String[] words;
    private final Coinflip coinflip;

    public FlipCommand(String[] words, Coinflip coinflip) {
        this.words = words;
        this.coinflip = coinflip;
    }

    @Override
    public void execute() throws CoinflipException {
        coinflip.bet(words);
    }
}
