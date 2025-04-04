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

import java.util.Scanner;

/**
 * Handles the receiving and parsing of user inputs to determine
 * the appropriate command for the program to execute.
 */
public class Parser {
    private final Storage storage;
    private UserData userData;
    private Scanner scanner;
    private String input;

    //@@author timothyloh0523
    public Parser(UserData userData, Storage storage) {
        this.userData = userData;
        this.storage = storage;
        this.scanner = new Scanner(System.in);
        CoinflipLogger.info("Parser initialized");
    }

    //@@author HTY2003
    public void receiveUserInput() {
        this.input = scanner.nextLine();
        CoinflipLogger.info("Received user input: " + this.input);
    }

    // @@author HTY2003
    public Command parseUserInput() throws CoinflipException {
        String[] words = this.input.split("\\s+");
        CoinflipLogger.fine("Split input into " + words.length + "words");

        switch (words[0]) {
        case "check":
            CoinflipLogger.fine("Created CheckCommand object");
            return new CheckCommand(words, userData);
        case "change":
            CoinflipLogger.fine("Created ChangeCommand object");
            return new ChangeCommand(words, userData, storage);
        case "flip":
            CoinflipLogger.fine("Created FlipCommand object");
            return new FlipCommand(words, userData, storage);
        case "help":
            CoinflipLogger.fine("Created HelpCommand object");
            return new HelpCommand(words);
        case "exit":
            CoinflipLogger.fine("Created ExitCommand object");
            return new ExitCommand(words);
        default:
            CoinflipLogger.warning("Unknown command: " + words[0]);
            throw new CoinflipException(CoinflipException.INVALID_COMMAND);
        }
    }
}
