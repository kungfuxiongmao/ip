package panda.parser;

import panda.command.Command;
import panda.exception.parser.NoCommandFoundException;
import panda.exception.parser.ParseException;
import panda.parser.commandparser.AddDeadlineCommandParser;
import panda.parser.commandparser.AddEventCommandParser;
import panda.parser.commandparser.AddTodoCommandParser;
import panda.parser.commandparser.ByeCommandParser;
import panda.parser.commandparser.CommandParser;
import panda.parser.commandparser.DeleteTaskCommandParser;
import panda.parser.commandparser.DisplayDateCommandParser;
import panda.parser.commandparser.ListTasksCommandParser;
import panda.parser.commandparser.MarkTaskCommandParser;
import panda.parser.commandparser.TodayCommandParser;
import panda.parser.commandparser.UnmarkTaskCommandParser;


/**
 * Converts user-entered text into commands Panda can execute.
 */
public final class Parser {

    private Parser() {
        // Utility class: prevent instantiation.
    }

    /**
     * Maps supported command keywords to parsers that validate their arguments.
     */
    private enum CommandType {
        BYE("bye", new ByeCommandParser()),
        LIST("list", new ListTasksCommandParser()),
        MARK("mark", new MarkTaskCommandParser()),
        UNMARK("unmark", new UnmarkTaskCommandParser()),
        DELETE("delete", new DeleteTaskCommandParser()),
        TODO("todo", new AddTodoCommandParser()),
        DEADLINE("deadline", new AddDeadlineCommandParser()),
        EVENT("event", new AddEventCommandParser()),
        TODAY("today", new TodayCommandParser()),
        DISPLAY("display", new DisplayDateCommandParser());

        private final String keyword;
        private final CommandParser commandParser;

        CommandType(String keyword, CommandParser commandParser) {
            this.keyword = keyword;
            this.commandParser = commandParser;
        }

        /**
         * Returns the argument parser for the supplied command keyword.
         *
         * @param keyword command keyword entered by the user
         * @return matching command parser, or {@code null} if the keyword is not supported
         */
        static CommandParser getCommandParser(String keyword) {
            for (CommandType command : values()) {
                if (command.keyword.equals(keyword)) {
                    return command.commandParser;
                }
            }
            return null;
        }
    }

    /**
     * Removes surrounding whitespace before matching the input to a command.
     *
     * @param input text entered by the user
     * @return command that corresponds to the input
     * @throws ParseException if the command is unrecognized or has invalid arguments
     */
    public static Command parse(String input) throws ParseException {
        return matchCommand(processInput(input));
    }

    /**
     * Simple input processing - removal of surrounding whitespace.
     *
     * @param input text entered by the user
     * @return input without surrounding whitespace
     */
    private static String processInput(String input) {
        return input.strip();
    }

    /**
     * Matches the command keyword, then delegates argument validation to its command parser.
     *
     * @param input user input without surrounding whitespace
     * @return command that handles the input
     * @throws ParseException if the input is empty, the command keyword is unknown, or arguments are invalid
     */
    private static Command matchCommand(String input) throws ParseException {
        if (input.isEmpty()) {
            throw new NoCommandFoundException(input);
        }
        String[] commandParts = input.split("\\s+", 2);
        String command = commandParts[0];
        String arguments = commandParts.length == 2 ? commandParts[1] : "";

        CommandParser commandParser = CommandType.getCommandParser(command);
        if (commandParser == null) {
            throw new NoCommandFoundException(input);
        }
        return commandParser.parseArguments(arguments);
    }

}
