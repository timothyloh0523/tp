package seedu.coinflip.utils.printer;

import seedu.coinflip.utils.animations.LoadingAnimation;
import seedu.coinflip.utils.animations.ScanningWordAnimation;
import seedu.coinflip.utils.exceptions.CoinflipException;
import seedu.coinflip.utils.userdata.UserData;

public class Printer {
    private static final int NUMBER_OF_UNDERSCORES = 50;
    private static final String USER_GUIDE_URL = "https://ay2425s2-cs2113-f13-1.github.io/tp/UserGuide.html";

    //@@author HTY2003
    public static void printUnderscoreLine() {
        System.out.println(new String(new char[NUMBER_OF_UNDERSCORES]).replace("\0", "_"));
    }

    //@@author HTY2003
    public static void printWelcome() {
        System.out.println("Welcome to Coinflip!");
    }

    //@@author HTY2003
    public static void printBye() {
        System.out.println("Thank you for using Coinflip. Goodbye!");
    }

    //@@author HTY2003
    public static void printException(CoinflipException e) {
        System.out.println(e.message);
    }

    //@@author timothyloh0523
    public static void printFlipSummary(UserData userData) throws CoinflipException {
        Printer.printBetAmount(userData.betAmount);
        Printer.printBalance(userData.balance);
        Printer.printStreaks(userData.winStreak, userData.loseStreak);
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

    //@@author CRL006
    public static void printNewSaveFileNote() {
        System.out.println("Note: No save file found. A new one will be created for you.");
    }

    //@@author wongyihao0506
    public static void printUnlockFiveWinStreakAchievement() {
        System.out.println("Coinflip Beginner unlocked! " +
                "\nCongratulations! You achieved a 5-win streak!" +
                "\nKeep it up!!");
    }

    public static void printUnlockTenWinStreakAchievement() {
        System.out.println("Coinflip Apprentice unlocked! " +
                "\nCongratulations! You achieved a 10-win streak!" +
                "\nKeep it up!!");
    }

    public static void printUnlockTwentyWinStreakAchievement() {
        System.out.println("Coinflip Expert unlocked! " +
                "\nCongratulations! You achieved a 20-win streak!" +
                "\nKeep it up!!");
    }

    public static void printUnlockFiftyWinStreakAchievement() {
        System.out.println("Coinflip Master unlocked! " +
                "\nCongratulations! You achieved a 50-win streak!" +
                "\nKeep it up!!");
    }

    public static void printUnlockHundredWinStreakAchievement() {
        System.out.println("Coinflip Legend unlocked! " +
                "\nCongratulations! You achieved a 100-win streak!" +
                "\nKeep it up!!");
    }

    public static void printUnlockFiveFiveWinStreakAchievement() {
        System.out.println("Congratulations! You have unlocked the 5-win streak achievement 5 times!");
    }

    public static void printUnlockTenFiveWinStreakAchievement() {
        System.out.println("Congratulations! You have unlocked the 5-win streak achievement 10 times!");
    }

    public static void printUnlockTwentyFiveWinStreakAchievement() {
        System.out.println("Congratulations! You have unlocked the 5-win streak achievement 20 times!");
    }

    public static void printUnlockFiftyFiveWinStreakAchievement() {
        System.out.println("Congratulations! You have unlocked the 5-win streak achievement 50 times!");
    }

    public static void printUnlockHundredFiveWinStreakAchievement() {
        System.out.println("Congratulations! You have unlocked the 5-win streak achievement 100 times!");
    }

    public static void printUnlockFiveTenWinStreakAchievement() {
        System.out.println("Congratulations! You have unlocked the 10-win streak achievement 5 times!");
    }

    public static void printUnlockTenTenWinStreakAchievement() {
        System.out.println("Congratulations! You have unlocked the 10-win streak achievement 10 times!");
    }

    public static void printUnlockTwentyTenWinStreakAchievement() {
        System.out.println("Congratulations! You have unlocked the 10-win streak achievement 20 times!");
    }

    public static void printUnlockFiftyTenWinStreakAchievement() {
        System.out.println("Congratulations! You have unlocked the 10-win streak achievement 50 times!");
    }

    public static void printUnlockHundredTenWinStreakAchievement() {
        System.out.println("Congratulations! You have unlocked the 10-win streak achievement 100 times!");
    }

    public static void printUnlockFiveTwentyWinStreakAchievement() {
        System.out.println("Congratulations! You have unlocked the 20-win streak achievement 5 times!");
    }

    public static void printUnlockTenTwentyWinStreakAchievement() {
        System.out.println("Congratulations! You have unlocked the 20-win streak achievement 10 times!");
    }

    public static void printUnlockTwentyTwentyWinStreakAchievement() {
        System.out.println("Congratulations! You have unlocked the 20-win streak achievement 20 times!");
    }

    public static void printUnlockFiftyTwentyWinStreakAchievement() {
        System.out.println("Congratulations! You have unlocked the 20-win streak achievement 50 times!");
    }

    public static void printUnlockHundredTwentyWinStreakAchievement() {
        System.out.println("Congratulations! You have unlocked the 20-win streak achievement 100 times!");
    }

    public static void printUnlockFiveFiftyWinStreakAchievement() {
        System.out.println("Congratulations! You have unlocked the 50-win streak achievement 5 times!");
    }

    public static void printUnlockTenFiftyWinStreakAchievement() {
        System.out.println("Congratulations! You have unlocked the 50-win streak achievement 10 times!");
    }

    public static void printUnlockTwentyFiftyWinStreakAchievement() {
        System.out.println("Congratulations! You have unlocked the 50-win streak achievement 20 times!");
    }

    public static void printUnlockFiftyFiftyWinStreakAchievement() {
        System.out.println("Congratulations! You have unlocked the 50-win streak achievement 50 times!");
    }

    public static void printUnlockHundredFiftyWinStreakAchievement() {
        System.out.println("Congratulations! You have unlocked the 50-win streak achievement 100 times!");
    }

    public static void printUnlockFiveHundredWinStreakAchievement() {
        System.out.println("Congratulations! You have unlocked the 100-win streak achievement 5 times!");
    }

    public static void printUnlockTenHundredWinStreakAchievement() {
        System.out.println("Congratulations! You have unlocked the 100-win streak achievement 10 times!");
    }

    public static void printUnlockTwentyHundredWinStreakAchievement() {
        System.out.println("Congratulations! You have unlocked the 100-win streak achievement 20 times!");
    }

    public static void printUnlockFiftyHundredWinStreakAchievement() {
        System.out.println("Congratulations! You have unlocked the 100-win streak achievement 50 times!");
    }

    public static void printUnlockHundredHundredWinStreakAchievement() {
        System.out.println("Congratulations! You have unlocked the 100-win streak achievement 100 times!");
    }

    public static void printBetAmount(int betAmount) {
        System.out.println("Your current bet amount is: " + betAmount);
    }

    //@@author wongyihao0506
    public static void printFlipOutcome(String actualFlip, Boolean outcome, int betAmount) throws CoinflipException {
        LoadingAnimation.animate("Flipping coin...");
        String outcomeString = outcome ? "You won " : "You lost ";
        String outcomeMessage = actualFlip + "! " + outcomeString + betAmount + " coins.";
        ScanningWordAnimation.animate(outcomeMessage);
    }

    //@@author OliverQiL
    public static void printLoggerFail() {
        System.out.println("Coinflip could not start due errors initializing its Java logger: ");
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
