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
import panda.task.TaskList;
import panda.ui.ExceptionHandler;
import panda.ui.Ui;

/**
 * Processes commands received through input and output streams.
 */
public final class Panda {
    private final Scanner commandScanner;
    private final boolean isGui;
    private final TaskList taskList;

    private Panda(InputStream commandInputStream, OutputStream responseOutputStream,
            boolean isGui) {
        commandScanner = new Scanner(
                Objects.requireNonNull(commandInputStream), StandardCharsets.UTF_8);
        Ui.directOutputTo(Objects.requireNonNull(responseOutputStream));
        this.isGui = isGui;
        this.taskList = start();
    }

    /**
     * Starts Panda's command-line interface using the standard input and output streams.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Panda panda = new Panda(System.in, System.out, false);
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
        Panda panda = new Panda(commandInputStream, responseOutputStream, true);
        return panda;
    }

    /**
     * Processes commands until the input stream closes or a command terminates Panda.
     */
    public void processCommandsUntilInputCloses() {
        while (commandScanner.hasNextLine()) {
            processCommand(commandScanner.nextLine());
        }
    }

    /**
     * Starts Panda and recovers with an empty task list if startup fails.
     */
    private TaskList start() {
        try {
            return StartManager.start(isGui);
        } catch (ApplicationException exception) {
            ExceptionHandler.handle(exception);
            return new TaskList();
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
            command.execute(taskList);
        } catch (ApplicationException exception) {
            ExceptionHandler.handle(exception);
        }
    }
}
