package exceptions.task;

import exceptions.ApplicationException;

/**
 * Signals an attempt to initialize the singleton task list more than once.
 */
public class TaskListAlreadyInstantiatedException extends ApplicationException {

    /**
     * Creates an exception explaining that the task list already exists.
     */
    public TaskListAlreadyInstantiatedException() {
        super("OOPS! There's already a tasklist, don't create one more!");
    }
}
