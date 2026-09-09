package panda.parser.commandparser;

import java.time.format.DateTimeParseException;
import java.time.temporal.Temporal;

import panda.command.Command;
import panda.command.DisplayDateCommand;
import panda.exception.parser.InvalidArgumentException;
import panda.exception.parser.InvalidDateException;
import panda.util.datetime.DateTimeHelper;

/**
 * Parses arguments for a command that displays tasks matching a specific date.
 */
public class DisplayDateCommandParser implements CommandParser {

    /**
     * Constructs a {@code DisplayDateCommandParser}.
     */
    public DisplayDateCommandParser() {
    }

    /**
     * Parses the date filter argument for the {@code display} command.
     *
     * @param arguments Text containing the {@code /date DATE} parameter.
     * @return A {@link DisplayDateCommand} configured with the target date filter.
     * @throws InvalidArgumentException If the {@code /date} delimiter is missing or the date value is invalid.
     */
    @Override
    public Command parseArguments(String arguments) throws InvalidArgumentException {
        String trimmed = arguments.strip();
        if (!trimmed.equals("/date") && !trimmed.startsWith("/date ") && !trimmed.startsWith("/date\t")) {
            throw new InvalidArgumentException("display", "display /date DATE");
        }
        String dateString = trimmed.substring(5).strip();
        if (dateString.isEmpty()) {
            throw new InvalidArgumentException("display", "display /date DATE");
        }
        Temporal date;
        try {
            date = DateTimeHelper.parse(dateString);
        } catch (DateTimeParseException exception) {
            throw new InvalidDateException(dateString, "display", "display /date DATE");
        }
        return new DisplayDateCommand(date);
    }
}
