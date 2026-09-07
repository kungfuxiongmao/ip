package panda.lifecycle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import panda.exception.task.TaskListAlreadyInstantiatedException;
import panda.storage.Storage;
import panda.task.Task;
import panda.task.TaskList;
import panda.ui.ExceptionHandler;
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
     * <p>
     * An empty task list is used if the file is corrupted or cannot be read. It can be assumed
     * after running this function that TaskList has been instantiated correctly for the application.
     *
     * @param isGui Whether Panda is running through the graphical interface.
     */
    public static void start(boolean isGui) {
        greet(isGui);

        List<Task> tasks;
        try {
            tasks = Storage.readTasks();
        } catch (IOException exception) {
            Ui.printMessage("Hmm. The scroll will not open. We must begin with an empty one: "
                    + exception.getMessage());
            tasks = null;
        }

        if (tasks == null) {
            tasks = new ArrayList<>();
        }
        try {
            TaskList.of(tasks);
        } catch (TaskListAlreadyInstantiatedException exception) {
            ExceptionHandler.handle(exception);
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
