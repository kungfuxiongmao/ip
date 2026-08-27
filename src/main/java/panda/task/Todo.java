package panda.task;

import java.time.temporal.Temporal;

/**
 * A task without a date or time requirement.
 */
public class Todo extends Task {

    /**
     * Creates an unmarked to-do task.
     *
     * @param description description of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Checks if this to-do task occurs on the specified date.
     * Always returns {@code false} as to-dos do not have date associations.
     *
     * @param date the date to check against
     * @return {@code false}
     */
    @Override
    public boolean checkDate(Temporal date) {
        return false;
    }

    /**
     * Returns a string representation of the to-do task.
     *
     * @return formatted to-do string
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
