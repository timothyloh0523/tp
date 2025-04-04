package seedu.coinflip.utils.achievement;

import seedu.coinflip.utils.userdata.UserData;

//@@author CRL006
public class WinStreakAchievement extends Achievement {
    public WinStreakAchievement() {

    }

    public void update(UserData userData, Boolean outcome) {
        if (!outcome) {
            return;
        }
        
        int currentWinStreak = userData.currentWinStreak + 1;

        if (userData.highestWinStreak >= currentWinStreak)
            return;

        switch (currentWinStreak) {
        case 1:
        case 5:
        case 10:
        case 20:
        case 50:
        case 100:
            setToBePrinted(true);
            setPrintMessage("Congratulations, you achieved a " +
                    currentWinStreak +
                    "-win streak for the first time! Keep it up!");
            break;
        default:
            break;
        }
    }
}
