package panda.task;

import java.time.LocalDate;
import java.time.temporal.Temporal;

import panda.util.datetime.DateTimeHelper;

/**
 * A task scheduled between supplied start and end date/time values.
 */
public class Event extends Task {
    private final Temporal dateTimeFrom;
    private final Temporal dateTimeTo;

    /**
     * Creates an unmarked event task.
     *
     * @param description Description of the event.
     * @param dateTimeFrom Event start date and time.
     * @param dateTimeTo Event end date and time.
     */
    public Event(String description, Temporal dateTimeFrom, Temporal dateTimeTo) {
        super(description);
        this.dateTimeFrom = dateTimeFrom;
        this.dateTimeTo = dateTimeTo;
    }

    /**
     * Returns the event's start date and time.
     *
     * @return Event start as a {@link Temporal}.
     */
    public Temporal getDateTimeFrom() {
        return dateTimeFrom;
    }

    /**
     * Returns the event's end date and time.
     *
     * @return Event end as a {@link Temporal}.
     */
    public Temporal getDateTimeTo() {
        return dateTimeTo;
    }

    @Override
    public boolean checkDate(Temporal date) {
        if (date == null) {
            return false;
        }
        LocalDate targetDate = LocalDate.from(date);
        LocalDate fromDate = LocalDate.from(this.dateTimeFrom);
        LocalDate toDate = LocalDate.from(this.dateTimeTo);
        return !targetDate.isBefore(fromDate) && !targetDate.isAfter(toDate);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + DateTimeHelper.format(dateTimeFrom)
                + " to: " + DateTimeHelper.format(dateTimeTo) + ")";
    }
}
