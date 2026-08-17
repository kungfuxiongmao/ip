package ui;

/**
 * Provides a consistent way for Panda to display messages to the user.
 */
public final class UI {
    private static final String DIVIDER = "____________________________________________________________";

    private UI() {
        // Utility class: prevent accidental instantiation.
    }

    /**
     * Prints message in a standardised format
     *
     * @param message text to display
     */
    public static void printMessage(String message) {
        System.out.println(DIVIDER);
        System.out.println(message);
        System.out.println(DIVIDER);
    }
}
