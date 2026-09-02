package panda.ui;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * Provides a consistent way for Panda to display messages to the user.
 */
public final class Ui {
    private static final String DIVIDER = "____________________________________________________________";
    private static Consumer<String> messageConsumer = Ui::printToConsole;

    private Ui() {
        // Utility class: prevent accidental instantiation.
    }

    /**
     * Prints the message in a standardized format.
     *
     * @param message Text to display.
     */
    public static void printMessage(String message) {
        messageConsumer.accept(message);
    }

    /**
     * Directs subsequently displayed messages to the supplied consumer.
     *
     * @param messageConsumer Consumer that receives each message.
     */
    public static void setMessageConsumer(Consumer<String> messageConsumer) {
        Ui.messageConsumer = Objects.requireNonNull(messageConsumer);
    }

    /**
     * Restores console output for subsequently displayed messages.
     */
    public static void resetMessageConsumer() {
        messageConsumer = Ui::printToConsole;
    }

    /**
     * Prints one message between divider lines.
     *
     * @param message Text to print.
     */
    private static void printToConsole(String message) {
        System.out.println(DIVIDER);
        System.out.println(message);
        System.out.println(DIVIDER);
    }
}
