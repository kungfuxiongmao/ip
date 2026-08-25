package panda.command;

import panda.lifecycle.TerminationManager;
import panda.ui.UI;

/**
 * Prints Panda's farewell and ends the program.
 */
public class ByeCommand implements Command {
    @Override
    public void execute() {
        UI.printMessage("Bye. Hope to see you again soon!");
        TerminationManager.terminate();
    }
}
