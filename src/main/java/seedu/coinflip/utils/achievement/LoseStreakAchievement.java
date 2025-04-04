package seedu.coinflip.utils.achievement;

import seedu.coinflip.utils.userdata.UserData;

//@@author CRL006
public class LoseStreakAchievement extends Achievement {
    public LoseStreakAchievement() {

    }

    public void update(UserData userData, Boolean outcome) {
        if (outcome) {
            return;
        }

        int currentLoseStreak = userData.currentLoseStreak + 1;

        if (userData.highestLoseStreak >= currentLoseStreak) {
            return;
        }

        switch (currentLoseStreak) {
        case 5:
        case 10:
        case 20:
        case 50:
        case 100:
            setToBePrinted(true);
            setPrintMessage("Uh-oh! I mean, congratulations, you achieved a " +
                    currentLoseStreak +
                    "-loss streak for the first time! Keep going!");
            break;
        default:
            break;
        }
    }
}
