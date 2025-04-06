package seedu.coinflip.utils.printer;

import seedu.coinflip.utils.achievement.AchievementList;
import seedu.coinflip.utils.animation.LoadingAnimation;
import seedu.coinflip.utils.animation.ScanningWordAnimation;
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
        Printer.printStreaks(userData.currentWinStreak, userData.currentLoseStreak);
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
    public static void printStats(UserData userData) {
        if (userData.winCount == 0 && userData.loseCount == 0) {
            System.out.println("You have not flipped a coin before! " +
                    "Type in 'flip heads' or 'flip tails' to play!");
        } else {
            float winPercentage = 100 * (float) userData.winCount /
                    (float) (userData.winCount + userData.loseCount);
            System.out.printf("Win Percentage:  %.2f", winPercentage);
            System.out.println("%\nWins: " + userData.winCount +
                    "\nLosses: " + userData.loseCount +
                    "\nTotal Won: " + userData.totalWon +
                    "\nTotal Lost: " + userData.totalLost +
                    "\nCurrent Win Streak: " + userData.currentWinStreak +
                    "\nHighest Win Streak: " + userData.highestWinStreak +
                    "\nCurrent Lose Streak: " + userData.currentLoseStreak +
                    "\nHighest Lose Streak: " + userData.highestLoseStreak);
        }
    }

    public static void printUnlockedAchievements(AchievementList achievementList) {
        String unlockedAchievementsString = achievementList.getPrintString();
        if (unlockedAchievementsString.isEmpty()) {
            return;
        }
        printUnderscoreLine();
        System.out.print(unlockedAchievementsString);
    }

    //@@author CRL006
    public static void printNewSaveFileNote() {
        System.out.println("Note: No save file found. A new one will be created for you.");
    }

    //@@author wongyihao0506
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
    public static void printResetSuccessful() {
        System.out.println("Your data has been reset!");
    }

    //@@author OliverQiL
    public static void printHelp() {
        System.out.println("""
                Here are the commands you can use:\
                \n\ncheck balance - Shows your remaining balance.\
                \ncheck bet - Shows your current bet amount.\
                \ncheck history - Shows your game history.\
                \nchange <new bet amount> - Changes your bet amount.\
                \nflip <heads/tails> - Bet on a coin flip being heads or tails.\
                \nexit - Exits the application.\
                \nhelp - Shows this help message.\
                \n\nFor more information, please visit our User Guide: \n"""
                + USER_GUIDE_URL);
    }
}
