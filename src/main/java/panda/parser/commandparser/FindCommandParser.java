package panda.parser.commandparser;

import panda.command.Command;
import panda.command.FindCommand;
import panda.exception.parser.InvalidArgumentException;

/**
 * Parses arguments for the {@code FindCommand}.
 */
public class FindCommandParser implements CommandParser {

    @Override
    public Command parseArguments(String arguments) throws InvalidArgumentException {
        String keyword = arguments.strip();
        if (keyword.isEmpty()) {
            throw new InvalidArgumentException("find", "find KEYWORD");
        }
        return new FindCommand(keyword);
    }
}
