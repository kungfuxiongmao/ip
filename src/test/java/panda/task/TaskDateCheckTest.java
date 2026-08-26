package panda.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for task date checking logic across {@link Todo}, {@link Deadline}, and {@link Event}.
 */
public class TaskDateCheckTest {

    @Test
    public void todoCheckDate_alwaysReturnsFalse() {
        Todo todo = new Todo("read book");
        assertFalse(todo.checkDate(LocalDate.of(2026, 10, 15)));
        assertFalse(todo.checkDate(LocalDateTime.of(2026, 10, 15, 12, 0)));
        assertFalse(todo.checkDate(null));
    }

    @Test
    public void deadlineCheckDate_sameDate_returnsTrue() {
        Deadline deadline = new Deadline("submit assignment", LocalDateTime.of(2026, 10, 15, 18, 0));
        assertTrue(deadline.checkDate(LocalDate.of(2026, 10, 15)));
        assertTrue(deadline.checkDate(LocalDateTime.of(2026, 10, 15, 9, 0)));
    }

    @Test
    public void deadlineCheckDate_differentDate_returnsFalse() {
        Deadline deadline = new Deadline("submit assignment", LocalDate.of(2026, 10, 15));
        assertFalse(deadline.checkDate(LocalDate.of(2026, 10, 16)));
        assertFalse(deadline.checkDate(LocalDate.of(2026, 10, 14)));
        assertFalse(deadline.checkDate(null));
    }

    @Test
    public void eventCheckDate_encompassingDates_returnsTrue() {
        Event event = new Event("camp",
                LocalDate.of(2026, 10, 15),
                LocalDate.of(2026, 10, 17));

        // Start date
        assertTrue(event.checkDate(LocalDate.of(2026, 10, 15)));
        // Middle date
        assertTrue(event.checkDate(LocalDate.of(2026, 10, 16)));
        // End date
        assertTrue(event.checkDate(LocalDate.of(2026, 10, 17)));
    }

    @Test
    public void eventCheckDate_outOfRangeDates_returnsFalse() {
        Event event = new Event("camp",
                LocalDate.of(2026, 10, 15),
                LocalDate.of(2026, 10, 17));

        assertFalse(event.checkDate(LocalDate.of(2026, 10, 14)));
        assertFalse(event.checkDate(LocalDate.of(2026, 10, 18)));
        assertFalse(event.checkDate(null));
    }
}
