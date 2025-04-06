package seedu.coinflip.utils.achievement;

import seedu.coinflip.utils.userdata.UserData;

import java.util.ArrayList;

//@@author CRL006

/**
 * Helper class representing list of all achievements user could attain,
 * to simplify updating of achievements.
 */
public class AchievementList {
    private final ArrayList<Achievement> achievements;

    /**
     * Constructs AchievementList, with array containing a
     * pre-defined list of possible Achievement objects.
     */
    public AchievementList() {
        this.achievements = new ArrayList<Achievement>();
        achievements.add(new WinStreakAchievement());
        achievements.add(new LoseStreakAchievement());
        achievements.add(new GamesAchievement());
        achievements.add(new WinCountAchievement());
        achievements.add(new LoseCountAchievement());
    }

    /**
     * Updates all achievements in list.
     *
     * @param userData User data to check for achievements
     * @param outcome  Coinflip outcome to check for achievements
     */
    public void update(UserData userData, Boolean outcome) {
        for (Achievement achievement : achievements) {
            achievement.update(userData, outcome);
        }
    }

    /**
     * Returns string of all unlocked achievements to be printed.
     *
     * @return String of all unlocked achievements to be printed
     */
    public String getPrintString() {
        String output = "";
        for (Achievement achievement : achievements) {
            if (achievement.getToBePrinted()) {
                output += achievement.getPrintMessage();
                output += "\n";
                achievement.setToBePrinted(false);
            }
        }
        return output;
    }
}
