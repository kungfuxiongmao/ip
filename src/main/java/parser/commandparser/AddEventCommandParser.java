package parser.commandparser;

import commands.AddEventCommand;
import commands.Command;
import exceptions.parser.InvalidArgumentException;

/** Parses arguments for a command that adds an event task. */
public class AddEventCommandParser implements CommandParser {

    @Override
    public Command parseArguments(String arguments) throws InvalidArgumentException {
        String[] eventParts = arguments.split("\\s+/from\\s+|\\s+/to\\s+", 3);
        if (eventParts.length != 3 || eventParts[0].isEmpty()
                || eventParts[1].isEmpty() || eventParts[2].isEmpty()) {
            throw new InvalidArgumentException("event", "event DESCRIPTION /from START /to END");
        }
        return new AddEventCommand(eventParts[0], eventParts[1], eventParts[2]);
    }
}
