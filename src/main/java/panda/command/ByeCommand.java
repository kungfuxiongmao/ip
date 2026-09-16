package panda.command;

import panda.lifecycle.TerminationManager;
import panda.task.TaskList;
import panda.ui.Ui;

/**
 * Terminates Panda after confirmation from the graphical interface.
 */
public class ByeCommand implements Command {

    /**
     * Displays Panda's farewell and terminates the program.
     *
     * @param taskList Active task list.
     */
    @Override
    public void execute(TaskList taskList) {
        Ui.printMessage("Running away already? Fine. Try not to lose your white belt.");
        TerminationManager.terminate();
    }
}
