package panda.command;

import panda.exception.ApplicationException;
import panda.task.Task;
import panda.task.TaskList;
import panda.ui.Ui;

/**
 * Marks one existing task.
 */
public class MarkTaskCommand implements Command {
    private final int taskNumber;

    /**
     * Creates a command for the displayed task number.
     *
     * @param taskNumber One-based task number entered by the user.
     */
    public MarkTaskCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Marks the designated task as completed and displays a confirmation message.
     *
     * @throws ApplicationException If the task number is out of bounds or already marked.
     */
    @Override
    public void execute() throws ApplicationException {
        Task task = TaskList.getInstance().markEvent(taskNumber);
        Ui.printMessage("Nice! I've marked this task as done:"
                + System.lineSeparator() + "  " + task);
    }
}
