package seedu.coinflip.utils.achievement;

import seedu.coinflip.utils.userdata.UserData;

//@@author CRL006
public abstract class Achievement {
    private Boolean toBePrinted = false;
    private String printMessage = "";

    public abstract void update(UserData userData, Boolean outcome);

    public Boolean getToBePrinted() {
        return this.toBePrinted;
    }

    public void setToBePrinted(Boolean toBePrinted) {
        this.toBePrinted = toBePrinted;
    }

    public String getPrintMessage() {
        return this.printMessage;
    }

    public void setPrintMessage(String printMessage) {
        this.printMessage = printMessage;
    }
}
