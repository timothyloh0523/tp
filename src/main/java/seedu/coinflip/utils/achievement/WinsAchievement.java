package seedu.coinflip.utils.achievement;

import seedu.coinflip.utils.userdata.UserData;

import java.util.ArrayList;

public class WinsAchievement {
    public static ArrayList<Achievement> achievements;
    public WinsAchievement() {
        achievements = new ArrayList<>();
        achievements.add(new Achievement("5"));
        achievements.add(new Achievement("10"));
        achievements.add(new Achievement("20"));
        achievements.add(new Achievement("50"));
        achievements.add(new Achievement("100"));
        achievements.add(new Achievement("5 5"));
        achievements.add(new Achievement("10 5"));
        achievements.add(new Achievement("20 5"));
        achievements.add(new Achievement("50 5"));
        achievements.add(new Achievement("100 5"));
        achievements.add(new Achievement("5 10"));
        achievements.add(new Achievement("10 10"));
        achievements.add(new Achievement("20 10"));
        achievements.add(new Achievement("50 10"));
        achievements.add(new Achievement("100 10"));
        achievements.add(new Achievement("5 20"));
        achievements.add(new Achievement("10 20"));
        achievements.add(new Achievement("20 20"));
        achievements.add(new Achievement("50 20"));
        achievements.add(new Achievement("100 20"));
        achievements.add(new Achievement("5 50"));
        achievements.add(new Achievement("10 50"));
        achievements.add(new Achievement("20 50"));
        achievements.add(new Achievement("50 50"));
        achievements.add(new Achievement("100 50"));
        achievements.add(new Achievement("5 100"));
        achievements.add(new Achievement("10 100"));
        achievements.add(new Achievement("20 100"));
        achievements.add(new Achievement("50 100"));
        achievements.add(new Achievement("100 100"));
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
            }
            achievements.get(0).incrementTimesCompleted();
            return checkFiveMilestone(achievements.get(0).timesCompleted);
            //fallthrough
        case 10:
            if (userData.tenWinStreak != 0) {
                achievements.get(1).unlock();
                achievements.get(1).timesCompleted = userData.tenWinStreak;
            }
            if (!achievements.get(1).isUnlocked) {
                achievements.get(1).unlock();
                achievements.get(1).incrementTimesCompleted();
            }
            achievements.get(1).incrementTimesCompleted();
            return checkTenMilestone(achievements.get(1).timesCompleted);
            //fallthrough
        case 20:
            if (userData.twentyWinStreak != 0) {
                achievements.get(2).unlock();
                achievements.get(2).timesCompleted = userData.twentyWinStreak;
            }
            if (!achievements.get(2).isUnlocked) {
                achievements.get(2).unlock();
                achievements.get(2).incrementTimesCompleted();
            }
            achievements.get(2).incrementTimesCompleted();
            return checkTwentyMilestone(achievements.get(2).timesCompleted);
            //fallthrough
        case 50:
            if (userData.fiftyWinStreak != 0) {
                achievements.get(3).unlock();
                achievements.get(3).timesCompleted = userData.fiftyWinStreak;
            }
            if (!achievements.get(3).isUnlocked) {
                achievements.get(3).unlock();
                achievements.get(3).incrementTimesCompleted();
            }
            achievements.get(3).incrementTimesCompleted();
            return checkFiftyMilestone(achievements.get(3).timesCompleted);
            //fallthrough
        case 100:
            if (userData.hundredWinStreak != 0) {
                achievements.get(4).unlock();
                achievements.get(4).timesCompleted = userData.hundredWinStreak;
            }
            if (!achievements.get(4).isUnlocked) {
                achievements.get(4).unlock();
                achievements.get(4).incrementTimesCompleted();
            }
            achievements.get(4).incrementTimesCompleted();
            return checkHundredMilestone(achievements.get(4).timesCompleted);
            //fallthrough
        default:
            return "NA";
            //fallthrough
        }
    }

    public static String checkFiveMilestone(int times) {
        switch(times) {
        case 5:
            if (!achievements.get(5).isUnlocked) {
                achievements.get(5).unlock();
                achievements.get(5).incrementTimesCompleted();
                return "5 5";
            }
            break;
        case 10:
            if (!achievements.get(10).isUnlocked) {
                achievements.get(10).unlock();
                achievements.get(10).incrementTimesCompleted();
                return "10 5";
            }
            break;
        case 20:
            if (!achievements.get(15).isUnlocked) {
                achievements.get(15).unlock();
                achievements.get(15).incrementTimesCompleted();
                return "20 5";
            }
            break;
        case 50:
            if (!achievements.get(20).isUnlocked) {
                achievements.get(20).unlock();
                achievements.get(20).incrementTimesCompleted();
                return "50 5";
            }
            break;
        case 100:
            if (!achievements.get(25).isUnlocked) {
                achievements.get(25).unlock();
                achievements.get(25).incrementTimesCompleted();
                return "100 5";
            }
            break;
        default:
            return "NA";
            //fallthrough
        }
        return "NA";
    }

    public static String checkTenMilestone(int times) {
        switch(times) {
        case 5:
            if (!achievements.get(6).isUnlocked) {
                achievements.get(6).unlock();
                achievements.get(6).incrementTimesCompleted();
                return "5 10";
            }
            break;
        case 10:
            if (!achievements.get(11).isUnlocked) {
                achievements.get(11).unlock();
                achievements.get(11).incrementTimesCompleted();
                return "10 10";
            }
            break;
        case 20:
            if (!achievements.get(16).isUnlocked) {
                achievements.get(16).unlock();
                achievements.get(16).incrementTimesCompleted();
                return "20 10";
            }
            break;
        case 50:
            if (!achievements.get(21).isUnlocked) {
                achievements.get(21).unlock();
                achievements.get(21).incrementTimesCompleted();
                return "50 10";
            }
            break;
        case 100:
            if (!achievements.get(26).isUnlocked) {
                achievements.get(26).unlock();
                achievements.get(26).incrementTimesCompleted();
                return "100 10";
            }
            break;
        default:
            return "NA";
            //fallthrough
        }
        return "NA";
    }

    public static String checkTwentyMilestone(int times) {
        switch(times) {
        case 5:
            if (!achievements.get(7).isUnlocked) {
                achievements.get(7).unlock();
                achievements.get(7).incrementTimesCompleted();
                return "5 20";
            }
            break;
        case 10:
            if (!achievements.get(12).isUnlocked) {
                achievements.get(12).unlock();
                achievements.get(12).incrementTimesCompleted();
                return "10 20";
            }
            break;
        case 20:
            if (!achievements.get(17).isUnlocked) {
                achievements.get(17).unlock();
                achievements.get(17).incrementTimesCompleted();
                return "20 20";
            }
            break;
        case 50:
            if (!achievements.get(22).isUnlocked) {
                achievements.get(22).unlock();
                achievements.get(22).incrementTimesCompleted();
                return "50 20";
            }
            break;
        case 100:
            if (!achievements.get(27).isUnlocked) {
                achievements.get(27).unlock();
                achievements.get(27).incrementTimesCompleted();
                return "100 20";
            }
            break;
        default:
            return "NA";
            //fallthrough
        }
        return "NA";
    }

    public static String checkFiftyMilestone(int times) {
        switch(times) {
        case 5:
            if (!achievements.get(8).isUnlocked) {
                achievements.get(8).unlock();
                achievements.get(8).incrementTimesCompleted();
                return "5 50";
            }
            break;
        case 10:
            if (!achievements.get(13).isUnlocked) {
                achievements.get(13).unlock();
                achievements.get(13).incrementTimesCompleted();
                return "10 50";
            }
            break;
        case 20:
            if (!achievements.get(18).isUnlocked) {
                achievements.get(18).unlock();
                achievements.get(18).incrementTimesCompleted();
                return "20 50";
            }
            break;
        case 50:
            if (!achievements.get(23).isUnlocked) {
                achievements.get(23).unlock();
                achievements.get(23).incrementTimesCompleted();
                return "50 50";
            }
            break;
        case 100:
            if (!achievements.get(28).isUnlocked) {
                achievements.get(28).unlock();
                achievements.get(28).incrementTimesCompleted();
                return "100 50";
            }
            break;
        default:
            return "NA";
            //fallthrough
        }
        return "NA";
    }

    public static String checkHundredMilestone(int times) {
        switch(times) {
        case 5:
            if (!achievements.get(9).isUnlocked) {
                achievements.get(9).unlock();
                achievements.get(9).incrementTimesCompleted();
                return "5 100";
            }
            break;
        case 10:
            if (!achievements.get(14).isUnlocked) {
                achievements.get(14).unlock();
                achievements.get(14).incrementTimesCompleted();
                return "10 100";
            }
            break;
        case 20:
            if (!achievements.get(19).isUnlocked) {
                achievements.get(19).unlock();
                achievements.get(19).incrementTimesCompleted();
                return "20 100";
            }
            break;
        case 50:
            if (!achievements.get(24).isUnlocked) {
                achievements.get(24).unlock();
                achievements.get(24).incrementTimesCompleted();
                return "50 100";
            }
            break;
        case 100:
            if (!achievements.get(29).isUnlocked) {
                achievements.get(29).unlock();
                achievements.get(29).incrementTimesCompleted();
                return "100 100";
            }
            break;
        default:
            return "NA";
            //fallthrough
        }
        return "NA";
    }
}
