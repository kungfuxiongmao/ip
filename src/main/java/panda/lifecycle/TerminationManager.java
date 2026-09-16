package panda.lifecycle;

import java.io.IOException;

import panda.exception.storage.TaskSavingException;
import panda.storage.Storage;
import panda.task.TaskList;

/**
 * Provides operations used while Panda exits normally.
 */
public final class TerminationManager {

    private TerminationManager() {
        // Utility class: prevent accidental instantiation.
    }

    /**
     * Terminates Panda normally.
     */
    public static void terminate() {
        System.exit(0);
    }

    /**
     * Saves the current task list without terminating Panda.
     *
     * @param taskList Active task list to save.
     * @throws TaskSavingException If the task list cannot be saved.
     */
    public static void saveState(TaskList taskList) throws TaskSavingException {
        try {
            Storage.saveTasks(taskList.getTasks());
        } catch (IOException exception) {
            throw new TaskSavingException(
                    "Even my legendary technique couldn't save this mess: "
                            + exception.getMessage(),
                    exception);
        }
    }
}
