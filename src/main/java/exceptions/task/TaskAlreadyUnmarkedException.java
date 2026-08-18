package exceptions.task;

import task.Task;

/**
 * Signals an attempt to unmark a task that is already unmarked.
 */
public class TaskAlreadyUnmarkedException extends Exception {
    private final Task task;

    /**
     * Creates an exception that identifies the task that was unmarked twice.
     *
     * @param task unmarked task the user tried to unmark again
     */
    public TaskAlreadyUnmarkedException(Task task) {
        this.task = task;
    }

    /**
     * Returns the task that was already unmarked.
     *
     * @return unmarked task
     */
    public Task getTask() {
        return task;
    }
}
