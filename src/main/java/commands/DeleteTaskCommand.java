package commands;

import task.Task;
import task.TaskList;
import ui.UI;

/**
 * Removes one existing task from the task list.
 */
public class DeleteTaskCommand implements Command {
    private final int taskNumber;

    /**
     * Creates a command for the displayed task number.
     *
     * @param taskNumber one-based task number entered by the user
     */
    public DeleteTaskCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute() throws exceptions.ApplicationException {
        TaskList taskList = TaskList.getInstance();
        Task task = taskList.delete(taskNumber);
        UI.printMessage("Noted. I've removed this task:" + System.lineSeparator()
                + "  " + task + System.lineSeparator()
                + "Now you have " + taskList.getSize() + " tasks in the list.");
    }
}
