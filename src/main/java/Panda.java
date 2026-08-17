public class Panda {
    private static void greet() {
        String divider = "____________________________________________________________";
        String banner = """
                                                            _______               \s
                _________   _...._                  _..._   \\  ___ `'.            \s
                \\        |.'      '-.             .'     '.  ' |--.\\  \\           \s
                 \\        .'```'.    '.          .   .-.   . | |    \\  '          \s
                  \\      |       \\     \\   __    |  '   '  | | |     |  '    __   \s
                   |     |        |    |.:--.'.  |  |   |  | | |     |  | .:--.'. \s
                   |      \\      /    ./ |   \\ | |  |   |  | | |     ' .'/ |   \\ |\s
                   |     |\\`'-.-'   .' `" __ | | |  |   |  | | |___.' /' `" __ | |\s
                   |     | '-....-'`    .'.''| | |  |   |  |/_______.'/   .'.''| |\s
                  .'     '.            / /   | |_|  |   |  |\\_______|/   / /   | |_
                '-----------'          \\ \\._,\\ '/|  |   |  |             \\ \\._,\\ '/
                                        `--'  `" '--'   '--'              `--'  `"\s
                """;
        System.out.println(divider);
        System.out.print(banner);
        System.out.println("Hello! I'm Panda.");
        System.out.println("What can I do for you?");
        System.out.println(divider);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(divider);
    }

    public static void main(String[] args) {
        greet();
    }
}
