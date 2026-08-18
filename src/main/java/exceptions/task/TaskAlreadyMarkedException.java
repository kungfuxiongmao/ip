package exceptions.task;

import task.Task;

/**
 * Signals an attempt to mark a task that has already been marked.
 */
public class TaskAlreadyMarkedException extends Exception {
    private final Task task;

    /**
     * Creates an exception that identifies the task that was marked twice.
     *
     * @param task marked task the user tried to mark again
     */
    public TaskAlreadyMarkedException(Task task) {
        this.task = task;
    }

    /**
     * Returns the task that was already marked.
     *
     * @return marked task
     */
    public Task getTask() {
        return task;
    }
}
