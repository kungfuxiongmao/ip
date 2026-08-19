package parser.commandparser;

import commands.Command;
import commands.MarkTaskCommand;
import exceptions.parser.InvalidArgumentException;

/** Parses arguments for the {@code mark} command. */
public class MarkCommandParser implements CommandParser {

    @Override
    public Command parseArguments(String arguments) throws InvalidArgumentException {
        if (!arguments.matches("\\d+")) {
            throw new InvalidArgumentException("mark", "mark TASK_NUMBER");
        }
        return new MarkTaskCommand(Integer.parseInt(arguments));
    }
}
