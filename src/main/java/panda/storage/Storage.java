package panda.storage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import panda.exception.storage.FileCorruptedException;
import panda.task.Task;
import panda.ui.Ui;

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
     * A missing file represents an empty task list. A corrupted file is left
     * untouched and represented by a {@code null} return value.
     *
     * @return Decoded tasks, or {@code null} if any record is corrupted.
     * @throws IOException If the file cannot be read.
     */
    public static List<Task> readTasks() throws IOException {
        if (Files.notExists(SAVE_FILE)) {
            return new ArrayList<>();
        }

        List<Task> tasks = new ArrayList<>();
        try {
            for (String line : Files.readAllLines(SAVE_FILE, StandardCharsets.UTF_8)) {
                tasks.add(TaskCodec.decode(line));
            }
        } catch (FileCorruptedException exception) {
            Ui.printMessage("The saved file is broken... I can only restart your task list.\n"
                    + exception.getMessage());
            return null;
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
