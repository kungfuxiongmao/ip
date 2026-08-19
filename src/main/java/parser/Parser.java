package parser;

import commands.Command;
import exceptions.parser.NoCommandFoundException;
import exceptions.parser.ParseException;
import parser.commandparser.ByeCommandParser;
import parser.commandparser.CommandParser;
import parser.commandparser.DeadlineCommandParser;
import parser.commandparser.EventCommandParser;
import parser.commandparser.ListCommandParser;
import parser.commandparser.MarkCommandParser;
import parser.commandparser.TodoCommandParser;
import parser.commandparser.UnmarkCommandParser;


/**
 * Converts user-entered text into commands Panda can execute.
 */
public class Parser {
    /**
     * Removes surrounding whitespace before matching the input to a command.
     *
     * @param input text entered by the user
     * @return command that corresponds to the input
     */
    public static Command parse(String input) throws ParseException {
        return matchCommand(processInput(input));
    }

    /**
     * Simple input processing - removal of surrounding whitespace
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
     */
    private static Command matchCommand(String input) throws ParseException {
        if (input.isEmpty()) {
            throw new NoCommandFoundException(input);
        }
        String[] commandParts = input.split("\\s+", 2);
        String command = commandParts[0];
        String arguments = commandParts.length == 2 ? commandParts[1] : "";

        return getCommandParser(command, input).parseArguments(arguments);
    }

    /**
     * Returns the parser responsible for arguments of a recognised command keyword.
     *
     * @param command command keyword entered by the user
     * @param input complete cleaned user input
     * @return parser for the command keyword
     * @throws NoCommandFoundException if the keyword is not recognised
     */
    private static CommandParser getCommandParser(String command, String input) throws NoCommandFoundException {
        return switch (command) {
        case "bye" -> new ByeCommandParser();
        case "list" -> new ListCommandParser();
        case "mark" -> new MarkCommandParser();
        case "unmark" -> new UnmarkCommandParser();
        case "todo" -> new TodoCommandParser();
        case "deadline" -> new DeadlineCommandParser();
        case "event" -> new EventCommandParser();
        default -> throw new NoCommandFoundException(input);
        };
    }
}
