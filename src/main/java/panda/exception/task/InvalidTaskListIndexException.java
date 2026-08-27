package panda.exception.task;

import panda.exception.ApplicationException;

/**
 * Signals that a task number does not identify a task in the current list.
 */
public class InvalidTaskListIndexException extends ApplicationException {

    /**
     * Creates an error that identifies the unavailable task number and list size.
     *
     * @param taskNumber Task number requested by the user.
     * @param taskCount Number of tasks currently in the list.
     */
    public InvalidTaskListIndexException(int taskNumber, int taskCount) {
        super(taskNumber == 0
                ? String.format("OOPS! I think you made a mistake, task number cannot be %d", taskNumber)
                : String.format(
                "OOPS! Panda cannot find task number %d; there are only %d task(s) in the list. ",
                taskNumber, taskCount));
    }
}
