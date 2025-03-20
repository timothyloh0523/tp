package seedu.coinflip;

import java.util.Scanner;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Coinflip {
    private static String filePath = "./src/main/java/data/coinflip.csv";
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

    private void setupFile(){
        File userData = new File(filePath);
        try {
            if (!userData.exists()) {
                Files.createDirectories(Paths.get("./src/main/java/data"));
                Files.createFile(Paths.get(filePath));
            }
        } catch (IOException E) {
            System.out.println("Error setting up file.");
        }
    }

    private void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String data;
            reader.readLine();
            while (((data = reader.readLine()) != null)) {
                try {
                    int savedBalance = Integer.parseInt(data.trim());
                    balance = savedBalance;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid integer");
                }
            }
            System.out.println("Data successfully loaded");
        } catch (IOException e) {
            System.out.println("Error loading data");
        }
    }

    private void saveToFile() {
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write("Balance\n");
            writer.write(balance + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving to .csv file");
        }
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
        setupFile();
        loadFromFile();
        boolean isExit = false;
        while (!isExit) {
            if (!in.hasNextLine()) {
                System.out.println("No input provided. Exiting...");
                break;
            }
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
                saveToFile();
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
