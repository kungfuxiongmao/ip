package panda.task;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;

import panda.exception.task.EventClashException;

/**
 * Contains unit tests for {@link EventScheduleIndex} interval operations.
 */
public class EventScheduleIndexTest {

    @Test
    public void add_adjacentEvent_addsEvent() throws Exception {
        EventScheduleIndex index = new EventScheduleIndex();
        Event indexedEvent = createEvent("lecture", 10, 0, 11, 0);
        Event adjacentEvent = createEvent("lunch", 11, 0, 12, 0);

        index.add(indexedEvent);

        assertDoesNotThrow(() -> index.add(adjacentEvent));
    }

    @Test
    public void add_eventOverlappingMultipleEvents_throwsWithChronologicalConflicts() throws Exception {
        EventScheduleIndex index = new EventScheduleIndex();
        Event laterEvent = createEvent("laboratory", 11, 0, 12, 0);
        Event earlierEvent = createEvent("lecture", 9, 0, 10, 0);
        Event proposedEvent = createEvent("workshop", 9, 30, 11, 30);

        index.add(laterEvent);
        index.add(earlierEvent);

        EventClashException exception = assertThrows(
                EventClashException.class, () -> index.add(proposedEvent));

        assertEquals(List.of(earlierEvent, laterEvent), exception.getConflictingEvents());
    }

    @Test
    public void add_eventDuringDateOnlyEvent_throwsEventClashException() throws Exception {
        EventScheduleIndex index = new EventScheduleIndex();
        Event allDayEvent = new Event("conference",
                LocalDate.of(2026, 9, 10),
                LocalDate.of(2026, 9, 10));
        Event eveningEvent = createEvent("dinner", 18, 0, 19, 0);

        index.add(allDayEvent);

        EventClashException exception = assertThrows(
                EventClashException.class, () -> index.add(eveningEvent));

        assertEquals(List.of(allDayEvent), exception.getConflictingEvents());
    }

    @Test
    public void remove_indexedEvent_freesInterval() throws Exception {
        EventScheduleIndex index = new EventScheduleIndex();
        Event indexedEvent = createEvent("lecture", 10, 0, 11, 0);
        Event sameTimeEvent = createEvent("consultation", 10, 0, 11, 0);

        index.add(indexedEvent);
        index.remove(indexedEvent);

        assertDoesNotThrow(() -> index.add(sameTimeEvent));
    }

    @Test
    public void add_overlappingEvent_throwsEventClashException() throws Exception {
        EventScheduleIndex index = new EventScheduleIndex();
        Event indexedEvent = createEvent("lecture", 10, 0, 11, 0);
        Event overlappingEvent = createEvent("consultation", 10, 30, 11, 30);

        index.add(indexedEvent);

        EventClashException exception = assertThrows(
                EventClashException.class, () -> index.add(overlappingEvent));

        assertEquals(List.of(indexedEvent), exception.getConflictingEvents());
    }

    private static Event createEvent(String description, int startHour, int startMinute,
            int endHour, int endMinute) throws Exception {
        return new Event(description,
                LocalDateTime.of(2026, 9, 10, startHour, startMinute),
                LocalDateTime.of(2026, 9, 10, endHour, endMinute));
    }
}
