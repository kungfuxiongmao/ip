package panda.exception.parser;

/**
 * Signals that the user did not enter a command or task description.
 */
public class NoCommandFoundException extends ParseException {

    /**
     * Creates an exception for an unrecognised command.
     *
     * @param input Unrecognised input, or an empty string for blank input.
     */
    public NoCommandFoundException(String input) {
        super(input.isEmpty()
                ? "Even I cannot guide silence. Tell me what must be done."
                : "Hmm. \"" + input + "\" is a move I have not taught you. Try one you know.");
    }
}
