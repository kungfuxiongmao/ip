package panda.parser.commandparser;

import java.time.temporal.Temporal;

import panda.command.Command;
import panda.command.DisplayDateCommand;
import panda.exception.parser.InvalidArgumentException;
import panda.exception.parser.InvalidDateException;
import panda.util.datetime.DateTimeHelper;

/** Parses arguments for a command that displays tasks matching a specific date. */
public class DisplayDateCommandParser implements CommandParser {

    @Override
    public Command parseArguments(String arguments) throws InvalidArgumentException {
        String trimmed = arguments.strip();
        if (!trimmed.equals("/date") && !trimmed.startsWith("/date ") && !trimmed.startsWith("/date\t")) {
            throw new InvalidArgumentException("display", "display /date DATE");
        }
        String dateStr = trimmed.substring(5).strip();
        if (dateStr.isEmpty()) {
            throw new InvalidArgumentException("display", "display /date DATE");
        }
        if (!DateTimeHelper.isValidDateTime(dateStr)) {
            throw new InvalidDateException(dateStr, "display", "display /date DATE");
        }
        Temporal date = DateTimeHelper.parse(dateStr);
        return new DisplayDateCommand(date);
    }
}
