package panda.command;

import panda.exception.ApplicationException;

/**
 * Represents an action that Panda can perform for a user command.
 */
public interface Command {
    /**
     * Performs this command.
     *
     * @throws ApplicationException if an application-level error occurs during command execution
     */
    void execute() throws ApplicationException;
}
