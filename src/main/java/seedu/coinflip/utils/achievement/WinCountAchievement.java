package seedu.coinflip.utils.achievement;

import seedu.coinflip.utils.userdata.UserData;

//@@author CRL006
public class WinCountAchievement extends Achievement {
    public WinCountAchievement() {

    }

    public void update(UserData userData, Boolean outcome) {
        if (!outcome)
            return;

        int winCount = userData.winCount + 1;

        switch (winCount) {
        case 1:
        case 5:
        case 10:
        case 20:
        case 50:
        case 100:
            setToBePrinted(true);
            setPrintMessage("Congratulations, you have won a total " +
                    winCount +
                    " coin flips so far! Keep it up!");
            break;
        default:
            break;
        }
    }
}
