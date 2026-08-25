package panda.task;

import java.time.temporal.Temporal;

/**
 * Represents a task with shared completion state.
 * Concrete subclasses supply their own type-specific details when displayed.
 */
public abstract class Task {
    private boolean marked;
    private final String description;

    protected Task(String description) {
        this.marked = false;
        this.description = description;
    }

    public void mark() {
        this.marked = true;
    }

    public void unmark() {
        this.marked = false;
    }

    public boolean isMarked() {
        return this.marked;
    }

    /**
     * Returns the task description.
     *
     * @return task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Checks whether this task falls on, is due on, or spans across the specified date.
     * <p>
     * The default implementation returns {@code false}. Subclasses with date components
     * override this method to perform date-specific comparisons.
     *
     * @param date the date to check against
     * @return {@code true} if this task occurs on or spans across the specified date; {@code false} otherwise
     */
    public boolean checkDate(Temporal date) {
        return false;
    }

    @Override
    public String toString() {
        if (marked) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }
}
