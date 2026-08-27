package panda.task;

import java.time.temporal.Temporal;

/**
 * A task without a date or time requirement.
 */
public class Todo extends Task {

    /**
     * Creates an unmarked to-do task.
     *
     * @param description Description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public boolean checkDate(Temporal date) {
        return false;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
