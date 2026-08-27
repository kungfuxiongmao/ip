package panda.parser.commandparser;

import panda.command.Command;
import panda.command.UnmarkTaskCommand;
import panda.exception.parser.InvalidArgumentException;

/** Parses arguments for the {@code UnmarkTaskCommand}. */
public class UnmarkTaskCommandParser implements CommandParser {

    /**
     * Constructs an {@code UnmarkTaskCommandParser}.
     */
    public UnmarkTaskCommandParser() {
    }

    /**
     * Parses the task number to unmark from the supplied arguments.
     *
     * @param arguments text containing the one-based task number
     * @return an {@link UnmarkTaskCommand} with the parsed task index
     * @throws InvalidArgumentException if the argument is non-numeric or empty
     */
    @Override
    public Command parseArguments(String arguments) throws InvalidArgumentException {
        if (!arguments.matches("\\d+")) {
            throw new InvalidArgumentException("unmark", "unmark TASK_NUMBER");
        }
        return new UnmarkTaskCommand(Integer.parseInt(arguments));
    }
}
