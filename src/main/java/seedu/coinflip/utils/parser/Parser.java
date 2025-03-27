package seedu.coinflip.utils.parser;

import seedu.coinflip.utils.exceptions.CoinflipException;
import seedu.coinflip.utils.command.Command;
import seedu.coinflip.utils.command.ChangeCommand;
import seedu.coinflip.utils.command.CheckCommand;
import seedu.coinflip.utils.command.ExitCommand;
import seedu.coinflip.utils.command.FlipCommand;
import seedu.coinflip.utils.command.HelpCommand;
import seedu.coinflip.utils.logger.CoinflipLogger;
import seedu.coinflip.utils.storage.Storage;
import seedu.coinflip.utils.userdata.UserData;

/**
 * Handles the parsing of user inputs, and converts them
 * into executable commands for the program.
 */
public class Parser {
    private final Storage storage;
    private UserData userData;

    public Parser(UserData userData, Storage storage) {
        this.userData = userData;
        this.storage = storage;
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
            return new CheckCommand(words, userData);
        case "change":
            CoinflipLogger.fine("Creating ChangeCommand");
            return new ChangeCommand(words, userData, storage);
        case "flip":
            CoinflipLogger.fine("Creating FlipCommand");
            return new FlipCommand(words, userData, storage);
        case "help":
            CoinflipLogger.fine("Creating HelpCommand");
            return new HelpCommand();
        case "exit":
            CoinflipLogger.fine("Creating ExitCommand");
            return new ExitCommand();
        default:
            CoinflipLogger.warning("Invalid command: " + words[0]);
            throw new CoinflipException(CoinflipException.INVALID_COMMAND);
        }
    }
}
