package seedu.coinflip.utils.printer;

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
}
