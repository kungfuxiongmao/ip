package parser;

import commands.AddDeadlineCommand;
import commands.AddEventCommand;
import commands.AddTodoCommand;
import commands.ByeCommand;
import commands.Command;
import commands.ListTasksCommand;
import commands.MarkTaskCommand;
import commands.UnmarkTaskCommand;


/**
 * Converts user-entered text into commands Panda can execute.
 */
public class Parser {
    /**
     * Removes surrounding whitespace before matching the input to a command.
     *
     * @param input text entered by the user
     * @return command that corresponds to the input
     */
    public static Command parse(String input) {
        return matchCommand(processInput(input));
    }

    /**
     * Simple input processing - removal of surrounding whitespace
     *
     * @param input text entered by the user
     * @return input without surrounding whitespace
     */
    private static String processInput(String input) {
        return input.strip();
    }

    /**
     * Matches cleaned input to its command, treating unrecognised input as a task.
     *
     * @param input user input without surrounding whitespace
     * @return command that handles the input
     */
    private static Command matchCommand(String input) {
        if (input.equals("bye")) {
            return new ByeCommand();
        }
        if (input.equals("list")) {
            return new ListTasksCommand();
        }
        if (input.matches("mark\\s+\\d+")) {
            return new MarkTaskCommand(Integer.parseInt(input.substring(4).strip()));
        }
        if (input.matches("unmark\\s+\\d+")) {
            return new UnmarkTaskCommand(Integer.parseInt(input.substring(6).strip()));
        }

        if (input.matches("todo\\s+.+")) {
            return new AddTodoCommand(input.replaceFirst("todo\\s+", ""));
        }
        if (input.matches("deadline\\s+.+?\\s+/by\\s+.+")) {
            String[] deadlineParts = input.substring("deadline".length()).strip()
                    .split("\\s+/by\\s+", 2);
            return new AddDeadlineCommand(deadlineParts[0].replaceFirst("deadline\\s+", ""), deadlineParts[1]);
        }
        if (input.matches("event\\s+.+?\\s+/from\\s+.+?\\s+/to\\s+.+")) {
            String[] eventParts = input.substring("event".length()).strip()
                    .split("\\s+/from\\s+|\\s+/to\\s+", 3);
            return new AddEventCommand(eventParts[0], eventParts[1], eventParts[2]);
        }
        return new AddTodoCommand(input);
    }
}
