package seedu.coinflip.utils.userdata;

/**
 * Helper class which stores all user data for Coinflip,
 * specifically data which is saved in a save file.
 */
public class UserData {
    public int balance = 500;
    public int betAmount = 20;

    public int winCount = 0;
    public int loseCount = 0;
    public int totalWon = 0;
    public int totalLost = 0;

    public int currentWinStreak = 0;
    public int currentLoseStreak = 0;
    public int highestWinStreak = 0;
    public int highestLoseStreak = 0;

    /**
     * Constructs a UserData object with the default values.
     */
    public UserData() {

    }
}
