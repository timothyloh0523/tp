package seedu.coinflip.utils.parser;

import seedu.coinflip.Coinflip;

import seedu.coinflip.utils.exceptions.CoinflipException;
import seedu.coinflip.utils.command.Command;
import seedu.coinflip.utils.command.ChangeCommand;
import seedu.coinflip.utils.command.CheckCommand;
import seedu.coinflip.utils.command.ExitCommand;
import seedu.coinflip.utils.command.FlipCommand;
import seedu.coinflip.utils.command.HelpCommand;
import seedu.coinflip.utils.logger.CoinflipLogger;

/**
 * Handles the parsing of user inputs, and converts them
 * into executable commands for the program.
 */
public class Parser {
    private Coinflip coinflip;

    public Parser(Coinflip coinflip) {
        this.coinflip = coinflip;
        CoinflipLogger.info("Parser Initialized");
    }

    // @@author HTY2003
    public Command parseUserInput(String input) throws CoinflipException {
        CoinflipLogger.info("Parsing user input: " + input);

        String[] words = input.split("\\s+");
        CoinflipLogger.fine("Split input into " + words.length + "words");

        switch (words[0]) {
        case "check":
            CoinflipLogger.fine("Creating CheckCommand");
            return new CheckCommand(words, coinflip);
        case "change":
            CoinflipLogger.fine("Creating ChangeCommand");
            return new ChangeCommand(words, coinflip);
        case "flip":
            CoinflipLogger.fine("Creating FlipCommand");
            return new FlipCommand(words, coinflip);
        case "help":
            CoinflipLogger.fine("Creating HelpCommand");
            return new HelpCommand();
        case "exit":
            CoinflipLogger.fine("Creating ExitCommand");
            return new ExitCommand(coinflip);
        default:
            CoinflipLogger.warning("Invalid command: " + words[0]);
            throw new CoinflipException(CoinflipException.INVALID_COMMAND);
        }
    }
}
