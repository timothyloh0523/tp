package seedu.coinflip.utils.animations;

import seedu.coinflip.utils.exceptions.CoinflipException;

public class LoadingAnimation extends Animation {
    private static final int SLEEP_DURATION_MS = 300;
    private static final int NUMBER_OF_SPACES = 50;
    private static final int NUMBER_OF_LOADING_STAGES = 6;

    //@@author HTY2003
    public static void animate(String message) throws CoinflipException {

        for (int i = 0; i < NUMBER_OF_LOADING_STAGES - 1; i++) {
            printLoadingStage(i, message);
            sleep(SLEEP_DURATION_MS);
        }

        clearLine();
        printLoadingStage(NUMBER_OF_LOADING_STAGES - 1, "");
    }

    //@@author HTY2003
    private static void printSpaces() {
        System.out.print(new String(new char[NUMBER_OF_SPACES]).replace("\0", " "));
    }

    //@@author HTY2003
    private static void printCarriageReturn() {
        System.out.print('\r');
    }

    //@@author HTY2003
    private static void clearLine() {
        printCarriageReturn();
        printSpaces();
        printCarriageReturn();
    }

    //@@author HTY2003
    private static void printLoadingStage(int stage, String message) {
        switch (stage) {
        case 0:
            System.out.print("[ - ] ");
            break;
        case 1:
            System.out.print("\r[ \\ ] ");
            break;
        case 2:
            System.out.print("\r[ | ] ");
            break;
        case 3:
            System.out.print("\r[ / ] ");
            break;
        case 4:
            System.out.print("\r[ - ] ");
            break;
        case 5:
            System.out.print("\r[ X ] ");
            break;
        }
        System.out.print(message);
    }
}
