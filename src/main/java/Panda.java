import commands.Command;
import parser.Parser;
import task.TaskList;
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
     * Runs Panda until a command ends the program.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        greet();

        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();
        while (true) {
            String input = scanner.nextLine();
            Command command = Parser.parse(input);
            command.execute(taskList);
        }
    }
}
