package seedu.coinflip;

import java.util.Scanner;

public class Coinflip {
    private static int balance = 500;
    private static int betAmount = 20;

    /**
     * Main entry-point for the java.seedu.coinflip.Coinflip application.
     */
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to Coinflip!");

        boolean isExit = false;
        while (!isExit)
        {
            String input = in.nextLine();
            String[] words = input.split("\\s+");
            switch (words[0]) {
            case "check":
                if (words.length > 1 && words[1].equals("balance")) {
                    System.out.println("Your remaining balance is: " + balance);
                }
                if (words.length > 1 && words[1].equals("bet")) {
                    System.out.println("Your current bet amount is: " + betAmount);
                }
                break;
            case "change":
                try {
                    betAmount = Integer.parseInt(words[1]);
                    System.out.println("Changed your bet amount to " + betAmount + "!");
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Please provide a valid bet amount!");
                }
                break;
            case "exit":
                System.out.println("Thank you for using Coinflip. Goodbye!");
                isExit = true;
                break;
            case "help":
                System.out.println("Here are the commands you can use:");
                System.out.println("\ncheck balance - Shows your remaining balance.");
                System.out.println("check bet - Shows your current bet amount.");
                System.out.println("change <amount> - Changes your bet amount.");
                System.out.println("flip <heads/tails> - Bet on a coin flip being heads or tails.");
                System.out.println("exit - Exits the application.");
                System.out.println("help - Shows this help message.");
                System.out.println("\nFor more information, please visit our User Guide: <insert user guide URL>");
                break;
            default:
                System.out.println("Invalid command!");
                break;
            }
        }
    }
}
