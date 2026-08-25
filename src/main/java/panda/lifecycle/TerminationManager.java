package panda.lifecycle;

import java.io.IOException;

import panda.storage.Storage;
import panda.task.TaskList;
import panda.ui.UI;

/**
 * Saves Panda's current task list when the application terminates normally.
 */
public final class TerminationManager {
    private TerminationManager() {
    }

    /**
     * Saves the current task list and terminates Panda. A save error is shown
     * to the user before the application exits.
     */
    public static void terminate() {
        try {
            Storage.saveTasks(TaskList.getInstance().getTasks());
        } catch (IOException exception) {
            UI.printMessage("OOPS! Panda could not save tasks: " + exception.getMessage());
        }
        System.exit(0);
    }
}
