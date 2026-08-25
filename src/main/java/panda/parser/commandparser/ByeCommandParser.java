package panda.parser.commandparser;

import panda.command.ByeCommand;
import panda.command.Command;
import panda.exception.parser.InvalidArgumentException;

/** Parses arguments for the {@code bye} command. */
public class ByeCommandParser implements CommandParser {

    @Override
    public Command parseArguments(String arguments) throws InvalidArgumentException {
        if (!arguments.isEmpty()) {
            throw new InvalidArgumentException("bye", "bye");
        }
        return new ByeCommand();
    }
}
