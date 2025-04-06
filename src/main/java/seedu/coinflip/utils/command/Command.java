package seedu.coinflip.utils.command;

import seedu.coinflip.utils.exceptions.CoinflipException;

/**
 * Abstract class which defines the methods all Command objects must have.
 */
public abstract class Command {
    public abstract void execute() throws CoinflipException;
}
