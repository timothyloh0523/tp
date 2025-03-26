package seedu.coinflip.utils.parser;

import seedu.coinflip.Coinflip;

import seedu.coinflip.utils.exceptions.CoinflipException;
import seedu.coinflip.utils.command.Command;
import seedu.coinflip.utils.command.ChangeCommand;
import seedu.coinflip.utils.command.CheckCommand;
import seedu.coinflip.utils.command.ExitCommand;
import seedu.coinflip.utils.command.FlipCommand;
import seedu.coinflip.utils.command.HelpCommand;


public class Parser {
    private Coinflip coinflip;

    public Parser(Coinflip coinflip) {
        this.coinflip = coinflip;
    }

    // @@author HTY2003
    public Command parseUserInput(String input) throws CoinflipException {

        String[] words = input.split("\\s+");

        switch (words[0]) {
        case "check":
            return new CheckCommand(words, coinflip);
        case "change":
            return new ChangeCommand(words, coinflip);
        case "flip":
            return new FlipCommand(words, coinflip);
        case "help":
            return new HelpCommand();
        case "exit":
            return new ExitCommand(coinflip);
        default:
            throw new CoinflipException(CoinflipException.INVALID_COMMAND);
        }
    }
}