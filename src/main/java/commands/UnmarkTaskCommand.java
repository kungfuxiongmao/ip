package commands;

import task.Task;
import task.TaskList;
import ui.UI;

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

    @Override
    public void execute() throws exceptions.ApplicationException {
        Task task = TaskList.getInstance().unmarkEvent(taskNumber);
        UI.printMessage("OK, I've marked this task as not done yet:"
                + System.lineSeparator() + "  " + task);
    }
}
