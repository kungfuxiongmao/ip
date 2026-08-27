package panda.command;

import panda.exception.ApplicationException;

/**
 * Represents an action that Panda can perform for a user command.
 */
public interface Command {

    /**
     * Performs this command.
     *
     * @throws ApplicationException If an application error occurs during execution.
     */
    void execute() throws ApplicationException;
}
