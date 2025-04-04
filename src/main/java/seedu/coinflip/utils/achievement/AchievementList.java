package seedu.coinflip.utils.achievement;

import seedu.coinflip.utils.userdata.UserData;

import java.util.ArrayList;

//@@author CRL006
public class AchievementList {
    private final ArrayList<Achievement> achievements;

    public AchievementList() {
        this.achievements = new ArrayList<Achievement>();
        achievements.add(new WinStreakAchievement());
        achievements.add(new LoseStreakAchievement());
        achievements.add(new GamesAchievement());
        achievements.add(new WinCountAchievement());
        achievements.add(new LoseCountAchievement());
    }

    public void update(UserData userData, Boolean outcome) {
        for (Achievement achievement : achievements) {
            achievement.update(userData, outcome);
        }
    }

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
