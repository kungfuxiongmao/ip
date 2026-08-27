package panda.parser.commandparser;

import panda.command.Command;
import panda.command.TodayCommand;
import panda.exception.parser.InvalidArgumentException;

/** Parses arguments for the {@code TodayCommand}. */
public class TodayCommandParser implements CommandParser {

    /**
     * Constructs a {@code TodayCommandParser}.
     */
    public TodayCommandParser() {
    }

    /**
     * Validates that no extra arguments are passed to the {@code today} command.
     *
     * @param arguments text following the today command keyword
     * @return a {@link TodayCommand} instance
     * @throws InvalidArgumentException if extraneous arguments are supplied
     */
    @Override
    public Command parseArguments(String arguments) throws InvalidArgumentException {
        if (!arguments.isEmpty()) {
            throw new InvalidArgumentException("today", "today");
        }
        return new TodayCommand();
    }
}
