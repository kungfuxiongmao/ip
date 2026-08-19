package ui;

import exceptions.ApplicationException;

/**
 * Displays recoverable application errors using Panda's standard UI format.
 */
public final class ExceptionHandler {

    private ExceptionHandler() {
        // Utility class: prevent accidental instantiation.
    }

    /**
     * Displays the message provided by a recoverable application exception.
     *
     * @param exception exception to display
     */
    public static void handle(ApplicationException exception) {
        UI.printMessage(exception.getMessage());
    }
}
