package panda.parser.commandparser;

import panda.command.Command;
import panda.command.ListTasksCommand;
import panda.exception.parser.InvalidArgumentException;

/** Parses arguments for the {@code ListTasksCommand}. */
public class ListTasksCommandParser implements CommandParser {

    /**
     * Constructs a {@code ListTasksCommandParser}.
     */
    public ListTasksCommandParser() {
    }

    /**
     * Validates that no extra arguments are passed to the {@code list} command.
     *
     * @param arguments text following the list command keyword
     * @return a {@link ListTasksCommand} instance
     * @throws InvalidArgumentException if extraneous arguments are supplied
     */
    @Override
    public Command parseArguments(String arguments) throws InvalidArgumentException {
        if (!arguments.isEmpty()) {
            throw new InvalidArgumentException("list", "list");
        }
        return new ListTasksCommand();
    }
}
