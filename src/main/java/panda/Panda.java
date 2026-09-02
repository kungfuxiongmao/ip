package panda;

import java.util.Scanner;

import panda.command.Command;
import panda.exception.ApplicationException;
import panda.lifecycle.StartManager;
import panda.parser.Parser;
import panda.ui.ExceptionHandler;
import panda.ui.Ui;

/**
 * Starts Panda's command-line interaction with the user.
 */
public final class Panda {
    /**
     * Initializes Panda and loads saved tasks.
     */
    public Panda() {
        StartManager.start();
    }

    /**
     * Runs Panda until a command ends the program.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Panda panda = new Panda();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            panda.executeCommand(input);
        }
    }

    /**
     * Processes a user command and returns Panda's response for a graphical interface.
     *
     * @param input User command to process.
     * @return Panda's response to the command.
     */
    public synchronized String getResponse(String input) {
        StringBuilder response = new StringBuilder();
        Ui.setMessageConsumer(message -> {
            if (!response.isEmpty()) {
                response.append(System.lineSeparator());
            }
            response.append(message);
        });
        try {
            executeCommand(input);
            return response.toString();
        } finally {
            Ui.resetMessageConsumer();
        }
    }

    /**
     * Parses and executes one user command, displaying any application error.
     *
     * @param input User command to process.
     */
    private void executeCommand(String input) {
        try {
            Command command = Parser.parse(input);
            command.execute();
        } catch (ApplicationException exception) {
            ExceptionHandler.handle(exception);
        }
    }
}
