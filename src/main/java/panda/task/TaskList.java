package panda.task;

import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.List;

import panda.exception.task.InvalidTaskListIndexException;
import panda.exception.task.TaskAlreadyMarkedException;
import panda.exception.task.TaskAlreadyUnmarkedException;
import panda.exception.task.TaskListAlreadyInstantiatedException;
import panda.util.datetime.DateTimeHelper;

/**
 * Stores the tasks entered during the current Panda session.
 * Tasks are kept only in memory and are discarded when Panda closes.
 */
public class TaskList {
    private static TaskList instance;
    private final ArrayList<Task> tasks;

    /**
     * Creates an empty task list.
     */
    private TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Initializes the singleton task list with the supplied tasks.
     * The tasks are copied into the internal list so that later changes to the
     * supplied list cannot change this task list.
     *
     * @param initialTasks Tasks to include when creating the singleton.
     * @return The initialized singleton instance.
     * @throws TaskListAlreadyInstantiatedException If the singleton has already been initialized.
     */
    public static TaskList of(List<Task> initialTasks) throws TaskListAlreadyInstantiatedException {
        if (instance != null) {
            throw new TaskListAlreadyInstantiatedException();
        }
        instance = new TaskList();
        instance.tasks.addAll(initialTasks);
        return instance;
    }

    /**
     * Returns the singleton task list, initializing an empty one if necessary.
     *
     * @return The singleton instance.
     */
    public static TaskList getInstance() {
        if (instance == null) {
            instance = new TaskList();
        }
        return instance;
    }

    /**
     * Creates and adds a to-do task.
     *
     * @param description Description of the task.
     * @return The newly added task.
     */
    public Task addTodo(String description) {
        return add(new Todo(description));
    }

    /**
     * Creates and adds a deadline task.
     *
     * @param description Description of the task.
     * @param dueDate Date by which the task is due as a {@link Temporal}.
     * @return The newly added task.
     */
    public Task addDeadline(String description, Temporal dueDate) {
        return add(new Deadline(description, dueDate));
    }

    /**
     * Creates and adds an event task.
     *
     * @param description Description of the event.
     * @param dateTimeFrom Event start date and time as a {@link Temporal}.
     * @param dateTimeTo Event end date and time as a {@link Temporal}.
     * @return The newly added task.
     */
    public Task addEvent(String description, Temporal dateTimeFrom, Temporal dateTimeTo) {
        return add(new Event(description, dateTimeFrom, dateTimeTo));
    }

    /**
     * Returns the number of tasks currently in this list.
     *
     * @return Task count.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns an unmodifiable snapshot of the tasks currently in this list.
     *
     * @return Snapshot of the current tasks.
     */
    public List<Task> getTasks() {
        return List.copyOf(tasks);
    }

    /**
     * Adds an already-created task to this list.
     *
     * @param task The task to append to the list.
     * @return The added task.
     */
    private Task add(Task task) {
        tasks.add(task);
        return task;
    }

    /**
     * Marks the task with the supplied one-based task number.
     *
     * @param taskNumber Number displayed beside the task.
     * @return The task that was marked.
     * @throws InvalidTaskListIndexException If the number does not identify a task in this list.
     * @throws TaskAlreadyMarkedException If the task is already marked.
     */
    public Task markEvent(int taskNumber) throws TaskAlreadyMarkedException, InvalidTaskListIndexException {
        int arrayIndex = taskNumber - 1;
        if (arrayIndex < 0 || arrayIndex >= tasks.size()) {
            throw new InvalidTaskListIndexException(taskNumber, tasks.size());
        }

        Task task = tasks.get(arrayIndex);
        if (task.isMarked()) {
            throw new TaskAlreadyMarkedException(task);
        }
        task.mark();
        return task;
    }

    /**
     * Removes the mark from the task with the supplied one-based task number.
     *
     * @param taskNumber Number displayed beside the task.
     * @return The task that was unmarked.
     * @throws InvalidTaskListIndexException If the number does not identify a task in this list.
     * @throws TaskAlreadyUnmarkedException If the task is already unmarked.
     */
    public Task unmarkEvent(int taskNumber) throws TaskAlreadyUnmarkedException, InvalidTaskListIndexException {
        int arrayIndex = taskNumber - 1;
        if (arrayIndex < 0 || arrayIndex >= tasks.size()) {
            throw new InvalidTaskListIndexException(taskNumber, tasks.size());
        }

        Task task = tasks.get(arrayIndex);
        if (!task.isMarked()) {
            throw new TaskAlreadyUnmarkedException(task);
        }
        task.unmark();
        return task;
    }

    /**
     * Removes the task with the supplied one-based task number.
     *
     * @param taskNumber Number displayed beside the task.
     * @return The removed task.
     * @throws InvalidTaskListIndexException If the number does not identify a task in this list.
     */
    public Task delete(int taskNumber) throws InvalidTaskListIndexException {
        int arrayIndex = taskNumber - 1;
        if (arrayIndex < 0 || arrayIndex >= tasks.size()) {
            throw new InvalidTaskListIndexException(taskNumber, tasks.size());
        }
        return tasks.remove(arrayIndex);
    }

    /**
     * Returns a numbered, multi-line string representation of all tasks that occur on,
     * are due on, or span across the specified date, using each task's original 1-based index in the list.
     *
     * @param date Date to filter tasks by as a {@link Temporal}.
     * @return Formatted list of matching tasks with original list numbers, or an empty list message if none match.
     */
    public String getTasksOnDate(Temporal date) {
        if (date == null) {
            return "~~~ Empty List ~~~";
        }
        StringBuilder result = new StringBuilder("Here are the tasks for:")
                .append(DateTimeHelper.format(date))
                .append(System.lineSeparator());
        boolean hasMatches = false;
        for (int index = 0; index < tasks.size(); index++) {
            Task task = tasks.get(index);
            if (task.checkDate(date)) {
                hasMatches = true;
                result.append(index + 1)
                        .append(".")
                        .append(task)
                        .append(System.lineSeparator());
            }
        }
        if (!hasMatches) {
            return "~~~ Empty List ~~~";
        }
        return result.toString().stripTrailing();
    }

    /**
     * Returns a numbered, multi-line string representation of tasks whose descriptions contain the whole keyword,
     * using each task's original one-based index in the list.
     *
     * @param keyword Keyword to find in task descriptions.
     * @return Formatted list of matching tasks with original list numbers, or an empty list message if none match.
     */
    public String getTasksWithKeyword(String keyword) {
        StringBuilder result = new StringBuilder("Here are the matching tasks in your list:")
                .append(System.lineSeparator());
        boolean hasMatches = false;
        for (int index = 0; index < tasks.size(); index++) {
            Task task = tasks.get(index);
            if (task.hasKeyword(keyword)) {
                hasMatches = true;
                result.append(index + 1)
                        .append(".")
                        .append(task)
                        .append(System.lineSeparator());
            }
        }
        if (!hasMatches) {
            return "~~~ Empty List ~~~";
        }
        return result.toString().stripTrailing();
    }

    /**
     * Returns all tasks as a numbered, multi-line string.
     *
     * @return The formatted task list.
     */
    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return "~~~ Empty List ~~~";
        }
        StringBuilder result = new StringBuilder("Here are the tasks in your list:")
                .append(System.lineSeparator());
        for (int index = 0; index < tasks.size(); index++) {
            result.append(index + 1)
                    .append(".")
                    .append(tasks.get(index))
                    .append(System.lineSeparator());
        }
        return result.toString().stripTrailing();
    }
}
