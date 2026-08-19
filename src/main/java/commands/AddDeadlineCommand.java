package commands;

import task.Task;
import task.TaskList;

/** Adds a deadline task to Panda's task list. */
public class AddDeadlineCommand extends AddTaskCommand {
    private final String description;
    private final String dueDate;

    /**
     * Creates a command for a deadline task.
     *
     * @param description description of the task
     * @param dueDate date by which the task is due
     */
    public AddDeadlineCommand(String description, String dueDate) {
        this.description = description;
        this.dueDate = dueDate;
    }

    @Override
    protected Task addTask(TaskList taskList) {
        return taskList.addDeadline(description, dueDate);
    }
}
