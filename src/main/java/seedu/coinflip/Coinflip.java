package seedu.coinflip;

import java.util.Scanner;

public class Coinflip {
    private int balance = 500;

    /**
     * Main entry-point for the java.seedu.coinflip.Coinflip application.
     */
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.println("Welcome to Coinflip!");

        while (true)
        {
            String input = in.nextLine();

            if (input.equals("exit")) {
                System.out.println("Thank you for using Coinflip. Goodbye!");
                break;
            }
        }
    }
}
