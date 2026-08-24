package task;

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

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
