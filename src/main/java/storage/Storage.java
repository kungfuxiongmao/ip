package storage;

import exceptions.storage.FileCorruptedException;
import task.Task;
import ui.UI;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads and writes Panda's task save file.
 */
public final class Storage {
    private static final Path SAVE_FILE = Path.of("data", "tasks.txt");

    private Storage() {
    }

    /**
     * Reads and decodes every task in the save file.
     * A missing file represents an empty task list. A corrupted file is left
     * untouched and represented by a {@code null} return value.
     *
     * @return decoded tasks, or {@code null} if any record is corrupted
     * @throws IOException if the file cannot be read
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
            UI.printMessage("The saved file is broken... I can only restart your task list.\n"
                    + exception.getMessage());
            return null;
        }
        return tasks;
    }

    /**
     * Overwrites the save file with the supplied tasks.
     *
     * @param tasks tasks to save
     * @throws IOException if the file cannot be written
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
