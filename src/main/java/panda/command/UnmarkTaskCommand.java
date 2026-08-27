package panda.command;

import panda.exception.ApplicationException;
import panda.task.Task;
import panda.task.TaskList;
import panda.ui.UI;

/**
 * Removes the mark from one existing task.
 */
public class UnmarkTaskCommand implements Command {
    private final int taskNumber;

    /**
     * Creates a command for the displayed task number.
     *
     * @param taskNumber one-based task number entered by the user
     */
    public UnmarkTaskCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Unmarks the designated task (marks as not done) and displays a confirmation message.
     *
     * @throws ApplicationException if the task number is out of bounds or already unmarked
     */
    @Override
    public void execute() throws ApplicationException {
        Task task = TaskList.getInstance().unmarkEvent(taskNumber);
        UI.printMessage("OK, I've marked this task as not done yet:"
                + System.lineSeparator() + "  " + task);
    }
}
