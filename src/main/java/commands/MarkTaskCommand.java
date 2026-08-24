package commands;

import task.Task;
import task.TaskList;
import ui.UI;

/**
 * Marks one existing task.
 */
public class MarkTaskCommand implements Command {
    private final int taskNumber;

    /**
     * Creates a command for the displayed task number.
     *
     * @param taskNumber one-based task number entered by the user
     */
    public MarkTaskCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute() throws exceptions.ApplicationException {
        Task task = TaskList.getInstance().markEvent(taskNumber);
        UI.printMessage("Nice! I've marked this task as done:"
                + System.lineSeparator() + "  " + task);
    }
}
