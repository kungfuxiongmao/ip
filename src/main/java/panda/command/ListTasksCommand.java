package panda.command;

import panda.task.TaskList;
import panda.ui.UI;

/**
 * Displays every task stored during the current Panda session.
 */
public class ListTasksCommand implements Command {
    @Override
    public void execute() {
        UI.printMessage(TaskList.getInstance().toString());
    }
}
