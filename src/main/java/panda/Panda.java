package panda;

import java.util.Scanner;

import panda.command.Command;
import panda.exception.ApplicationException;
import panda.lifecycle.StartManager;
import panda.parser.Parser;
import panda.ui.ExceptionHandler;

/**
 * Starts Panda's command-line interaction with the user.
 */
public final class Panda {
    private Panda() {
        // Utility / entry point class: prevent instantiation.
    }

    /**
     * Runs Panda until a command ends the program.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        StartManager.start();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            try {
                Command command = Parser.parse(input);
                command.execute();
            } catch (ApplicationException exception) {
                ExceptionHandler.handle(exception);
            }
        }
    }
}
