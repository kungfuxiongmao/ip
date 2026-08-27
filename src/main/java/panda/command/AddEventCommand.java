package panda.command;

import java.time.temporal.Temporal;

import panda.task.Task;
import panda.task.TaskList;

/**
 * Adds an event task to Panda's task list.
 */
public class AddEventCommand extends AddTaskCommand {
    private final String description;
    private final Temporal dateTimeFrom;
    private final Temporal dateTimeTo;

    /**
     * Creates a command for an event task.
     *
     * @param description Description of the event.
     * @param dateTimeFrom Event start date and time.
     * @param dateTimeTo Event end date and time.
     */
    public AddEventCommand(String description, Temporal dateTimeFrom, Temporal dateTimeTo) {
        this.description = description;
        this.dateTimeFrom = dateTimeFrom;
        this.dateTimeTo = dateTimeTo;
    }

    @Override
    protected Task addTask() {
        return TaskList.getInstance().addEvent(description, dateTimeFrom, dateTimeTo);
    }
}
