package panda.command;

import panda.lifecycle.TerminationManager;
import panda.ui.Ui;

/**
 * Prints Panda's farewell and ends the program.
 */
public class ByeCommand implements Command {

    @Override
    public void execute() {
        Ui.printMessage("Bye. Hope to see you again soon!");
        TerminationManager.terminate();
    }
}
