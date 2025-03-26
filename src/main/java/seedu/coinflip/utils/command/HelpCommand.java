package seedu.coinflip.utils.command;

import seedu.coinflip.utils.printer.Printer;

/**
 * Displays the list of commands for the user.
 */
public class HelpCommand extends Command {

    public HelpCommand() {}

    public void execute() {
        Printer.printHelp();
    }
}
