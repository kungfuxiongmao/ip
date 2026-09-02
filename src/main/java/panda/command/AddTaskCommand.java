package panda.command;

import panda.task.Task;
import panda.task.TaskList;
import panda.ui.Ui;

/**
 * Represents a base command for adding a task and displaying its shared confirmation.
 */
public abstract class AddTaskCommand implements Command {

    /**
     * Initializes an {@code AddTaskCommand}.
     */
    protected AddTaskCommand() {
    }

    /**
     * Adds this command's specific task type to the singleton task list.
     *
     * @return The newly added task.
     */
    protected abstract Task addTask();

    /**
     * Executes the task addition by invoking {@link #addTask()}, then prints a standardized
     * confirmation message displaying the added task and the updated task list size.
     */
    @Override
    public final void execute() {
        TaskList taskList = TaskList.getInstance();
        Task task = addTask();
        Ui.printMessage("Got it. I've added this task:" + System.lineSeparator()
                + "  " + task + System.lineSeparator()
                + "Now you have " + taskList.getSize() + " tasks in the list.");
    }
}
