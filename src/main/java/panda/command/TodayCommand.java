package panda.command;

import java.time.LocalDate;

/**
 * Displays tasks occurring on, due on, or spanning across today's date.
 */
public class TodayCommand implements Command {

    /**
     * Retrieves and displays tasks that fall on or span across the current system date.
     */
    @Override
    public void execute() {
        LocalDate today = LocalDate.now();
        DisplayDateCommand.displayTasksOnDate(today);
    }
}
