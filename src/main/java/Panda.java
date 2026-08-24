import commands.Command;
import exceptions.ApplicationException;
import parser.Parser;
import lifecycle.StartManager;
import ui.ExceptionHandler;

import java.util.Scanner;

/**
 * Starts Panda's command-line interaction with the user.
 */
public class Panda {
    /**
     * Runs Panda until a command ends the program.
     *
     * @param args command-line arguments (not used)
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
