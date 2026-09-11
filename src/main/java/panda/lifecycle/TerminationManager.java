package panda.lifecycle;

import java.io.IOException;

import panda.exception.storage.TaskSavingException;
import panda.storage.Storage;
import panda.task.TaskList;

/**
 * Saves Panda's current task list when the application terminates normally.
 */
public final class TerminationManager {

    private TerminationManager() {
        // Utility class: prevent accidental instantiation.
    }

    /**
     * Saves the current task list and terminates Panda.
     *
     * @param taskList Active task list to save.
     * @throws TaskSavingException If the task list cannot be saved; Panda remains running.
     */
    public static void terminate(TaskList taskList) throws TaskSavingException {
        saveState(taskList);
        System.exit(0);
    }

    private static void saveState(TaskList taskList) throws TaskSavingException {
        try {
            Storage.saveTasks(taskList.getTasks());
        } catch (IOException exception) {
            throw new TaskSavingException(
                    "The ink has failed us. Your tasks could not be saved, so Panda will remain open: "
                            + exception.getMessage(),
                    exception);
        }
    }
}
