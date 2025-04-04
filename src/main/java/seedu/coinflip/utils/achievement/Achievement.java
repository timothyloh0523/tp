package seedu.coinflip.utils.achievement;

public class Achievement {
    public Boolean isUnlocked;
    public int timesCompleted;
    public String descriptor;
    Achievement(String descriptor) {
        this.isUnlocked = false;
        this.timesCompleted = 0;
        this.descriptor = descriptor;
    }
    public void unlock() {
        isUnlocked = true;
    }
    public void incrementTimesCompleted() {
        timesCompleted++;
    }
}
