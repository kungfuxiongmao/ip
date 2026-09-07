package panda.exception.parser;

/**
 * Signals that a command argument has an invalid value.
 */
public class InvalidArgumentException extends ParseException {

    /**
     * Creates an exception with a custom error message.
     *
     * @param message Explanation of the argument problem.
     */
    public InvalidArgumentException(String message) {
        super(message);
    }

    /**
     * Creates an exception that explains the expected command usage.
     *
     * @param command Command whose arguments are invalid.
     * @param usage Expected command format.
     */
    public InvalidArgumentException(String command, String usage) {
        super(String.format("Patience, young warrior. To use %s, follow this form: \"%s\"",
                command, usage));
    }
}
