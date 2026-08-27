package panda.parser.commandparser;

import panda.command.Command;
import panda.command.MarkTaskCommand;
import panda.exception.parser.InvalidArgumentException;

/**
 * Parses arguments for the {@code mark} command.
 */
public class MarkTaskCommandParser implements CommandParser {

    @Override
    public Command parseArguments(String arguments) throws InvalidArgumentException {
        if (!arguments.matches("\\d+")) {
            throw new InvalidArgumentException("mark", "mark TASK_NUMBER");
        }
        return new MarkTaskCommand(Integer.parseInt(arguments));
    }
}
