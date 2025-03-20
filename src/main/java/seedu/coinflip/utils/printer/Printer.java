package seedu.coinflip.utils.printer;

import seedu.coinflip.utils.exceptions.CoinFlipException;

public class Printer {

    public static void printWelcome() {
        System.out.println("Welcome to Coinflip!");
    }

    public static void printBye() {
        System.out.println("Thank you for using Coinflip. Goodbye!");
    }

    public static void printInvalidCommand() {
        System.out.println("Invalid command!");
    }

    public static void printBalance(int balance) {
        System.out.println("Your remaining balance is: " + balance);
    }

    public static void printBetAmount(int betAmount) {
        System.out.println("Your current bet amount is: " + betAmount);
    }

    public static void printFlipOutcome(String coinFlip, Boolean outcome, int betAmount) {
        String outcomeMessage = outcome ? "You won " : "You lost ";
        System.out.println(coinFlip + "! " + outcomeMessage + betAmount + " coins.");
    }

    public static void printNewSaveFileNote() {
        System.out.println("Note: No save file found. A new one will be created for you.");
    }

    public static void printHelp() {
        System.out.println("""
                Here are the commands you can use:\
                
                check balance - Shows your remaining balance.\
                check bet - Shows your current bet amount.\
                change <amount> - Changes your bet amount.\
                flip <heads/tails> - Bet on a coin flip being heads or tails.\
                exit - Exits the application.\
                help - Shows this help message.\
                
                For more information, please visit our User Guide: <insert user guide URL>""");
    }

    public static void printException(CoinFlipException e) {
        System.out.println(e.message);
    }
}
