package seedu.coinflip;


import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class Coinflip {
    private int balance = 500;
    private int betAmount = 20;

    /**
     * Constructs Coinflip object
     */
    Coinflip() {

    }

    /**
     * Returns user's coin balance
     *
     * @return user's balance in coins
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Returns the amount of coins a user bets on each coinflip
     *
     * @return user's betting amount in coins
     */
    public int getBetAmount() {
        return betAmount;
    }

    /**
     * Runs main Coinflip program, which waits for next line of user input
     * before outputting an appropriate response
     *
     * @param args Arguments included with command to start Coinflip
     */
    public void run(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to Coinflip!");

        boolean isExit = false;
        while (!isExit) {
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
            case "flip":
                if (words.length != 3){
                    System.out.println("Please follow the format: Flip <Heads>/<Tails> <Amount>");
                }
                else {
                bet(Integer.parseInt(words[1]), words[2]);
                }
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
    public void bet (int amount, String words) {
        balance -= amount;
        System.out.println("Your current bet amount is: " + betAmount + ".");
        Random random = new Random();
        boolean coinFlip = random.nextBoolean();
        String result = coinFlip ? "Heads" : "Tails";
        if (result.equalsIgnoreCase(Arrays.toString(words.toCharArray()))) {
            System.out.println("Correct! You won: " + betAmount + ".");
            balance += 2 * amount;
        }
        else {
            System.out.println("Wrong! You lost: " + betAmount + ".");
        }
    }

    /**
     * Main entry-point for the java.seedu.coinflip.Coinflip application.
     *
     * @param args Arguments included with command to start Coinflip
     */
    public static void main(String[] args) {
        Coinflip program = new Coinflip();
        program.run(args);
    }
}
