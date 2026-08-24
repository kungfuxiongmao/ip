package commands;

import task.TaskList;
import ui.UI;

/**
 * Displays every task stored during the current Panda session.
 */
public class ListTasksCommand implements Command {
    @Override
    public void execute() {
        UI.printMessage(TaskList.getInstance().toString());
    }
}
