package seedu.coinflip.utils.achievement;

import seedu.coinflip.utils.userdata.UserData;

//@@author CRL006
public class LoseCountAchievement extends Achievement {
    public LoseCountAchievement() {

    }

    public void update(UserData userData, Boolean outcome) {
        if (outcome) {
            return;
        }

        int loseCount = userData.loseCount + 1;

        switch (loseCount) {
        case 5:
        case 10:
        case 20:
        case 50:
        case 100:
            setToBePrinted(true);
            setPrintMessage("Uh-oh! I mean, congratulations, " +
                    "you have lost a total of " +
                    loseCount +
                    " coin flips so far! Just one more!");
            break;
        default:
            break;
        }
    }
}
