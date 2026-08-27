package panda.command;

import panda.task.TaskList;
import panda.ui.Ui;

/**
 * Displays every task stored during the current Panda session.
 */
public class ListTasksCommand implements Command {

    @Override
    public void execute() {
        Ui.printMessage(TaskList.getInstance().toString());
    }
}
