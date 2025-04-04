package seedu.coinflip.utils.achievement;

import seedu.coinflip.utils.userdata.UserData;

//@@author CRL006
public class GamesAchievement extends Achievement {
    public GamesAchievement() {

    }

    public void update(UserData userData, Boolean outcome) {
        int gameCount = userData.winCount + userData.loseCount + 1;

        switch (gameCount) {
        case 1:
        case 5:
        case 10:
        case 20:
        case 50:
        case 100:
            setToBePrinted(true);
            setPrintMessage("Congratulations, you have made a total of " +
                    gameCount +
                    " coin flips so far! Keep it up!");
            break;
        default:
            break;
        }
    }
}
