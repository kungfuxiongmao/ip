package panda.parser.commandparser;

import panda.command.Command;
import panda.command.TodayCommand;
import panda.exception.parser.InvalidArgumentException;

/** Parses arguments for the {@code TodayCommand}. */
public class TodayCommandParser implements CommandParser {

    @Override
    public Command parseArguments(String arguments) throws InvalidArgumentException {
        if (!arguments.isEmpty()) {
            throw new InvalidArgumentException("today", "today");
        }
        return new TodayCommand();
    }
}
