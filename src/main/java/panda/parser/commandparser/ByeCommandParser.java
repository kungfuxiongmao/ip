package panda.parser.commandparser;

import panda.command.ByeCommand;
import panda.command.Command;
import panda.exception.parser.InvalidArgumentException;

/** Parses arguments for the {@code bye} command. */
public class ByeCommandParser implements CommandParser {

    /**
     * Constructs a {@code ByeCommandParser}.
     */
    public ByeCommandParser() {
    }

    /**
     * Validates that no extra arguments are passed to the {@code bye} command.
     *
     * @param arguments text following the bye command
     * @return a {@link ByeCommand} instance
     * @throws InvalidArgumentException if extraneous arguments are supplied
     */
    @Override
    public Command parseArguments(String arguments) throws InvalidArgumentException {
        if (!arguments.isEmpty()) {
            throw new InvalidArgumentException("bye", "bye");
        }
        return new ByeCommand();
    }
}
