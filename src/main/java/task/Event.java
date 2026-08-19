package task;

/**
 * A task scheduled between supplied start and end date/time strings.
 */
public class Event extends Task {
    private final String dateTimeFrom;
    private final String dateTimeTo;

    /**
     * Creates an unmarked event task.
     *
     * @param description description of the event
     * @param dateTimeFrom event start, kept in its input format
     * @param dateTimeTo event end, kept in its input format
     */
    protected Event(String description, String dateTimeFrom, String dateTimeTo) {
        super(description);
        this.dateTimeFrom = dateTimeFrom;
        this.dateTimeTo = dateTimeTo;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + dateTimeFrom + " to: " + dateTimeTo + ")";
    }
}
