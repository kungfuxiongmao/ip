package panda.exception.storage;

/**
 * Indicates that a saved task record does not follow Panda's storage format.
 */
public class FileCorruptedException extends Exception {
    /**
     * Creates an exception describing the invalid record.
     *
     * @param message description of the format problem
     */
    public FileCorruptedException(String message) {
        super(message);
    }
}
