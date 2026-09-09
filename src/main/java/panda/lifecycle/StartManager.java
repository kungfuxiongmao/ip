package panda.lifecycle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import panda.exception.ApplicationException;
import panda.exception.storage.FileCorruptedException;
import panda.exception.storage.TaskLoadingException;
import panda.storage.Storage;
import panda.task.Task;
import panda.task.TaskList;
import panda.ui.Ui;

/**
 * Handles Panda's greeting and task-list initialization at startup.
 */
public final class StartManager {

    private StartManager() {
        // Utility class: prevent accidental instantiation.
    }

    /**
     * Greets the user and initializes the task list from storage.
     *
     * @param isGui Whether Panda is running through the graphical interface.
     * @throws ApplicationException If saved tasks cannot be loaded or the task list already exists.
     */
    public static void start(boolean isGui) throws ApplicationException {
        greet(isGui);
        try {
            TaskList.of(loadTasks());
        } catch (TaskLoadingException exception) {
            TaskList.of(new ArrayList<>());
            throw exception;
        }

    }

    /**
     * Loads saved tasks and translates storage failures into a recoverable application error.
     *
     * @return Tasks loaded from storage.
     * @throws TaskLoadingException If the save file cannot be read or contains invalid data.
     */
    private static List<Task> loadTasks() throws TaskLoadingException {
        try {
            return Storage.readTasks();
        } catch (FileCorruptedException exception) {
            throw new TaskLoadingException(
                    "This scroll is damaged, young warrior. We must begin with an empty one."
                            + System.lineSeparator()
                            + exception.getMessage(),
                    exception);
        } catch (IOException exception) {
            throw new TaskLoadingException(
                    "Hmm. The scroll will not open. We must begin with an empty one: "
                            + exception.getMessage(),
                    exception);
        }
    }

    /**
     * Prints Panda's initial greeting, omitting the ASCII art in the graphical interface.
     *
     * @param isGui Whether Panda is running through the graphical interface.
     */
    private static void greet(boolean isGui) {
        String banner = """
                                                            _______               \s
                _________   _...._                  _..._   \\  ___ `'.            \s
                \\        |.'      '-.             .'     '.  ' |--.\\  \\           \s
                 \\        .'```'.    '.          .   .-.   . | |    \\  '          \s
                  \\      |       \\     \\   __    |  '   '  | | |     |  '    __   \s
                   |     |        |    |.:--.'.  |  |   |  | | |     |  | .:--.'. \s
                   |      \\      /    ./ |   \\ | |  |   |  | | |     ' .'/ |   \\ |\s
                   |     |\\`'-.-'   .' `" __ | | |  |   |  | | |___.' /' `" __ | |\s
                   |     | '-....-'`    .'.''| | |  |   |  |/_______.'/   .'.''| |\s
                  .'     '.            / /   | |_|  |   |  |\\_______|/   / /   | |_
                '-----------'          \\ \\._,\\ '/|  |   |  |             \\ \\._,\\ '/
                                        `--'  `" '--'   '--'              `--'  `"\s
                """;
        String greeting = "Ah, you are here. Breathe, young warrior.\nWhat shall we face today?";
        Ui.printMessage(isGui ? greeting : banner + "\n" + greeting);
    }
}
