package seedu.coinflip.utils.achievement;

import seedu.coinflip.utils.userdata.UserData;

//@@author CRL006

/**
 * Abstract class for all achievement classes, defining shared methods and variables.
 */
public abstract class Achievement {
    private Boolean toBePrinted = false;
    private String printMessage = "";

    /**
     * Updates data members (toBePrinted and printMessage) if outcome and
     * userData indicate an achievement has been unlocked.
     *
     * @param userData User data to check for achievements
     * @param outcome  Coinflip outcome to check for achievements
     */
    public abstract void update(UserData userData, Boolean outcome);

    /**
     * Returns whether achievement should be printed.
     *
     * @return Whether achievement should be printed
     */
    public Boolean getToBePrinted() {
        return this.toBePrinted;
    }

    /**
     * Sets whether achievement should be printed.
     *
     * @param toBePrinted Whether achievement should be printed
     */
    public void setToBePrinted(Boolean toBePrinted) {
        this.toBePrinted = toBePrinted;
    }

    /**
     * Returns print message for unlocked achievement (if any).
     *
     * @return Print message for unlocked achievement (if any)
     */
    public String getPrintMessage() {
        return this.printMessage;
    }

    public void setPrintMessage(String printMessage) {
        this.printMessage = printMessage;
    }
}
