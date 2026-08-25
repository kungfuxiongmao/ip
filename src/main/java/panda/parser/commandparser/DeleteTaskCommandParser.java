package panda.parser.commandparser;

import panda.command.Command;
import panda.command.DeleteTaskCommand;
import panda.exception.parser.InvalidArgumentException;

/** Parses arguments for the {@code DeleteTaskCommand}. */
public class DeleteTaskCommandParser implements CommandParser {

    @Override
    public Command parseArguments(String arguments) throws InvalidArgumentException {
        if (!arguments.matches("\\d+")) {
            throw new InvalidArgumentException("delete", "delete TASK_NUMBER");
        }
        return new DeleteTaskCommand(Integer.parseInt(arguments));
    }
}
