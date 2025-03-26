package seedu.coinflip.utils.command;

import seedu.coinflip.utils.printer.Printer;

public class HelpCommand extends Command {

    public HelpCommand() {}

    public void execute() {
        Printer.printHelp();
    }
}
