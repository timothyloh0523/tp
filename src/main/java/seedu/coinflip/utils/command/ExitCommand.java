package seedu.coinflip.utils.command;

import seedu.coinflip.Coinflip;
import seedu.coinflip.utils.exceptions.CoinflipFileException;
import seedu.coinflip.utils.printer.Printer;

/**
 * Handles the command to exit the program.
 */
public class ExitCommand extends Command {
    private final Coinflip coinflip;

    public ExitCommand(Coinflip coinflip) {
        this.coinflip = coinflip;
    }

    @Override
    // @@author HTY2003
    public void execute() {
        Printer.printBye();
        try {
            coinflip.saveToFile();
        } catch (CoinflipFileException e) {
            Printer.printException(e);
        }
    }
}
