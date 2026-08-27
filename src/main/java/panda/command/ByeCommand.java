package panda.command;

import panda.lifecycle.TerminationManager;
import panda.ui.UI;

/**
 * Prints Panda's farewell and ends the program.
 */
public class ByeCommand implements Command {

    /**
     * Constructs a {@code ByeCommand}.
     */
    public ByeCommand() {
    }
    /**
     * Prints a farewell message to the user and terminates the application session.
     */
    @Override
    public void execute() {
        UI.printMessage("Bye. Hope to see you again soon!");
        TerminationManager.terminate();
    }
}
