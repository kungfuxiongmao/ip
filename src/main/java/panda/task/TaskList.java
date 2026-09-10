package panda.task;

import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import panda.exception.task.InvalidTaskListIndexException;
import panda.exception.task.TaskAlreadyMarkedException;
import panda.exception.task.TaskAlreadyUnmarkedException;
import panda.util.datetime.DateTimeHelper;

/**
 * Stores tasks loaded for the current Panda session and provides operations for managing them.
 * Tasks are saved when Panda terminates normally.
 */
public final class TaskList {
    private static TaskList instance;
    private final List<Task> tasks;

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
     */
    public static TaskList of(List<Task> initialTasks) {
        assert initialTasks != null : "Stored task list cannot be null";
        assert initialTasks.stream().allMatch(Objects::nonNull)
                : "Task in stored task list cannot be null";
        assert instance == null : "TaskList has already been initialized";

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
     * @param startDateTime Event start date and time as a {@link Temporal}.
     * @param endDateTime Event end date and time as a {@link Temporal}.
     * @return The newly added task.
     */
    public Task addEvent(String description, Temporal startDateTime, Temporal endDateTime) {
        return add(new Event(description, startDateTime, endDateTime));
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
        assert task != null : "Task added to TaskList must not be null";
        tasks.add(task);
        return task;
    }

    /**
     * Converts a displayed task number to a valid zero-based index.
     *
     * @param taskNumber One-based task number entered by the user.
     * @return Zero-based index of the identified task.
     * @throws InvalidTaskListIndexException If the number does not identify a task in this list.
     */
    private int getValidatedIndex(int taskNumber) throws InvalidTaskListIndexException {
        int arrayIndex = taskNumber - 1;
        if (arrayIndex < 0 || arrayIndex >= tasks.size()) {
            throw new InvalidTaskListIndexException(taskNumber, tasks.size());
        }
        return arrayIndex;
    }

    /**
     * Marks the task with the supplied one-based task number.
     *
     * @param taskNumber Number displayed beside the task.
     * @return The task that was marked.
     * @throws InvalidTaskListIndexException If the number does not identify a task in this list.
     * @throws TaskAlreadyMarkedException If the task is already marked.
     */
    public Task markTask(int taskNumber) throws TaskAlreadyMarkedException, InvalidTaskListIndexException {
        int arrayIndex = getValidatedIndex(taskNumber);

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
    public Task unmarkTask(int taskNumber) throws TaskAlreadyUnmarkedException, InvalidTaskListIndexException {
        int arrayIndex = getValidatedIndex(taskNumber);

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
        int arrayIndex = getValidatedIndex(taskNumber);
        return tasks.remove(arrayIndex);
    }

    /**
     * Returns a numbered, multi-line string representation of all tasks that occur on,
     * are due on, or span across the specified date, using each task's original 1-based index in the list.
     *
     * @param date Date to filter tasks by as a {@link Temporal}.
     * @return Formatted list of matching tasks with original list numbers, or an empty string if none match.
     */
    public String getTasksOnDate(Temporal date) {
        if (date == null) {
            return "";
        }
        String heading = "On " + DateTimeHelper.formatForDisplay(date) + ", these tasks await you:";
        return formatMatchingTasks(task -> task.occursOn(date), heading, "");
    }

    /**
     * Returns a numbered, multi-line string representation of tasks whose descriptions contain the whole keyword,
     * using each task's original one-based index in the list.
     *
     * @param keyword Keyword to find in task descriptions.
     * @return Formatted list of matching tasks with original list numbers, or an empty list message if none match.
     */
    public String getTasksWithKeyword(String keyword) {
        return formatMatchingTasks(task -> task.hasKeyword(keyword),
                "Look again. These are the tasks you seek:",
                "The scroll is empty, young warrior. Every journey begins with a single step.");
    }

    /**
     * Returns all tasks as a numbered, multi-line string.
     *
     * @return The formatted task list.
     */
    @Override
    public String toString() {
        return formatMatchingTasks(task -> true,
                "Look closely, young warrior. These tasks await you:",
                "The scroll is empty, young warrior. Every journey begins with a single step.");
    }

    /**
     * Formats tasks that satisfy the supplied condition, retaining their original task numbers.
     *
     * @param taskCondition Condition used to select tasks for display.
     * @param heading Heading displayed before matching tasks.
     * @param noMatchesMessage Message returned when no tasks match.
     * @return Formatted matching tasks, or the supplied no-match message when none match.
     */
    private String formatMatchingTasks(Predicate<Task> taskCondition, String heading, String noMatchesMessage) {
        StringBuilder result = new StringBuilder(heading).append(System.lineSeparator());
        boolean hasMatches = false;
        for (int index = 0; index < tasks.size(); index++) {
            Task task = tasks.get(index);
            if (taskCondition.test(task)) {
                hasMatches = true;
                result.append(index + 1)
                        .append(".")
                        .append(task)
                        .append(System.lineSeparator());
            }
        }
        if (!hasMatches) {
            return noMatchesMessage;
        }
        return result.toString().stripTrailing();
    }
}
