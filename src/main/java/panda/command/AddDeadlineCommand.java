package panda.command;

import java.time.temporal.Temporal;

import panda.task.Task;
import panda.task.TaskList;

/**
 * Adds a deadline task to Panda's task list.
 */
public class AddDeadlineCommand extends AddTaskCommand {
    private final String description;
    private final Temporal dueDate;

    /**
     * Creates a command for a deadline task.
     *
     * @param description Description of the task.
     * @param dueDate Date by which the task is due.
     */
    public AddDeadlineCommand(String description, Temporal dueDate) {
        this.description = description;
        this.dueDate = dueDate;
    }

    @Override
    protected Task addTask() {
        return TaskList.getInstance().addDeadline(description, dueDate);
    }
}
