package panda.task;

import java.time.LocalDate;
import java.time.temporal.Temporal;

import panda.util.datetime.DateTimeHelper;

/**
 * Represents a task scheduled between supplied start and end date/time values.
 */
public class Event extends Task {
    private final Temporal startDateTime;
    private final Temporal endDateTime;

    /**
     * Creates an unmarked event task.
     *
     * @param description Description of the event.
     * @param startDateTime Event start date and time.
     * @param endDateTime Event end date and time.
     */
    public Event(String description, Temporal startDateTime, Temporal endDateTime) {
        super(description);
        assert startDateTime != null : "Event start date and time must not be null";
        assert endDateTime != null : "Event end date and time must not be null";
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Returns the event's start date and time.
     *
     * @return Event start as a {@link Temporal}.
     */
    public Temporal getStartDateTime() {
        return startDateTime;
    }

    /**
     * Returns the event's end date and time.
     *
     * @return Event end as a {@link Temporal}.
     */
    public Temporal getEndDateTime() {
        return endDateTime;
    }

    /**
     * Checks if the specified date falls within the event's date range (inclusive).
     *
     * @param date The date to check against.
     * @return Whether the date is between or on the event start and end dates.
     */
    @Override
    public boolean occursOn(Temporal date) {
        if (date == null) {
            return false;
        }
        LocalDate targetDate = LocalDate.from(date);
        LocalDate startDate = LocalDate.from(this.startDateTime);
        LocalDate endDate = LocalDate.from(this.endDateTime);
        return !targetDate.isBefore(startDate) && !targetDate.isAfter(endDate);
    }

    /**
     * Returns a string representation of the event.
     *
     * @return Formatted event string.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + DateTimeHelper.formatForDisplay(startDateTime)
                + " to: " + DateTimeHelper.formatForDisplay(endDateTime) + ")";
    }
}
