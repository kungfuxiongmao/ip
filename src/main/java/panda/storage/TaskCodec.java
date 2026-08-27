package panda.storage;

import java.time.format.DateTimeParseException;
import java.util.Objects;

import panda.exception.storage.FileCorruptedException;
import panda.task.Deadline;
import panda.task.Event;
import panda.task.Task;
import panda.task.Todo;
import panda.util.datetime.DateTimeHelper;

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
            return String.join(DELIMITER, "D", state, description,
                    DateTimeHelper.saveDate(deadline.getDueDate()));
        }
        if (task instanceof Event event) {
            return String.join(DELIMITER, "E", state, description,
                    DateTimeHelper.saveDate(event.getDateTimeFrom()),
                    DateTimeHelper.saveDate(event.getDateTimeTo()));
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
            if (Objects.equals(fields[index], "")) {
                throw new FileCorruptedException("Missing data");
            }
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
        Task task;
        try {
            task = switch (taskType) {
            case "T" -> new Todo(fields[2]);
            case "D" -> new Deadline(fields[2], DateTimeHelper.loadDate(fields[3]));
            case "E" -> new Event(fields[2], DateTimeHelper.loadDate(fields[3]), DateTimeHelper.loadDate(fields[4]));
            default -> throw new AssertionError("Task type was validated before decoding");
            };
        } catch (DateTimeParseException | IllegalArgumentException e) {
            throw new FileCorruptedException("Failed to parse date: " + e.getMessage());
        }

        if (marked) {
            task.mark();
        }
        return task;
    }

    /**
     * Validates the completion-state field and converts it to a boolean.
     *
     * @param state state flag string ("1" for done, "0" for not done)
     * @return {@code true} if marked; {@code false} if unmarked
     * @throws FileCorruptedException if the state value is neither "0" nor "1"
     */
    private static boolean decodeState(String state) throws FileCorruptedException {
        return switch (state) {
        case "1" -> true;
        case "0" -> false;
        default -> throw new FileCorruptedException("Task state must be 0 or 1");
        };
    }
}
