package panda.exception.task;

import panda.exception.ApplicationException;
import panda.task.Task;

/**
 * Signals an attempt to mark a task that has already been marked.
 */
public class TaskAlreadyMarkedException extends ApplicationException {

    /**
     * Creates an exception that identifies the task that was marked twice.
     *
     * @param task marked task the user tried to mark again
     */
    public TaskAlreadyMarkedException(Task task) {
        super("OOPS! Panda has already marked this task as done:" + System.lineSeparator()
                + "  " + task + System.lineSeparator() + "No extra tick needed. :>");
    }
}
