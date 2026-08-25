package task;

import java.time.LocalDate;
import java.time.temporal.Temporal;

import util.datetime.DateTimeHelper;

/**
 * A task scheduled between supplied start and end date/time values.
 */
public class Event extends Task {
    private final Temporal dateTimeFrom;
    private final Temporal dateTimeTo;

    /**
     * Creates an unmarked event task.
     *
     * @param description description of the event
     * @param dateTimeFrom event start date and time
     * @param dateTimeTo event end date and time
     */
    public Event(String description, Temporal dateTimeFrom, Temporal dateTimeTo) {
        super(description);
        this.dateTimeFrom = dateTimeFrom;
        this.dateTimeTo = dateTimeTo;
    }

    /**
     * Returns the event's start date and time.
     *
     * @return event start as a {@link Temporal}
     */
    public Temporal getDateTimeFrom() {
        return dateTimeFrom;
    }

    /**
     * Returns the event's end date and time.
     *
     * @return event end as a {@link Temporal}
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
