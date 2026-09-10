package panda.command;

import panda.exception.storage.TaskSavingException;
import panda.lifecycle.TerminationManager;

/**
 * Prints Panda's farewell and ends the program.
 */
public class ByeCommand implements Command {

    /**
     * Saves the current task list and terminates the application session.
     *
     * @throws TaskSavingException If the current task list cannot be saved.
     */
    @Override
    public void execute() throws TaskSavingException {
        TerminationManager.terminate();
    }
}
