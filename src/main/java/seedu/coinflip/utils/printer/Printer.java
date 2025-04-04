package seedu.coinflip.utils.printer;

import seedu.coinflip.utils.exceptions.CoinflipException;

public class Printer {
    private static final int NUM_OF_UNDERSCORES = 50;
    private static final String USER_GUIDE_URL = "https://ay2425s2-cs2113-f13-1.github.io/tp/UserGuide.html";

    //@@author HTY2003
    public static void printUnderscoreLine() {
        System.out.println(new String(new char[NUM_OF_UNDERSCORES]).replace("\0", "_"));
    }

    //@@author HTY2003
    public static void printWelcome() {
        System.out.println("Welcome to Coinflip!");
    }

    public static void printBye() {
        System.out.println("Thank you for using Coinflip. Goodbye!");
    }

    public static void printInvalidCommand() {
        System.out.println("Invalid command!");
    }

    public static void printException(CoinflipException e) {
        System.out.println(e.message);
    }

    public static void printLoggerFail() {
        System.out.println("Coinflip could not start due errors initializing its Java logger: ");
    }

    //@@author timothyloh0523
    public static void printBalance(int balance) {
        System.out.println("Your remaining balance is: " + balance);
    }

    //@@author timothyloh0523
    public static void printStreaks(int winStreak, int loseStreak) throws CoinflipException {
        if (winStreak == 0 && loseStreak == 0) {
            System.out.println("You have no active win or loss streaks!");
        } else if (winStreak > 0) {
            System.out.println("Your current win streak is " + winStreak + "!");
        } else if (loseStreak > 0) {
            System.out.println("Your current loss streak is " + loseStreak + "!");
        } else {
            throw new CoinflipException(CoinflipException.STREAK_LOADING_ERROR);
        }
    }

    //@@author CRL006
    public static void printStats(int wins, int losses, int totalWon, int totalLost) {
        if (wins == 0 && losses == 0) {
            System.out.println("You have not flipped a coin before! " +
                    "Type in 'flip heads' or 'flip tails' to play!");
        } else {
            float winPercentage = 100 * (float) wins / (float) (wins + losses);
            System.out.println("Win Percentage: ");
            System.out.printf("%.2f", winPercentage);
            System.out.print("%" +
                    "\nWins: " + wins +
                    "\nLosses: " + losses +
                    "\nTotal Won: " + totalWon +
                    "\nTotal Lost: " + totalLost +
                    "\nWin Percentage: ");
        }
    }

    public static void printBetAmount(int betAmount) {
        System.out.println("Your current bet amount is: " + betAmount);
    }

    //@@author wongyihao0506
    private static void sleepForAnimation() throws CoinflipException {
        try {
            Thread.sleep(350);
        } catch (InterruptedException e) {
            throw new CoinflipException(CoinflipException.ANIMATION_ERROR);
        }
    }

    //@@author wongyihao0506
    private static void printLoadingAnimation() throws CoinflipException {
        System.out.print('-');
        sleepForAnimation();
        System.out.print("\b\\");
        sleepForAnimation();
        System.out.print("\b|");
        sleepForAnimation();
        System.out.print("\b/");
        sleepForAnimation();
        System.out.print("\b-");
        sleepForAnimation();
        System.out.print("\b");
    }

    //@@author wongyihao0506
    public static void printFlipOutcome(String actualFlip, Boolean outcome, int betAmount) throws CoinflipException {
        printLoadingAnimation();
        String outcomeMessage = outcome ? "You won " : "You lost ";
        System.out.println(actualFlip + "! " + outcomeMessage + betAmount + " coins.");
    }

    public static void printNewSaveFileNote() {
        System.out.println("Note: No save file found. A new one will be created for you.");
    }

    //@@author OliverQiL
    public static void printHelp() {
        System.out.println("""
                Here are the commands you can use:\
                \n\ncheck balance - Shows your remaining balance.\
                \ncheck bet - Shows your current bet amount.\
                \ncheck history - Shows your game history.\
                \nchange <amount> - Changes your bet amount.\
                \nflip <heads/tails> - Bet on a coin flip being heads or tails.\
                \nexit - Exits the application.\
                \nhelp - Shows this help message.\
                \n\nFor more information, please visit our User Guide: \n"""
                + USER_GUIDE_URL);
    }
}
