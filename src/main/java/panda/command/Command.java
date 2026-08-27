package panda.command;

import panda.exception.ApplicationException;

/**
 * Represents an action that Panda can perform for a user command.
 */
public interface Command {
    /**
     * Performs the command.
     *
     */
    void execute() throws ApplicationException;
}
