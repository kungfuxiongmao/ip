package panda.exception.task;

import panda.exception.ApplicationException;

/**
 * Signals an attempt to initialize the singleton task list more than once.
 */
public class TaskListAlreadyInstantiatedException extends ApplicationException {

    /**
     * Creates an exception explaining that the task list already exists.
     */
    public TaskListAlreadyInstantiatedException() {
        super("One scroll is enough, young warrior. Do not summon another.");
    }
}
