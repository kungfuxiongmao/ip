package parser.commandparser;

import commands.AddTodoCommand;
import commands.Command;
import exceptions.parser.InvalidArgumentException;

/** Parses arguments for a command that adds a to-do task. */
public class AddTodoCommandParser implements CommandParser {

    @Override
    public Command parseArguments(String arguments) throws InvalidArgumentException {
        if (arguments.isEmpty()) {
            throw new InvalidArgumentException("todo", "todo DESCRIPTION");
        }
        return new AddTodoCommand(arguments);
    }
}
