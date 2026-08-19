package parser.commandparser;

import commands.Command;
import commands.UnmarkTaskCommand;
import exceptions.parser.InvalidArgumentException;

/** Parses arguments for the {@code unmark} command. */
public class UnmarkCommandParser implements CommandParser {

    @Override
    public Command parseArguments(String arguments) throws InvalidArgumentException {
        if (!arguments.matches("\\d+")) {
            throw new InvalidArgumentException("unmark", "unmark TASK_NUMBER");
        }
        return new UnmarkTaskCommand(Integer.parseInt(arguments));
    }
}
