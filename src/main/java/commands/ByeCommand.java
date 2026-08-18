package commands;

import tasklist.TaskList;
import ui.UI;

/**
 * Prints Panda's farewell and ends the program.
 */
public class ByeCommand implements Command {
    @Override
    public void execute(TaskList taskList) {
        UI.printMessage("Bye. Hope to see you again soon!");
        System.exit(0);
    }
}
