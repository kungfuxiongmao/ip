package panda.ui;

/**
 * Provides a consistent way for Panda to display messages to the user.
 */
public final class Ui {
    private static final String DIVIDER = "____________________________________________________________";

    private Ui() {
        // Utility class: prevent accidental instantiation.
    }

    /**
     * Prints the message in a standardized format.
     *
     * @param message Text to display.
     */
    public static void printMessage(String message) {
        System.out.println(DIVIDER);
        System.out.println(message);
        System.out.println(DIVIDER);
    }
}
