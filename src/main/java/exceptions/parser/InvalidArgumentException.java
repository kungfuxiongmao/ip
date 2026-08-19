package exceptions.parser;

/**
 * Signals that a command argument has an invalid value.
 */
public class InvalidArgumentException extends ParseException {

    /**
     * Creates an exception that explains the expected command usage.
     *
     * @param command command whose arguments are invalid
     * @param usage expected command format
     */
    public InvalidArgumentException(String command, String usage) {
        super(String.format(
                "OOPS! Panda needs the %s command written like this: \"%s\" ", command, usage));
    }
}
