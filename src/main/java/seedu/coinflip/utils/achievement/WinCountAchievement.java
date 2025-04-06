package seedu.coinflip.utils.achievement;

import seedu.coinflip.utils.userdata.UserData;

//@@author CRL006
public class WinCountAchievement extends Achievement {
    public WinCountAchievement() {

    }

    public void update(UserData userData, Boolean outcome) {
        if (!outcome) {
            return;
        }

        int winCount = userData.winCount + 1;

        switch (winCount) {
        case 5:
            //fallthrough
        case 10:
            //fallthrough
        case 20:
            //fallthrough
        case 50:
            //fallthrough
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
