package commands;

import task.Task;
import task.TaskList;

/** Adds an event task to Panda's task list. */
public class AddEventCommand extends AddTaskCommand {
    private final String description;
    private final String dateTimeFrom;
    private final String dateTimeTo;

    /**
     * Creates a command for an event task.
     *
     * @param description description of the event
     * @param dateTimeFrom event start date and time
     * @param dateTimeTo event end date and time
     */
    public AddEventCommand(String description, String dateTimeFrom, String dateTimeTo) {
        this.description = description;
        this.dateTimeFrom = dateTimeFrom;
        this.dateTimeTo = dateTimeTo;
    }

    @Override
    protected Task addTask() {
        return TaskList.getInstance().addEvent(description, dateTimeFrom, dateTimeTo);
    }
}
