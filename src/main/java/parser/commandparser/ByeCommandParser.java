package parser.commandparser;

import commands.ByeCommand;
import commands.Command;
import exceptions.parser.InvalidArgumentException;

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
