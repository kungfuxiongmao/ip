package panda.command;

import panda.task.TaskList;
import panda.ui.Ui;

/**
 * Displays every task stored during the current Panda session.
 */
public class ListTasksCommand implements Command {

    /**
     * Constructs a {@code ListTasksCommand}.
     */
    public ListTasksCommand() {
    }

    /**
     * Retrieves and displays the full formatted list of all current tasks.
     */
    @Override
    public void execute() {
        Ui.printMessage(TaskList.getInstance().toString());
    }
}
