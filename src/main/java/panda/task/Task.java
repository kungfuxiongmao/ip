package panda.task;

import java.time.temporal.Temporal;
import java.util.regex.Pattern;

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
     * Checks whether this task's description contains the specified whole keyword, ignoring letter case.
     *
     * @param keyword Keyword to find in the description.
     * @return {@code true} if the description contains the whole keyword; {@code false} otherwise.
     */
    public boolean hasKeyword(String keyword) {
        String keywordPattern = "(?<![\\p{L}\\p{N}])"
                + Pattern.quote(keyword)
                + "(?![\\p{L}\\p{N}])";
        return Pattern.compile(keywordPattern, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)
                .matcher(description)
                .find();
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
