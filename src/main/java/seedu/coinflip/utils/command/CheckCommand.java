package seedu.coinflip.utils.command;

import seedu.coinflip.utils.exceptions.CoinflipException;
import seedu.coinflip.Coinflip;

public class CheckCommand extends Command {
    private final String[] words;
    private final Coinflip coinflip;

    public CheckCommand(String[] words, Coinflip coinflip) {
        this.words = words;
        this.coinflip = coinflip;
    }

    @Override
    public void execute() throws CoinflipException {
        coinflip.check(words);
    }
}
