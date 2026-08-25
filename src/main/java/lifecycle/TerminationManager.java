package lifecycle;

import storage.Storage;
import task.TaskList;
import ui.UI;

import java.io.IOException;

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
