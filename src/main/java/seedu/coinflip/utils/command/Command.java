package seedu.coinflip.utils.command;

import seedu.coinflip.utils.exceptions.CoinflipException;
import seedu.coinflip.utils.logger.CoinflipLogger;

/**
 * Abstract class to abstract all commands.
 */
public abstract class Command {
    public abstract void execute() throws CoinflipException;
}