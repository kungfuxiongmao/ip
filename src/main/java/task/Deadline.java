package task;

import java.time.LocalDate;
import java.time.temporal.Temporal;

import util.datetime.DateTimeHelper;

/**
 * A task that must be completed by a supplied date.
 */
public class Deadline extends Task {
    private final Temporal dueDate;

    /**
     * Creates an unmarked deadline task.
     *
     * @param description description of the task
     * @param dueDate date by which the task is due
     */
    public Deadline(String description, Temporal dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    /**
     * Returns the deadline date.
     *
     * @return deadline date as a {@link Temporal}
     */
    public Temporal getDueDate() {
        return dueDate;
    }

    @Override
    public boolean checkDate(Temporal date) {
        if (date == null) {
            return false;
        }
        LocalDate targetDate = LocalDate.from(date);
        LocalDate dueDateLocal = LocalDate.from(this.dueDate);
        return dueDateLocal.equals(targetDate);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTimeHelper.format(dueDate) + ")";
    }
}
