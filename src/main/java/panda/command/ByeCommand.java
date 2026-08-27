package panda.command;

import panda.lifecycle.TerminationManager;
import panda.ui.Ui;

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
        Ui.printMessage("Bye. Hope to see you again soon!");
        TerminationManager.terminate();
    }
}
