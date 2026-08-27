package panda.lifecycle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import panda.exception.task.TaskListAlreadyInstantiatedException;
import panda.storage.Storage;
import panda.task.Task;
import panda.task.TaskList;
import panda.ui.ExceptionHandler;
import panda.ui.UI;

/**
 * Handles Panda's greeting and task-list initialization at startup.
 */
public final class StartManager {
    private StartManager() {
    }

    /**
     * Greets the user and initializes the task list from storage. An empty task
     * list is used if the file is corrupted or cannot be read. It can be assumed
     * after running this function that TaskList has been instantiated correctly
     * for the application.
     *
     */
    public static void start() {
        greet();

        List<Task> tasks;
        try {
            tasks = Storage.readTasks();
        } catch (IOException exception) {
            UI.printMessage("Panda could not read the save file: " + exception.getMessage());
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
     * Prints Panda's welcome banner and initial greeting message to the console.
     */
    private static void greet() {
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
        UI.printMessage(banner + "\nHello! I'm Panda." + "\nWhat can I do for you?");
    }
}
