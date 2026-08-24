package storage;

import exceptions.storage.FileCorruptedException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

/**
 * Converts tasks to and from Panda's line-based save format.
 */
public final class TaskCodec {
    private static final String DELIMITER = " | ";

    private TaskCodec() {
    }

    /**
     * Encodes one task as a line suitable for the save file.
     *
     * @param task task to encode
     * @return serialized task line
     * @throws IllegalArgumentException if the task type is unsupported
     */
    public static String encode(Task task) {
        String state = task.isMarked() ? "1" : "0";
        String description = task.getDescription();

        if (task instanceof Todo) {
            return String.join(DELIMITER, "T", state, description);
        }
        if (task instanceof Deadline deadline) {
            return String.join(DELIMITER, "D", state, description, deadline.getDueDate());
        }
        if (task instanceof Event event) {
            return String.join(DELIMITER, "E", state, description,
                    event.getDateTimeFrom(), event.getDateTimeTo());
        }
        throw new IllegalArgumentException("Unsupported task type: " + task.getClass().getName());
    }

    /**
     * Decodes one save-file line into its corresponding task type.
     *
     * @param line serialized task line
     * @return decoded task
     * @throws FileCorruptedException if the line has an invalid format
     */
    public static Task decode(String line) throws FileCorruptedException {
        if (line == null) {
            throw new FileCorruptedException("Task record cannot be null");
        }

        String[] fields = line.split("\\|", -1);
        for (int index = 0; index < fields.length; index++) {
            fields[index] = fields[index].strip();
        }

        String taskType = fields[0];
        int expectedFieldCount = switch (taskType) {
        case "T" -> 3;
        case "D" -> 4;
        case "E" -> 5;
        default -> throw new FileCorruptedException("Unknown task type: " + taskType);
        };
        if (fields.length != expectedFieldCount) {
            throw new FileCorruptedException(
                    "Expected " + expectedFieldCount + " fields but found " + fields.length);
        }

        boolean marked = decodeState(fields[1]);
        Task task = switch (taskType) {
        case "T" -> new Todo(fields[2]);
        case "D" -> new Deadline(fields[2], fields[3]);
        case "E" -> new Event(fields[2], fields[3], fields[4]);
        default -> throw new AssertionError("Task type was validated before decoding");
        };

        if (marked) {
            task.mark();
        }
        return task;
    }

    /** Validates the completion-state field and converts it to a boolean. */
    private static boolean decodeState(String state) throws FileCorruptedException {
        return switch (state) {
        case "1" -> true;
        case "0" -> false;
        default -> throw new FileCorruptedException("Task state must be 0 or 1");
        };
    }
}
