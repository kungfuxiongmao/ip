package parser.commandparser;

import commands.Command;
import commands.MarkTaskCommand;
import exceptions.parser.InvalidArgumentException;

/** Parses arguments for the {@code MarkTaskCommand}. */
public class MarkTaskCommandParser implements CommandParser {

    @Override
    public Command parseArguments(String arguments) throws InvalidArgumentException {
        if (!arguments.matches("\\d+")) {
            throw new InvalidArgumentException("mark", "mark TASK_NUMBER");
        }
        return new MarkTaskCommand(Integer.parseInt(arguments));
    }
}
