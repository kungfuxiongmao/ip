package panda.parser.commandparser;

import panda.command.Command;
import panda.command.UnmarkTaskCommand;
import panda.exception.parser.InvalidArgumentException;

/** Parses arguments for the {@code UnmarkTaskCommand}. */
public class UnmarkTaskCommandParser implements CommandParser {

    @Override
    public Command parseArguments(String arguments) throws InvalidArgumentException {
        if (!arguments.matches("\\d+")) {
            throw new InvalidArgumentException("unmark", "unmark TASK_NUMBER");
        }
        return new UnmarkTaskCommand(Integer.parseInt(arguments));
    }
}
