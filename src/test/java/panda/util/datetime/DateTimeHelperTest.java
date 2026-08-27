package panda.util.datetime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link DateTimeHelper}.
 */
public class DateTimeHelperTest {

    @Test
    public void isValidDateTime_validInputs_returnsTrue() {
        assertTrue(DateTimeHelper.isValidDateTime("15/10/2026 18:00"));
        assertTrue(DateTimeHelper.isValidDateTime("2/12/2019"));
        assertTrue(DateTimeHelper.isValidDateTime(" 15/10/2026 18:00 "));
    }

    @Test
    public void isValidDateTime_invalidInputs_returnsFalse() {
        assertFalse(DateTimeHelper.isValidDateTime("invalid-date"));
        assertFalse(DateTimeHelper.isValidDateTime("32/1/2026"));
        assertFalse(DateTimeHelper.isValidDateTime(""));
        assertFalse(DateTimeHelper.isValidDateTime("   "));
        assertFalse(DateTimeHelper.isValidDateTime(null));
    }

    @Test
    public void parse_validDateTime_returnsLocalDateTime() {
        Temporal temporal = DateTimeHelper.parse("15/10/2026 18:00");
        assertEquals(LocalDateTime.of(2026, 10, 15, 18, 0), temporal);
    }

    @Test
    public void parse_validDateOnly_returnsLocalDate() {
        Temporal temporal = DateTimeHelper.parse("15/10/2026");
        assertEquals(LocalDate.of(2026, 10, 15), temporal);
    }

    @Test
    public void parse_nullOrBlankInput_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> DateTimeHelper.parse(null));
        assertThrows(IllegalArgumentException.class, () -> DateTimeHelper.parse("   "));
    }

    @Test
    public void format_localDateTime_returnsFormattedString() {
        LocalDateTime ldt = LocalDateTime.of(2019, 12, 2, 18, 0);
        assertEquals("2 Dec 2019 18:00", DateTimeHelper.format(ldt));
    }

    @Test
    public void format_localDate_returnsFormattedString() {
        LocalDate ld = LocalDate.of(2019, 12, 2);
        assertEquals("2 Dec 2019", DateTimeHelper.format(ld));
    }

    @Test
    public void format_nullInput_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> DateTimeHelper.format(null));
    }

    @Test
    public void saveDate_localDateTime_returnsStorageFormat() {
        LocalDateTime ldt = LocalDateTime.of(2019, 12, 2, 18, 0);
        assertEquals("2019-12-02 18:00", DateTimeHelper.saveDate(ldt));
    }

    @Test
    public void saveDate_localDate_returnsStorageFormat() {
        LocalDate ld = LocalDate.of(2019, 12, 2);
        assertEquals("2019-12-02", DateTimeHelper.saveDate(ld));
    }

    @Test
    public void loadDate_validStorageFormats_returnsTemporal() {
        Temporal dateTime = DateTimeHelper.loadDate("2019-12-02 18:00");
        assertEquals(LocalDateTime.of(2019, 12, 2, 18, 0), dateTime);

        Temporal dateOnly = DateTimeHelper.loadDate("2019-12-02");
        assertEquals(LocalDate.of(2019, 12, 2), dateOnly);
    }

    @Test
    public void loadDate_fallbackToInputFormat_returnsTemporal() {
        Temporal fallback = DateTimeHelper.loadDate("2/12/2019");
        assertEquals(LocalDate.of(2019, 12, 2), fallback);
    }

    @Test
    public void loadDate_nullOrBlank_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> DateTimeHelper.loadDate(null));
        assertThrows(IllegalArgumentException.class, () -> DateTimeHelper.loadDate("   "));
    }
}
