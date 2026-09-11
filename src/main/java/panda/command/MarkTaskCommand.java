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
     * @param taskList Active task list.
     * @throws ApplicationException If the task number is out of bounds or already marked.
     */
    @Override
    public void execute(TaskList taskList) throws ApplicationException {
        Task task = taskList.markTask(taskNumber);
        Ui.printMessage("Well done. Discipline bears fruit. You have completed this:"
                + System.lineSeparator() + "  " + task);
    }
}
