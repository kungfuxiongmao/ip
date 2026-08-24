package task;

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

    @Override
    public String toString() {
        if (marked) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }
}
