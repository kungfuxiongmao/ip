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
                ? String.format("A task number begins at 1, young warrior; %d cannot guide us.",
                        taskNumber)
                : String.format("You seek task %d, but only %d %s on the scroll.",
                        taskNumber, taskCount, taskCount == 1 ? "task rests" : "tasks rest"));
    }
}
