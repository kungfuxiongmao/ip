package commands;

import tasklist.TaskList;
import ui.UI;

/**
 * Displays every task stored during the current Panda session.
 */
public class ListTasksCommand implements Command {
    @Override
    public void execute(TaskList taskList) {
        UI.printMessage(taskList.toString());
    }
}
