package panda.task;

import java.time.temporal.Temporal;

/**
 * Represents a single item tracked by Panda.
 */
public abstract class Task {
    private final String description;
    private boolean isMarked;

    /**
     * Creates an unmarked task with the given description.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isMarked = false;
    }

    /**
     * Returns the description of this task.
     *
     * @return Description text.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns whether this task has been marked as done.
     *
     * @return {@code true} if marked; {@code false} otherwise.
     */
    public boolean isMarked() {
        return isMarked;
    }

    /**
     * Marks this task as done.
     */
    public void mark() {
        this.isMarked = true;
    }

    /**
     * Marks this task as not done.
     */
    public void unmark() {
        this.isMarked = false;
    }

    /**
     * Checks if this task occurs on, is due on, or spans across the specified date.
     *
     * @param date Date to check against as a {@link Temporal}.
     * @return {@code true} if this task matches the specified date, {@code false} otherwise.
     */
    public abstract boolean checkDate(Temporal date);

    @Override
    public String toString() {
        return "[" + (isMarked ? "X" : " ") + "] " + description;
    }
}
