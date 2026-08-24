package task;

/**
 * A task that must be completed by a supplied date.
 */
public class Deadline extends Task {
    private final String dueDate;

    /**
     * Creates an unmarked deadline task.
     *
     * @param description description of the task
     * @param dueDate date by which the task is due, kept in its input format
     */
    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    /**
     * Returns the deadline in its original input format.
     *
     * @return deadline text
     */
    public String getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate + ")";
    }
}
