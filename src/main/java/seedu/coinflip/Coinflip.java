package seedu.coinflip;

import java.util.Scanner;

public class Coinflip {
    private static int balance = 500;

    /**
     * Main entry-point for the java.seedu.coinflip.Coinflip application.
     */
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.println("Welcome to Coinflip!");

        while (true)
        {
            String input = in.nextLine();
            switch (input) {
            case "check balance":
                System.out.println("Your remaining balance is: " + balance);
                break;
            case "exit":
                System.out.println("Thank you for using Coinflip. Goodbye!");
                break;
            default:
                break;
            }
        }
    }
}
