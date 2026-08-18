package task;

import exceptions.task.TaskAlreadyMarkedException;
import exceptions.task.TaskAlreadyUnmarkedException;

/**
 * Stores the tasks entered during the current Panda session.
 * Tasks are kept only in memory and are discarded when Panda closes.
 */
public class TaskList {
    private static final int MAX_TASKS = 100;
    private final Task[] tasks;
    private int size;

    /**
     * Creates an empty task list with room for up to 100 tasks.
     */
    public TaskList() {
        this.tasks = new Task[MAX_TASKS];
    }

    /**
     * Adds a task to this list.
     *
     * @param task text entered by the user
     */
    public void add(String task) {
        tasks[size] = new Task(task);
        size++;
    }

    /**
     * Marks the task with the supplied one-based task number.
     *
     * @param taskNumber number displayed beside the task
     * @return the task that was marked
     * @throws ArrayIndexOutOfBoundsException if the number does not identify a task in this list
     * @throws TaskAlreadyMarkedException if the task is already marked
     */
    public Task markEvent(int taskNumber) throws TaskAlreadyMarkedException {
        int arrayIndex = taskNumber - 1;
        if (arrayIndex < 0 || arrayIndex >= size) {
            throw new ArrayIndexOutOfBoundsException("Maximum number of tasks is: " + this.size);
        }

        Task task = tasks[arrayIndex];
        if (task.isMarked()) {
            throw new TaskAlreadyMarkedException(task);
        }
        task.mark();
        return task;
    }

    /**
     * Removes the mark from the task with the supplied one-based task number.
     *
     * @param taskNumber number displayed beside the task
     * @return the task that was unmarked
     * @throws ArrayIndexOutOfBoundsException if the number does not identify a task in this list
     * @throws TaskAlreadyUnmarkedException if the task is already unmarked
     */
    public Task unmarkEvent(int taskNumber) throws TaskAlreadyUnmarkedException {
        int arrayIndex = taskNumber - 1;
        if (arrayIndex < 0 || arrayIndex >= size) {
            throw new ArrayIndexOutOfBoundsException("Maximum number of tasks is: " + this.size);
        }

        Task task = tasks[arrayIndex];
        if (!task.isMarked()) {
            throw new TaskAlreadyUnmarkedException(task);
        }
        task.unmark();
        return task;
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
        StringBuilder result = new StringBuilder("Here are the tasks in your list:")
                .append(System.lineSeparator());
        for (int index = 0; index < size; index++) {
            result.append(index + 1)
                    .append(".")
                    .append(tasks[index].toString())
                    .append(System.lineSeparator());
        }
        return result.toString().stripTrailing();
    }
}
