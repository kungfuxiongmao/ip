package parser.commandparser;

import commands.AddDeadlineCommand;
import commands.Command;
import exceptions.parser.InvalidArgumentException;

/** Parses arguments for a command that adds a deadline task. */
public class AddDeadlineCommandParser implements CommandParser {

    @Override
    public Command parseArguments(String arguments) throws InvalidArgumentException {
        String[] deadlineParts = arguments.split("\\s+/by\\s+", 2);
        if (deadlineParts.length != 2 || deadlineParts[0].isEmpty() || deadlineParts[1].isEmpty()) {
            throw new InvalidArgumentException("deadline", "deadline DESCRIPTION /by DATE");
        }
        return new AddDeadlineCommand(deadlineParts[0], deadlineParts[1]);
    }
}
