package commands;

import task.Task;
import task.TaskList;

/** Adds a to-do task to Panda's task list. */
public class AddTodoCommand extends AddTaskCommand {
    private final String description;

    /**
     * Creates a command for a to-do description.
     *
     * @param description description of the task
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    protected Task addTask() {
        return TaskList.getInstance().addTodo(description);
    }
}
