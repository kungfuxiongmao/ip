package panda.command;

import panda.exception.storage.TaskSavingException;
import panda.lifecycle.TerminationManager;
import panda.task.TaskList;

/**
 * Prints Panda's farewell and ends the program.
 */
public class ByeCommand implements Command {

    /**
     * Saves the current task list and terminates the application session.
     *
     * @param taskList Active task list.
     * @throws TaskSavingException If the current task list cannot be saved.
     */
    @Override
    public void execute(TaskList taskList) throws TaskSavingException {
        TerminationManager.terminate(taskList);
    }
}
