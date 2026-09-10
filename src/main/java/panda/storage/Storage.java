package panda.storage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import panda.exception.storage.FileCorruptedException;
import panda.task.Task;

/**
 * Reads and writes Panda's task save file.
 */
public final class Storage {
    private static final Path SAVE_FILE = Path.of("data", "tasks.txt");

    private Storage() {
        // Utility class: prevent accidental instantiation.
    }

    /**
     * Reads and decodes every task in the save file.
     * <p>
     * A missing file represents an empty task list.
     *
     * @return Decoded tasks, or an empty list if the save file does not exist.
     * @throws FileCorruptedException If any saved task record is malformed.
     * @throws IOException If the file cannot be read.
     */
    public static List<Task> readTasks() throws FileCorruptedException, IOException {
        if (Files.notExists(SAVE_FILE)) {
            return List.of();
        }

        List<Task> tasks = new ArrayList<>();
        for (String line : Files.readAllLines(SAVE_FILE, StandardCharsets.UTF_8)) {
            tasks.add(TaskCodec.decode(line));
        }
        return tasks;
    }

    /**
     * Overwrites the save file with the supplied tasks.
     *
     * @param tasks Tasks to save.
     * @throws IOException If the file cannot be written.
     */
    public static void saveTasks(List<Task> tasks) throws IOException {
        assert tasks != null : "Task list passed to storage must not be null";

        Path parentDirectory = SAVE_FILE.getParent();
        if (parentDirectory != null) {
            Files.createDirectories(parentDirectory);
        }

        List<String> lines = tasks.stream()
                .map(TaskCodec::encode)
                .toList();
        Files.write(SAVE_FILE, lines, StandardCharsets.UTF_8);
    }
}
