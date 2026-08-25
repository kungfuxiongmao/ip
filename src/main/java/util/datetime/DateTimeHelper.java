package util.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.temporal.Temporal;

/**
 * Utility class for parsing, validating, and formatting date and date-time values.
 * <p>
 * This class cannot be instantiated. It provides static helper methods to convert between
 * user-provided string inputs and Java {@link Temporal} representations ({@link LocalDate}
 * and {@link LocalDateTime}), supporting inputs both with and without a time component.
 */
public final class DateTimeHelper {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d/M/uuuu")
            .withResolverStyle(ResolverStyle.STRICT);
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("d/M/uuuu H:mm")
            .withResolverStyle(ResolverStyle.STRICT);

    private DateTimeHelper() {
        // Prevent instantiation of utility class
    }

    /**
     * Checks whether the given string is a valid date or date-time in the supported format.
     *
     * @param dateTime the date or date-time string to validate
     * @return {@code true} if the input is a valid date or date-time; {@code false} otherwise
     */
    public static boolean isValidDateTime(String dateTime) {
        if (dateTime == null || dateTime.isBlank()) {
            return false;
        }
        String trimmed = dateTime.strip();
        try {
            LocalDateTime.parse(trimmed, DATE_TIME_FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            // Not a date-time; try validating as a date-only string
        }
        try {
            LocalDate.parse(trimmed, DATE_FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Parses a string representation into a {@link Temporal} instance.
     * <p>
     * Returns a {@link LocalDateTime} if the input contains both date and time (e.g. {@code "2/12/2019 18:00"}),
     * or a {@link LocalDate} if the input contains only a date (e.g. {@code "2/12/2019"}).
     *
     * @param dateTime the date or date-time string to parse
     * @return a {@link Temporal} representing the parsed date or date-time
     * @throws DateTimeParseException if the input cannot be parsed into a valid date or date-time
     * @throws IllegalArgumentException if the input is null or blank
     */
    public static Temporal parse(String dateTime) throws DateTimeParseException {
        if (dateTime == null || dateTime.isBlank()) {
            throw new IllegalArgumentException("Date/time string cannot be null or blank");
        }
        String trimmed = dateTime.strip();
        try {
            return LocalDateTime.parse(trimmed, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            // Not a date-time; try parsing as a date-only string
        }
        return LocalDate.parse(trimmed, DATE_FORMATTER);
    }

    /**
     * Formats a {@link Temporal} instance back into its string representation.
     * <p>
     * If the temporal is an instance of {@link LocalDateTime}, it is formatted as {@code "d/M/uuuu H:mm"}.
     * If the temporal is an instance of {@link LocalDate}, it is formatted as {@code "d/M/uuuu"}.
     *
     * @param temporal the {@link Temporal} to format (must be {@link LocalDate} or {@link LocalDateTime})
     * @return the formatted date or date-time string
     * @throws IllegalArgumentException if the temporal parameter is null or an unsupported type
     */
    public static String format(Temporal temporal) {
        if (temporal == null) {
            throw new IllegalArgumentException("Temporal object cannot be null");
        }
        if (temporal instanceof LocalDateTime) {
            return DATE_TIME_FORMATTER.format(temporal);
        }
        if (temporal instanceof LocalDate) {
            return DATE_FORMATTER.format(temporal);
        }
        throw new IllegalArgumentException("Unsupported Temporal type: " + temporal.getClass().getName());
    }
}
