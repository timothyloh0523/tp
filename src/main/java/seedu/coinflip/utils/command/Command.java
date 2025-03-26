package seedu.coinflip.utils.command;

import seedu.coinflip.utils.exceptions.CoinflipException;

/**
 * Abstract class to abstract all commands.
 */
public abstract class Command {
    public abstract void execute() throws CoinflipException;
}
