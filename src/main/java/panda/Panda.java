package panda;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Scanner;

import panda.command.Command;
import panda.exception.ApplicationException;
import panda.lifecycle.StartManager;
import panda.parser.Parser;
import panda.ui.ExceptionHandler;
import panda.ui.Ui;

/**
 * Processes commands received through input and output streams.
 */
public final class Panda {
    private final Scanner commandScanner;

    private Panda(InputStream commandInputStream, OutputStream responseOutputStream) {
        commandScanner = new Scanner(
                Objects.requireNonNull(commandInputStream), StandardCharsets.UTF_8);
        Ui.directOutputTo(Objects.requireNonNull(responseOutputStream));
    }

    /**
     * Starts Panda's command-line interface using the standard input and output streams.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Panda panda = new Panda(System.in, System.out);
        panda.processCommandsUntilInputCloses();
    }

    /**
     * Creates a Panda instance connected to streams supplied by a graphical interface.
     *
     * @param commandInputStream Stream containing commands from the graphical interface.
     * @param responseOutputStream Stream receiving responses for the graphical interface.
     * @return Panda instance connected to the supplied streams.
     */
    public static Panda createForGraphicalInterface(
            InputStream commandInputStream, OutputStream responseOutputStream) {
        return new Panda(commandInputStream, responseOutputStream);
    }

    /**
     * Processes commands until the input stream closes or a command terminates Panda.
     */
    public void processCommandsUntilInputCloses() {
        StartManager.start();
        while (commandScanner.hasNextLine()) {
            processCommand(commandScanner.nextLine());
        }
    }

    /**
     * Parses and executes one command, displaying any application error through {@link Ui}.
     *
     * @param input User command to process.
     */
    private void processCommand(String input) {
        try {
            Command command = Parser.parse(input);
            command.execute();
        } catch (ApplicationException exception) {
            ExceptionHandler.handle(exception);
        }
    }
}
