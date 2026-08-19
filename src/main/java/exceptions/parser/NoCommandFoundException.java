package exceptions.parser;

/**
 * Signals that the user did not enter a command or task description.
 */
public class NoCommandFoundException extends ParseException {

    /**
     * Creates an exception for an unrecognised command.
     *
     * @param input unrecognised input, or an empty string for blank input
     */
    public NoCommandFoundException(String input) {
        super(input.isEmpty()
                ? "OOPS! Panda needs a command before it can help. :>"
                : "OOPS! Panda does not know the \"" + input + "\" command yet. :<");
    }
}
