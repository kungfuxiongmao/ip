package panda.exception.task;

import panda.exception.ApplicationException;
import panda.task.Task;

/**
 * Signals an attempt to unmark a task that is already unmarked.
 */
public class TaskAlreadyUnmarkedException extends ApplicationException {

    /**
     * Creates an exception that identifies the task that was unmarked twice.
     *
     * @param task unmarked task the user tried to unmark again
     */
    public TaskAlreadyUnmarkedException(Task task) {
        super("OOPS! Panda has already marked this task as not done:" + System.lineSeparator()
                + "  " + task + System.lineSeparator() + "No extra un-tick needed. :>");
    }
}
