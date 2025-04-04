package seedu.coinflip.utils.achievement;

import java.util.ArrayList;
import seedu.coinflip.utils.userdata.UserData;

public class MaxWinStreakAchievement {
    public static ArrayList<Achievement> achievements;
    public MaxWinStreakAchievement() {
        achievements = new ArrayList<>();
        achievements.add(new Achievement("5"));
        achievements.add(new Achievement("10"));
        achievements.add(new Achievement("20"));
        achievements.add(new Achievement("50"));
        achievements.add(new Achievement("100"));
    }

    public static String execute(int winStreak, UserData userData) {
        switch (winStreak) {
        case 5:
            if (userData.fiveWinStreak != 0) {
                achievements.get(0).unlock();
                achievements.get(0).timesCompleted = userData.fiveWinStreak;
            }
            if (!achievements.get(0).isUnlocked) {
                achievements.get(0).unlock();
                achievements.get(0).incrementTimesCompleted();
                return "5";
            } else {
                return WinsAchievement.execute(5, userData);
            }
            //fallthrough
        case 10:
            if (userData.tenWinStreak != 0) {
                achievements.get(1).unlock();
                achievements.get(1).timesCompleted = userData.tenWinStreak;
            }
            if (!achievements.get(1).isUnlocked) {
                achievements.get(1).unlock();
                achievements.get(1).incrementTimesCompleted();
                return "10";
            } else {
                return WinsAchievement.execute(10, userData);
            }
            //fallthrough
        case 20:
            if (userData.twentyWinStreak != 0) {
                achievements.get(2).unlock();
                achievements.get(2).timesCompleted = userData.twentyWinStreak;
            }
            if (!achievements.get(2).isUnlocked) {
                achievements.get(2).unlock();
                achievements.get(2).incrementTimesCompleted();
                return "20";
            } else {
                return WinsAchievement.execute(20, userData);
            }
            //fallthrough
        case 50:
            if (userData.fiftyWinStreak != 0) {
                achievements.get(3).unlock();
                achievements.get(3).timesCompleted = userData.fiftyWinStreak;
            }
            if (!achievements.get(3).isUnlocked) {
                achievements.get(3).unlock();
                achievements.get(3).incrementTimesCompleted();
                return "50";
            } else {
                return WinsAchievement.execute(50, userData);
            }
            //fallthrough
        case 100:
            if (userData.hundredWinStreak != 0) {
                achievements.get(4).unlock();
                achievements.get(4).timesCompleted = userData.hundredWinStreak;
            }
            if (!achievements.get(4).isUnlocked) {
                achievements.get(4).unlock();
                achievements.get(4).incrementTimesCompleted();
                return "100";
            } else {
                return WinsAchievement.execute(100, userData);
            }
            //fallthrough
        default:
            return "NA";
            //fallthrough
        }
    }
}
