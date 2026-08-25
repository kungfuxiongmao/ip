package commands;

import java.time.temporal.Temporal;

import task.TaskList;
import ui.UI;

/**
 * Displays tasks occurring on, due on, or spanning across a specified date.
 */
public class DisplayDateCommand implements Command {
    private final Temporal date;

    /**
     * Creates a command to display tasks on the specified date.
     *
     * @param date date to filter tasks by as a {@link Temporal}
     */
    public DisplayDateCommand(Temporal date) {
        this.date = date;
    }

    @Override
    public void execute() {
        UI.printMessage(TaskList.getInstance().getTasksOnDate(date));
    }
}
