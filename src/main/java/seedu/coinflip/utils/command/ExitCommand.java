package seedu.coinflip.utils.command;

import seedu.coinflip.utils.logger.CoinflipLogger;
import seedu.coinflip.utils.printer.Printer;

/**
 * Handles the command to exit the program.
 */
public class ExitCommand extends Command {

    public ExitCommand() {
        CoinflipLogger.fine("ExitCommand created");
    }

    @Override
    // @@author HTY2003
    public void execute() {
        CoinflipLogger.info("Executing exit command");

        Printer.printBye();

        CoinflipLogger.info("Exit command execution completed");
    }
}
