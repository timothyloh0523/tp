package seedu.coinflip.utils.command;

import seedu.coinflip.Coinflip;
import seedu.coinflip.utils.exceptions.CoinflipFileException;
import seedu.coinflip.utils.logger.CoinflipLogger;
import seedu.coinflip.utils.printer.Printer;

/**
 * Handles the command to exit the program.
 */
public class ExitCommand extends Command {
    private final Coinflip coinflip;

    public ExitCommand(Coinflip coinflip) {
        this.coinflip = coinflip;
        CoinflipLogger.fine("ExitCommand created");
    }

    @Override
    // @@author HTY2003
    public void execute() {
        CoinflipLogger.info("Executing exit command");
        Printer.printBye();
        try {
            coinflip.saveToFile();
            CoinflipLogger.info("Game state saved successfully");
        } catch (CoinflipFileException e) {
            CoinflipLogger.severe("Failed to save game state: " + e.message);
            Printer.printException(e);
        }
        CoinflipLogger.info("Exit command execution completed");
    }
}
