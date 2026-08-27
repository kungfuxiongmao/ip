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

    @Override
    public void execute() throws ApplicationException {
        Task task = TaskList.getInstance().markEvent(taskNumber);
        Ui.printMessage("Nice! I've marked this task as done:"
                + System.lineSeparator() + "  " + task);
    }
}
