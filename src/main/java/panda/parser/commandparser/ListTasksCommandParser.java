package panda.parser.commandparser;

import panda.command.Command;
import panda.command.ListTasksCommand;
import panda.exception.parser.InvalidArgumentException;

/** Parses arguments for the {@code ListTasksCommand}. */
public class ListTasksCommandParser implements CommandParser {

    @Override
    public Command parseArguments(String arguments) throws InvalidArgumentException {
        if (!arguments.isEmpty()) {
            throw new InvalidArgumentException("list", "list");
        }
        return new ListTasksCommand();
    }
}
