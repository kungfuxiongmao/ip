package panda.parser.commandparser;

import panda.command.Command;
import panda.command.MarkTaskCommand;
import panda.exception.parser.InvalidArgumentException;

/** Parses arguments for the {@code MarkTaskCommand}. */
public class MarkTaskCommandParser implements CommandParser {

    /**
     * Constructs a {@code MarkTaskCommandParser}.
     */
    public MarkTaskCommandParser() {
    }

    /**
     * Parses the task number to mark from the supplied arguments.
     *
     * @param arguments text containing the one-based task number
     * @return a {@link MarkTaskCommand} with the parsed task index
     * @throws InvalidArgumentException if the argument is non-numeric or empty
     */
    @Override
    public Command parseArguments(String arguments) throws InvalidArgumentException {
        if (!arguments.matches("\\d+")) {
            throw new InvalidArgumentException("mark", "mark TASK_NUMBER");
        }
        return new MarkTaskCommand(Integer.parseInt(arguments));
    }
}
