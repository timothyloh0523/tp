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
            default:
                System.out.println("Invalid command!");
                break;
            }
        }
    }
}
