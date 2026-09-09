package panda.command;

import java.time.LocalDate;

import panda.task.TaskList;
import panda.ui.Ui;

/**
 * Displays tasks occurring on, due on, or spanning across today's date.
 */
public class TodayCommand implements Command {

    /**
     * Constructs a {@code TodayCommand}.
     */
    public TodayCommand() {
    }

    /**
     * Retrieves and displays tasks that fall on or span across the current system date.
     */
    @Override
    public void execute() {
        LocalDate today = LocalDate.now();
        String tasksToday = TaskList.getInstance().getTasksOnDate(today);
        if (tasksToday.isEmpty()) {
            Ui.printMessage("The scroll shows no tasks today, young warrior.");
            return;
        }
        Ui.printMessage(tasksToday);
    }
}
