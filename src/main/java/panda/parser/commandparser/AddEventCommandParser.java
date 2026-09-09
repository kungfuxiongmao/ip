package panda.parser.commandparser;

import java.time.format.DateTimeParseException;
import java.time.temporal.Temporal;

import panda.command.AddEventCommand;
import panda.command.Command;
import panda.exception.parser.InvalidArgumentException;
import panda.exception.parser.InvalidDateException;
import panda.util.datetime.DateTimeHelper;

/**
 * Parses arguments for a command that adds an event task.
 */
public class AddEventCommandParser implements CommandParser {

    /**
     * Constructs an {@code AddEventCommandParser}.
     */
    public AddEventCommandParser() {
    }

    /**
     * Parses the event description, start date/time, and end date/time from the supplied arguments.
     *
     * @param arguments Text containing description, {@code /from START}, and {@code /to END}.
     * @return An {@link AddEventCommand} with the parsed description and date ranges.
     * @throws InvalidArgumentException If the syntax is malformed or date formats are invalid.
     */
    @Override
    public Command parseArguments(String arguments) throws InvalidArgumentException {
        String[] eventParts = arguments.split("\\s+/from\\s+", 2);
        if (eventParts.length != 2 || eventParts[0].isBlank()) {
            throw new InvalidArgumentException("event", "event DESCRIPTION /from START /to END");
        }
        String description = eventParts[0].strip();
        String[] timeParts = eventParts[1].split("\\s+/to\\s+", 2);
        if (timeParts.length != 2 || timeParts[0].isBlank() || timeParts[1].isBlank()) {
            throw new InvalidArgumentException("event", "event DESCRIPTION /from START /to END");
        }
        String startDateTimeString = timeParts[0].strip();
        String endDateTimeString = timeParts[1].strip();
        Temporal dateTimeFrom;
        try {
            dateTimeFrom = DateTimeHelper.parse(startDateTimeString);
        } catch (DateTimeParseException exception) {
            throw new InvalidDateException(
                    startDateTimeString, "event", "event DESCRIPTION /from START /to END");
        }
        Temporal dateTimeTo;
        try {
            dateTimeTo = DateTimeHelper.parse(endDateTimeString);
        } catch (DateTimeParseException exception) {
            throw new InvalidDateException(
                    endDateTimeString, "event", "event DESCRIPTION /from START /to END");
        }
        return new AddEventCommand(description, dateTimeFrom, dateTimeTo);
    }
}
