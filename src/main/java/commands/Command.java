package commands;

import tasklist.TaskList;

/**
 * Represents an action that Panda can perform for a user command.
 */
public interface Command {
    /**
     * Performs this command.
     *
     */
    void execute(TaskList taskList);
}
