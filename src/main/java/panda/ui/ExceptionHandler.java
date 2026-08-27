package panda.ui;

import panda.exception.ApplicationException;

/**
 * Displays recoverable application errors using Panda's standard Ui format.
 */
public final class ExceptionHandler {

    private ExceptionHandler() {
        // Utility class: prevent accidental instantiation.
    }

    /**
     * Displays the message provided by a recoverable application exception.
     *
     * @param exception Exception to display.
     */
    public static void handle(ApplicationException exception) {
        Ui.printMessage(exception.getMessage());
    }
}
