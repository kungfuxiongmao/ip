package panda.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * Provides a consistent way for Panda to send and receive formatted messages.
 */
public final class Ui {
    private static final String MESSAGE_DIVIDER = "____________________________________________________________";
    private static PrintWriter outputWriter = createOutputWriter(System.out);

    private Ui() {
        // Utility class: prevent accidental instantiation.
    }

    /**
     * Directs subsequently displayed messages to the supplied output stream.
     *
     * @param outputStream Stream that receives Panda's messages.
     */
    public static synchronized void directOutputTo(OutputStream outputStream) {
        outputWriter = createOutputWriter(Objects.requireNonNull(outputStream));
    }

    /**
     * Prints the message in a standardized format and flushes it to the configured stream.
     *
     * @param message Text to display.
     */
    public static synchronized void printMessage(String message) {
        outputWriter.println(MESSAGE_DIVIDER);
        outputWriter.println(message);
        outputWriter.println(MESSAGE_DIVIDER);
        outputWriter.flush();
    }

    /**
     * Reads the next complete Panda message from a buffered character stream.
     *
     * @param inputReader Reader connected to Panda's response stream.
     * @return Next message, or {@code null} after the stream closes.
     * @throws IOException If the response stream cannot be read.
     */
    public static String readNextMessage(BufferedReader inputReader) throws IOException {
        Objects.requireNonNull(inputReader);
        StringBuilder message = new StringBuilder();
        boolean isReadingMessage = false;

        String line;
        while ((line = inputReader.readLine()) != null) {
            if (line.equals(MESSAGE_DIVIDER)) {
                if (isReadingMessage) {
                    return message.toString();
                }
                isReadingMessage = true;
            } else if (isReadingMessage) {
                if (!message.isEmpty()) {
                    message.append(System.lineSeparator());
                }
                message.append(line);
            }
        }
        return null;
    }

    /**
     * Creates an automatically flushing writer for an output stream.
     *
     * @param outputStream Stream to wrap.
     * @return Writer configured to use UTF-8.
     */
    private static PrintWriter createOutputWriter(OutputStream outputStream) {
        return new PrintWriter(outputStream, true, StandardCharsets.UTF_8);
    }
}
