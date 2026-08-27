package panda.parser.commandparser;

import panda.command.AddTodoCommand;
import panda.command.Command;
import panda.exception.parser.InvalidArgumentException;

/** Parses arguments for a command that adds a to-do task. */
public class AddTodoCommandParser implements CommandParser {

    /**
     * Constructs an {@code AddTodoCommandParser}.
     */
    public AddTodoCommandParser() {
    }

    /**
     * Parses the to-do description from the supplied arguments.
     *
     * @param arguments description of the task
     * @return an {@link AddTodoCommand} with the parsed description
     * @throws InvalidArgumentException if the description is empty
     */
    @Override
    public Command parseArguments(String arguments) throws InvalidArgumentException {
        if (arguments.isEmpty()) {
            throw new InvalidArgumentException("todo", "todo DESCRIPTION");
        }
        return new AddTodoCommand(arguments);
    }
}
