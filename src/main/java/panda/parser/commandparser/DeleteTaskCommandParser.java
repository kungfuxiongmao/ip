package panda.parser.commandparser;

import panda.command.Command;
import panda.command.DeleteTaskCommand;
import panda.exception.parser.InvalidArgumentException;

/**
 * Parses arguments for the {@code delete} command.
 */
public class DeleteTaskCommandParser implements CommandParser {

    /**
     * Constructs a {@code DeleteTaskCommandParser}.
     */
    public DeleteTaskCommandParser() {
    }

    /**
     * Parses the task number to delete from the supplied arguments.
     *
     * @param arguments Text containing the one-based task number.
     * @return A {@link DeleteTaskCommand} with the parsed task index.
     * @throws InvalidArgumentException If the argument is non-numeric or empty.
     */
    @Override
    public Command parseArguments(String arguments) throws InvalidArgumentException {
        if (!arguments.matches("\\d+")) {
            throw new InvalidArgumentException("delete", "delete TASK_NUMBER");
        }
        return new DeleteTaskCommand(Integer.parseInt(arguments));
    }
}
