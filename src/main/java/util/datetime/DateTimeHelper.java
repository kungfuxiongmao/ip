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

    private static final DateTimeFormatter DATE_INPUT = DateTimeFormatter.ofPattern("d/M/uuuu")
            .withResolverStyle(ResolverStyle.STRICT);
    private static final DateTimeFormatter DATETIME_INPUT = DateTimeFormatter.ofPattern("d/M/uuuu H:mm")
            .withResolverStyle(ResolverStyle.STRICT);

    private static final DateTimeFormatter DATE_OUTPUT = DateTimeFormatter.ofPattern("d MMM yyyy");
    private static final DateTimeFormatter DATETIME_OUTPUT = DateTimeFormatter.ofPattern("d MMM yyyy H:mm");

    private static final DateTimeFormatter DATE_STORAGE = DateTimeFormatter.ofPattern("uuuu-MM-dd")
            .withResolverStyle(ResolverStyle.STRICT);
    private static final DateTimeFormatter DATETIME_STORAGE = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm")
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
            LocalDateTime.parse(trimmed, DATETIME_INPUT);
            return true;
        } catch (DateTimeParseException e) {
            // Not a date-time; try validating as a date-only string
        }
        try {
            LocalDate.parse(trimmed, DATE_INPUT);
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
            return LocalDateTime.parse(trimmed, DATETIME_INPUT);
        } catch (DateTimeParseException e) {
            // Not a date-time; try parsing as a date-only string
        }
        return LocalDate.parse(trimmed, DATE_INPUT);
    }

    /**
     * Formats a {@link Temporal} instance into its user-facing display string representation.
     * <p>
     * If the temporal is an instance of {@link LocalDateTime}, it is formatted as {@code "d MMM yyyy H:mm"}.
     * If the temporal is an instance of {@link LocalDate}, it is formatted as {@code "d MMM yyyy"}.
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
            return DATETIME_OUTPUT.format(temporal);
        }
        if (temporal instanceof LocalDate) {
            return DATE_OUTPUT.format(temporal);
        }
        throw new IllegalArgumentException("Unsupported Temporal type: " + temporal.getClass().getName());
    }

    /**
     * Converts a {@link Temporal} instance to a serialized string for storage.
     * <p>
     * If the temporal is an instance of {@link LocalDateTime}, it is formatted using {@code "uuuu-MM-dd HH:mm"}.
     * If the temporal is an instance of {@link LocalDate}, it is formatted using {@code "uuuu-MM-dd"}.
     *
     * @param temporal the {@link Temporal} to format for saving (must be {@link LocalDate} or {@link LocalDateTime})
     * @return the serialized date or date-time string for storage
     * @throws IllegalArgumentException if the temporal parameter is null or an unsupported type
     */
    public static String saveDate(Temporal temporal) {
        if (temporal == null) {
            throw new IllegalArgumentException("Temporal object cannot be null");
        }
        if (temporal instanceof LocalDateTime) {
            return DATETIME_STORAGE.format(temporal);
        }
        if (temporal instanceof LocalDate) {
            return DATE_STORAGE.format(temporal);
        }
        throw new IllegalArgumentException("Unsupported Temporal type: " + temporal.getClass().getName());
    }

    /**
     * Parses a serialized date or date-time string from file storage into a {@link Temporal} instance.
     * <p>
     * Supports stored date-time ({@code "uuuu-MM-dd HH:mm"}) and date-only ({@code "uuuu-MM-dd"}) formats,
     * falling back to user-input formats for backward compatibility.
     *
     * @param dateStr the saved date or date-time string to parse
     * @return a {@link Temporal} representing the parsed date or date-time
     * @throws DateTimeParseException if the input cannot be parsed using the storage format
     * @throws IllegalArgumentException if the input is null or blank
     */
    public static Temporal loadDate(String dateStr) throws DateTimeParseException {
        if (dateStr == null || dateStr.isBlank()) {
            throw new IllegalArgumentException("Saved date/time string cannot be null or blank");
        }
        String trimmed = dateStr.strip();
        try {
            return LocalDateTime.parse(trimmed, DATETIME_STORAGE);
        } catch (DateTimeParseException e) {
            // Not a storage date-time; try parsing as storage date-only
        }
        try {
            return LocalDate.parse(trimmed, DATE_STORAGE);
        } catch (DateTimeParseException e) {
            // Fall back to input format for backward compatibility
            return parse(trimmed);
        }
    }
}
