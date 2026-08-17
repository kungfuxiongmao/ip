package commands;

/**
 * Prints Panda's farewell and ends the program.
 */
public class ByeCommand implements Command {
    private static final String DIVIDER = "____________________________________________________________";

    @Override
    public void execute() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
        System.exit(0);
    }
}
