package commands;

import task.TaskList;
import ui.UI;

/**
 * Adds text entered by the user to Panda's task list.
 */
public class AddTaskCommand implements Command {
    private final String task;

    /**
     * Creates a command that adds the supplied task.
     *
     * @param task task text to store
     */
    public AddTaskCommand(String task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList) {
        taskList.add(task);
        UI.printMessage("added: " + task);
    }
}
