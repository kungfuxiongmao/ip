package tasklist;

/**
 * Stores the tasks entered during the current Panda session.
 * Tasks are kept only in memory and are discarded when Panda closes.
 */
public class TaskList {
    private static final int MAX_TASKS = 100;
    private final String[] tasks;
    private int size;

    /**
     * Creates an empty task list with room for up to 100 tasks.
     */
    public TaskList() {
        this.tasks = new String[MAX_TASKS];
    }

    /**
     * Adds a task to this list.
     *
     * @param task text entered by the user
     */
    public void add(String task) {
        tasks[size] = task;
        size++;
    }

    /**
     * Returns all tasks as a numbered, multi-line string.
     *
     * @return the formatted task list
     */
    @Override
    public String toString() {
        if (size == 0) {
            return "~~~ Empty List ~~~";
        }
        StringBuilder result = new StringBuilder();
        for (int index = 0; index < size; index++) {
            result.append(index + 1)
                    .append(". ")
                    .append(tasks[index])
                    .append(System.lineSeparator());
        }
        return result.toString().stripTrailing();
    }
}
