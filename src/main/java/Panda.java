import commands.ByeCommand;
import commands.Command;
import commands.AddTaskCommand;
import commands.ListTasksCommand;
import tasklist.TaskList;
import ui.UI;

import java.util.Scanner;

/**
 * Starts Panda's command-line interaction with the user.
 */
public class Panda {
    /**
     * Prints Panda's welcome message.
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

    /**
     * Maps user input to the command that should handle it.
     *
     * @param input command text entered by the user
     * @return the command that handles the input
     */
    private static Command getCommand(String input) {
        if (input.equals("bye")) {
            return new ByeCommand();
        }
        if (input.equals("list")) {
            return new ListTasksCommand();
        }
        return new AddTaskCommand(input);
    }

    /**
     * Runs Panda until a command ends the program.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        greet();

        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();
        while (true) {
            String input = scanner.nextLine().strip();

            Command command = getCommand(input);
            command.execute(taskList);
        }
    }
}
