package panda.exception;

/**
 * Represents the base class for exceptions Panda can explain to the user and recover from.
 */
public abstract class ApplicationException extends Exception {

    /**
     * Creates an application exception with a user-facing message.
     *
     * @param message Explanation of the error.
     */
    public ApplicationException(String message) {
        super(message);
    }
}
