package seedu.coinflip;

import java.util.Scanner;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import seedu.coinflip.utils.printer.Printer;

public class Coinflip {
    private static String filePath = "./data/coinflip.csv";
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

    private void setupFile() {
        File userData = new File(filePath);
        try {
            if (!userData.exists()) {
                Files.createDirectories(Paths.get("./data"));
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
        Printer.printWelcome();
        setupFile();
        loadFromFile();

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
            case "flip":
                if (words.length != 2) {
                    System.out.println("Please follow the format: Flip <Heads>/<Tails>");
                } else {
                    bet(words[1]);
                }
                break;
            case "help":
                Printer.printHelp();
                break;
            case "exit":
                Printer.printBye();
                saveToFile();
                isExit = true;
                break;
            default:
                Printer.printInvalidCommand();
                break;
            }
        }
    }

    public void bet(String words) {
        balance -= getBetAmount();
        System.out.println("Your current bet amount is: " + getBetAmount() + ".");
        Random random = new Random();
        String coinFlip = random.nextBoolean() ? "Heads" : "Tails";
        System.out.println(coinFlip);
        if (coinFlip.equalsIgnoreCase(words)) {
            System.out.println("Correct! You won: " + betAmount + ".");
            balance += 2 * getBetAmount();
        } else {
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
