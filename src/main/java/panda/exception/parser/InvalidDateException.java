package panda.exception.parser;

/**
 * Signals that a date or date-time command argument has an invalid format or value.
 * <p>
 * Inherits from {@link InvalidArgumentException} and provides a more descriptive error message
 * detailing the accepted date and date-time formats (e.g. {@code "d/M/yyyy"} or {@code "d/M/yyyy HH:mm"}).
 */
public class InvalidDateException extends InvalidArgumentException {

    /**
     * Creates an exception that explains the expected date and date-time format for a command.
     *
     * @param command command whose date argument is invalid
     * @param usage expected command format
     */
    public InvalidDateException(String command, String usage) {
        super(String.format(
                "OOPS! Panda needs a valid date in \"d/M/yyyy\" (e.g. 2/12/2019) or "
                        + "\"d/M/yyyy HH:mm\" (e.g. 2/12/2019 18:00) format.%n"
                        + "Usage: \"%s\" ",
                usage));
    }

    /**
     * Creates an exception that specifies the invalid date input, expected formats, and command usage.
     *
     * @param invalidDate the invalid date string supplied by the user
     * @param command command whose date argument is invalid
     * @param usage expected command format
     */
    public InvalidDateException(String invalidDate, String command, String usage) {
        super(String.format(
                "OOPS! \"%s\" is not a valid date. Panda accepts \"d/M/yyyy\" (e.g. 2/12/2019) "
                        + "or \"d/M/yyyy HH:mm\" (e.g. 2/12/2019 18:00).%n"
                        + "Usage: \"%s\" ",
                invalidDate, usage));
    }
}
