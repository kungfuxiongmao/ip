package panda.command;

import java.time.temporal.Temporal;

import panda.task.TaskList;
import panda.ui.Ui;

/**
 * Displays tasks occurring on, due on, or spanning across a specified date.
 */
public class DisplayDateCommand implements Command {
    private final Temporal date;

    /**
     * Creates a command to display tasks on the specified date.
     *
     * @param date Date to filter tasks by as a {@link Temporal}.
     */
    public DisplayDateCommand(Temporal date) {
        this.date = date;
    }

    @Override
    public void execute() {
        Ui.printMessage(TaskList.getInstance().getTasksOnDate(date));
    }
}
