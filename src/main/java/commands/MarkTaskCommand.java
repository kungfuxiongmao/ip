package commands;

import exceptions.task.TaskAlreadyMarkedException;
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
    public void execute(TaskList taskList) {
        try {
            Task task = taskList.markEvent(taskNumber);
            UI.printMessage("Nice! I've marked this task as done:"
                    + System.lineSeparator() + "  " + task);
        } catch (ArrayIndexOutOfBoundsException exception) {
            UI.printMessage("Invalid task number");
        } catch (TaskAlreadyMarkedException exception) {
            UI.printMessage(exception.getTask() + System.lineSeparator()
                    + "This task has already been marked.");
        }
    }
}
