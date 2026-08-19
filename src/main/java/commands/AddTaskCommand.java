package commands;

import task.Task;
import task.TaskList;
import ui.UI;

/**
 * Base command for adding a task and displaying its shared confirmation.
 */
public abstract class AddTaskCommand implements Command {

    /**
     * Adds this command's specific task type to the supplied list.
     *
     * @param taskList list receiving the task
     * @return the newly added task
     */
    protected abstract Task addTask(TaskList taskList);

    @Override
    public final void execute(TaskList taskList) {
        Task task = addTask(taskList);
        UI.printMessage("Got it. I've added this task:" + System.lineSeparator()
                + "  " + task + System.lineSeparator()
                + "Now you have " + taskList.getSize() + " tasks in the list.");
    }
}
