package task;

import exceptions.task.TaskAlreadyMarkedException;
import exceptions.task.TaskAlreadyUnmarkedException;
import exceptions.task.InvalidTaskListIndexException;

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
     * Creates and adds a to-do task.
     *
     * @param description description of the task
     * @return the newly added task
     */
    public Task addTodo(String description) {
        return add(new Todo(description));
    }

    /**
     * Creates and adds a deadline task.
     *
     * @param description description of the task
     * @param dueDate date by which the task is due
     * @return the newly added task
     */
    public Task addDeadline(String description, String dueDate) {
        return add(new Deadline(description, dueDate));
    }

    /**
     * Creates and adds an event task.
     *
     * @param description description of the event
     * @param dateTimeFrom event start date and time
     * @param dateTimeTo event end date and time
     * @return the newly added task
     */
    public Task addEvent(String description, String dateTimeFrom, String dateTimeTo) {
        return add(new Event(description, dateTimeFrom, dateTimeTo));
    }

    /**
     * Returns the number of tasks currently in this list.
     *
     * @return task count
     */
    public int getSize() {
        return size;
    }

    /** Adds an already-created task to this list. */
    private Task add(Task task) {
        tasks[size] = task;
        size++;
        return task;
    }

    /**
     * Marks the task with the supplied one-based task number.
     *
     * @param taskNumber number displayed beside the task
     * @return the task that was marked
     * @throws InvalidTaskListIndexException if the number does not identify a task in this list
     * @throws TaskAlreadyMarkedException if the task is already marked
     */
    public Task markEvent(int taskNumber) throws TaskAlreadyMarkedException, InvalidTaskListIndexException {
        int arrayIndex = taskNumber - 1;
        if (arrayIndex < 0 || arrayIndex >= size) {
            throw new InvalidTaskListIndexException(taskNumber, size);
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
     * @throws InvalidTaskListIndexException if the number does not identify a task in this list
     * @throws TaskAlreadyUnmarkedException if the task is already unmarked
     */
    public Task unmarkEvent(int taskNumber) throws TaskAlreadyUnmarkedException, InvalidTaskListIndexException {
        int arrayIndex = taskNumber - 1;
        if (arrayIndex < 0 || arrayIndex >= size) {
            throw new InvalidTaskListIndexException(taskNumber, size);
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
