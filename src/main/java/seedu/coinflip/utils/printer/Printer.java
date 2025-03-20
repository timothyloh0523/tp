package seedu.coinflip.utils.printer;

import seedu.coinflip.utils.exceptions.CoinflipException;

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

    public static void printNotEnoughCoins() {
        System.out.println("You are betting more coins than you currently have! Please change your bet amount.");
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
                \n\ncheck balance - Shows your remaining balance.\
                \ncheck bet - Shows your current bet amount.\
                \nchange <amount> - Changes your bet amount.\
                \nflip <heads/tails> - Bet on a coin flip being heads or tails.\
                \nexit - Exits the application.\
                \nhelp - Shows this help message.\
                \n\nFor more information, please visit our User Guide: <insert user guide URL>""");
    }

    public static void printException(CoinflipException e) {
        System.out.println(e.message);
    }
}
