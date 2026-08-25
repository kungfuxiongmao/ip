package panda.command;

import java.time.LocalDate;

import panda.task.TaskList;
import panda.ui.UI;

/**
 * Displays tasks occurring on, due on, or spanning across today's date.
 */
public class TodayCommand implements Command {

    @Override
    public void execute() {
        LocalDate today = LocalDate.now();
        UI.printMessage(TaskList.getInstance().getTasksOnDate(today));
    }
}
