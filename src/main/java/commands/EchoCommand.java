package commands;

/**
 * Echoes a command that Panda does not handle specially yet.
 */
public class EchoCommand implements Command {
    private final String input;

    /**
     * Creates an echo command for the supplied input.
     *
     * @param input command text to echo
     */
    public EchoCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute() {
        System.out.println(" " + input);
    }
}
